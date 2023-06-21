package pl.psi;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

import lombok.Getter;
import pl.psi.creatures.Creature;
import pl.psi.creatures.DoubleAttackCreature;
import pl.psi.creatures.Spell;
import pl.psi.specialFields.ObstaclesWeight;

import static java.lang.Math.abs;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class GameEngine {

    public static final String CREATURE_MOVED = "CREATURE_MOVED";
    private final TurnQueue turnQueue;
    private final Board board;
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);
    private final Hero hero1;
    private final Hero hero2;
    @Getter
    private final ObstaclePlacementList obstaclesList;


    public GameEngine(final Hero aHero1, final Hero aHero2, final ObstaclePlacementList obstaclesList) {
        turnQueue = new TurnQueue(aHero1.getCreatures(), aHero2.getCreatures());
        board = new Board(aHero1.getCreatures(), aHero2.getCreatures(), obstaclesList.getObstaclePlacement());
        hero1 = aHero1;
        hero2 = aHero2;
        this.obstaclesList = obstaclesList;
    }

    public GameEngine(final Hero aHero1, final Hero aHero2) {
        this(aHero1, aHero2, new ObstaclePlacementList(Collections.emptyMap()));
    }

    public void attack(final Point point) {
        board.getObject(point)
                .ifPresent(defender -> turnQueue.getCurrentCreature()
                        .attack(defender));
        pass();
    }

    public boolean canMove(final Point aPoint) {
        return board.canMove(turnQueue.getCurrentCreature(), aPoint);
    }

    public void move(final Point aPoint) {
        Runnable runnable = (() -> {
            Point startPoint = getPosition(turnQueue.getCurrentCreature());

            Node startingNode = new Node(startPoint.getX(), startPoint.getY());
            Node goalNode = new Node(aPoint.getX(), aPoint.getY());
            List<Node> path = generateMovesList(startingNode, goalNode);
            if (path != null) {
                for (Node node : path) {
                    board.move(turnQueue.getCurrentCreature(), node);
                    Creature creature = turnQueue.getCurrentCreature();
                    observerSupport.firePropertyChange(CREATURE_MOVED, startingNode, node);
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
        if(!obstaclesList.getObstaclePlacement().isEmpty()){
            list.forEach(this::chooseWeight);
        }

        return list;
    }

    public List<Node> generateMovesList(Node startingNode, Node destinationNode) {
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

    public void chooseWeight(Node aNode) {

        if (obstaclesList.getObstaclePlacement().containsKey(aNode)) {
            String name = obstaclesList.getObstaclePlacement().get(aNode).getName();
            int weight = 1;
           if (Objects.equals(name, ObstaclesWeight.BOULDER.getName())){
                weight = ObstaclesWeight.BOULDER.getWeight();
            }
           else if(Objects.equals(name,ObstaclesWeight.SPIKES.getName())){
               weight = ObstaclesWeight.SPIKES.getWeight();
           }
           else if(Objects.equals(name,ObstaclesWeight.FIREWALL.getName())){
               weight = ObstaclesWeight.FIREWALL.getWeight();
           }
           else if(Objects.equals(name,ObstaclesWeight.WEAKNESS_TOTEM.getName())){
               weight = ObstaclesWeight.WEAKNESS_TOTEM.getWeight();
           }
           else if(Objects.equals(name,ObstaclesWeight.HEALING_TOTEM.getName())){
               weight = ObstaclesWeight.HEALING_TOTEM.getWeight();
           }
           else if(Objects.equals(name,ObstaclesWeight.ROCK.getName())){
               weight = ObstaclesWeight.ROCK.getWeight();
           }
           else if(Objects.equals(name,ObstaclesWeight.TREE.getName())){
               weight = ObstaclesWeight.TREE.getWeight();
           }

            aNode.setWeight(weight);
        }
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

    public Optional<Defendable> getObject(final Point aPoint) {
        return board.getObject(aPoint);
    }

    public Optional<Creature> getCreature(Point aPoint) {
        return board.getCreature(aPoint);
    }

    public void pass() {
        turnQueue.next();
    }

    public void addObserver(final PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aObserver);
        turnQueue.addObserver(aObserver);
    }

    public boolean canAttack(final Point point) {
        double distance = board.getPosition(turnQueue.getCurrentCreature())
                .distance(point);
        return board.getObject(point)
                .isPresent()
                && distance < 2 && distance > 0;
    }

    public void castSpell(final Point aPoint, Spell aSpell) {
        if (board.getCreature(aPoint).isPresent()) {
            aSpell.cast(board.getCreature(aPoint).get());
            pass();
        }
    }

    public boolean isCurrentCreature(Point aPoint) {
        return Optional.of(turnQueue.getCurrentCreature()).equals(board.getObject(aPoint));
    }

    public List<Spell> getSpellBook() {
        if (hero1.getCreatures().contains(turnQueue.getCurrentCreature())) {
            return hero1.getSpellBook();
        } else {
            return hero2.getSpellBook();
        }
    }

    public Point getPosition(Creature aCreature) {
        return board.getPosition(aCreature);
    }
}


