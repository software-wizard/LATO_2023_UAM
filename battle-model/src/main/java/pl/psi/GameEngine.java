package pl.psi;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

import pl.psi.creatures.BattleUnit;
import pl.psi.creatures.Creature;
import pl.psi.creatures.Spell;
import pl.psi.creatures.SpellFailureCalculator;

import static java.lang.Math.abs;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class GameEngine {

    public static final String UNIT_MOVED = "UNIT_MOVED";
    private final TurnQueue turnQueue;
    private final Board board;
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);

    private final Hero hero1;
    private final Hero hero2;

    private final AutonomousUnitController autonomousUnitController;


    public GameEngine(final Hero aHero1, final Hero aHero2, final ObstaclePlacementList obstaclesList) {
        hero1 = aHero1;
        hero2 = aHero2;
        turnQueue = new TurnQueue(hero1.getBattleUnits(), hero2.getBattleUnits());
        board = new Board(hero1.getBattleUnits(), hero2.getBattleUnits());

        autonomousUnitController = new AutonomousUnitController(aHero1, aHero2, this::pass);
        addObserver(autonomousUnitController);
    }

    public GameEngine(final Hero aHero1, final Hero aHero2) {
        this(aHero1, aHero2, new ObstaclePlacementList(Collections.emptyMap()));
    }

    public void attack(final Point point) {
        if(board.getBattleUnit(point).isPresent()){
            turnQueue.getCurrentBattleUnit().attack(board.getBattleUnit(point).get());
        }
        pass();
    }

    public void heal(final Point point){
        if(board.getBattleUnit(point).isPresent()){
            turnQueue.getCurrentBattleUnit().heal(board.getBattleUnit(point).get());
        }
        pass();
    }

    public boolean canMove(final Point aPoint) {
        return board.canMove(turnQueue.getCurrentBattleUnit(), aPoint);
    }

    public void move(final Point aPoint) {
            Runnable runnable = (() -> {
                Map<Point, Integer> obstacles = Collections.emptyMap();
                Point startPoint = getPosition(turnQueue.getCurrentCreature());

                Node startingNode = new Node(startPoint.getX(), startPoint.getY());
                Node goalNode = new Node(aPoint.getX(), aPoint.getY());
                List<Node> path = generateMovesList(startingNode, goalNode, obstacles);
                if (path != null) {
                    for (Node node : path) {
                        board.move(turnQueue.getCurrentCreature(), node);
                        observerSupport.firePropertyChange(UNIT_MOVED, startingNode, node);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        startingNode = node;
                    }
                }
            });
            Thread thread = new Thread(runnable);
            thread.start();
    }

        public List<Node> generateNeigboursList(int x, int y) {
            List<Node> list = new ArrayList<>();
            if (y != 0) {
                //UP
                list.add(new Node(x, y - 1));
            }
            if (y != 9) {
                //DOWN
                list.add(new Node(x, y + 1));
            }
            if (x != 0) {
                //LEFT
                list.add(new Node(x - 1, y));
            }
            if (x != 14) {
                //RIGHT
                list.add(new Node(x + 1, y));
            }
            return list;
        }

        public List<Node> generateMovesList(Node startingNode, Node destinationNode, Map<Point, Integer> obstacles) {
            PriorityQueue<Node> openNodes = new PriorityQueue<>();
            ArrayList<Node> explored = new ArrayList<>();

            startingNode.setCost(calculateHeuristic(startingNode, destinationNode) + startingNode.getWeight());
            openNodes.add(startingNode);
            while (!openNodes.isEmpty()) {

                Node currentNode = openNodes.poll();

                if (currentNode.equals(destinationNode)) {
                    return reconstructPath(currentNode);
                }


                List<Node> neighbours = generateNeigboursList(currentNode.getX(), currentNode.getY());
                explored.add(currentNode);

                for (Node neighbour : neighbours) {

                    chooseWeight(neighbour, obstacles);
                    int h = calculateHeuristic(neighbour, destinationNode);
                    int g = neighbour.getWeight() + currentNode.getCostToReach();
                    int f = g + h;


                    if (!openNodes.contains(neighbour) && (!explored.contains(neighbour))) {
                        neighbour.setCost(f);
                        neighbour.setCostToReach(g);
                        neighbour.setHeuristic(h);
                        neighbour.setParent(currentNode);
                        openNodes.add(neighbour);


                    } else if (openNodes.contains(neighbour)) {
                        Optional<Node> result = openNodes.stream().filter(obj -> obj.equals(neighbour)).findFirst();
                        if (result.isPresent()) {
                            Node aNode = result.get();
                            if (aNode.getCost() > f) {
                                neighbour.setCost(f);
                                neighbour.setCostToReach(g);
                                neighbour.setHeuristic(h);
                                neighbour.setParent(currentNode);
                                openNodes.remove(aNode);
                                openNodes.add(neighbour);
                            }
                        }

                    }
                }
            }
            return null;
        }

        private void chooseWeight(Node aNode, Map<Point, Integer> obstacles) {

            if (obstacles.containsKey(aNode)) {
                aNode.setWeight(obstacles.get(aNode));
                return;
            }
            aNode.setWeight(1);
        }


        private List<Node> reconstructPath(Node current) {
            List<Node> path = new ArrayList<>();
            while (current != null) {
                path.add(current);
                current = current.getParent();
            }
            Collections.reverse(path);
            return path;
        }

        int calculateHeuristic(Node currentNode, Node destinationNode) {
            int xCost = abs(currentNode.getX() - destinationNode.getX());
            int yCost = abs(currentNode.getY() - destinationNode.getY());
            return xCost + yCost;
        }

    public Optional<BattleUnit> getBattleUnit(final Point aPoint) {
        return board.getBattleUnit(aPoint);
    }
    public Optional<Defendable> getObject(final Point aPoint) {
        return board.getObject(aPoint);
    }

    public void pass() {
        turnQueue.next();
    }

    public void addObserver(final PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aObserver);
        turnQueue.addObserver(aObserver);
    }

    public boolean canAttack(final Point point) {
        if (board.getObject(point).isPresent()) {
            if (turnQueue.getCurrentBattleUnit().isCreature()) {
                double distance = board.getPosition(turnQueue.getCurrentBattleUnit())
                        .distance(point);
                return distance < 2 && distance > 0 && !hero1.isAlly(turnQueue.getCurrentBattleUnit(), board.getBattleUnit(point).get());
            } else {
                if (turnQueue.getCurrentBattleUnit().canAttack()) {
                    return !hero1.isAlly(turnQueue.getCurrentBattleUnit(), board.getBattleUnit(point).get());
                }
            }
        }
        return false;
    }

    public boolean canHeal(final Point point){
        if(board.getBattleUnit(point).isPresent()){
            if(turnQueue.getCurrentBattleUnit().isWarMachine() && board.getBattleUnit(point).get().isCreature()){
                return hero1.isAlly(turnQueue.getCurrentBattleUnit(), board.getBattleUnit(point).get())
                        && board.getBattleUnit(point).get().getCurrentHp() < board.getBattleUnit(point).get().getMaxHp()
                        && turnQueue.getCurrentBattleUnit().canHeal();
            }
        }
        return false;
    }

    public void castSpell(final Point aPoint, Spell aSpell) {
        if (board.getBattleUnit(aPoint).isPresent()) {

            SpellFailureCalculator s = new SpellFailureCalculator();
            Creature c = (board.getBattleUnit(aPoint).get());

            if(s.spellWillNotFail(c)){
                aSpell.cast(board.getBattleUnit(aPoint).get());
            }
            pass();
        }
    }

    public boolean isCurrentBattleUnit(Point aPoint) {
        return Optional.of(turnQueue.getCurrentBattleUnit()).equals(board.getBattleUnit(aPoint));
    }

    public List<Spell> getSpellBook() {
        if (hero1.getCreatures().contains(turnQueue.getCurrentCreature())) {
            return hero1.getSpellBook();
        } else {
            return hero2.getSpellBook();
        }
    }

    public Point getPosition(BattleUnit aBattleUnit) {
        return board.getPosition(aBattleUnit);
    }
}


