package pl.psi;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

import pl.psi.creatures.Creature;

import static java.lang.Math.abs;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class GameEngine {

    public static final String CREATURE_MOVED = "CREATURE_MOVED";
    private final TurnQueue turnQueue;
    private final Board board;
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);


    public GameEngine(final Hero aHero1, final Hero aHero2) {
        turnQueue = new TurnQueue(aHero1.getCreatures(), aHero2.getCreatures());
        board = new Board(aHero1.getCreatures(), aHero2.getCreatures());
    }

    public void attack(final Point point) {
        board.getCreature(point)
                .ifPresent(defender -> turnQueue.getCurrentCreature()
                        .attack(defender));
        pass();
    }

    public boolean canMove(final Point aPoint) {
        return board.canMove(turnQueue.getCurrentCreature(), aPoint);
    }

    public void move(final Point aPoint) {
        board.move(turnQueue.getCurrentCreature(), aPoint);
        observerSupport.firePropertyChange(CREATURE_MOVED, null, aPoint);

    }
    List<Node> generateNeigboursList(int x, int y){
        List<Node> list = new ArrayList<>();
        if(y != 0){
            //UP
            list.add(new Node(x,y-1));
        }
        if(y != 9){
            //DOWN
            list.add(new Node(x,y+1));
        }
        if(x != 0){
            //LEFT
            list.add(new Node(x-1,y));
        }
        if(x != 14){
            //RIGHT
            list.add(new Node(x+1,y));
        }
        return list;
    }
    List<Node> generateMovesList(Node startingNode, Node destinationNode, Map<Point, Point> Obstacles) {
        PriorityQueue<Node> openNodes = new PriorityQueue<>();
        ArrayList<Node> explored = new ArrayList<>();

        startingNode.setCost(calculateHeuristic(startingNode,destinationNode) + startingNode.getWeight());
        openNodes.add(startingNode);
        while (!openNodes.isEmpty()){

            Node currentNode = openNodes.poll();

            if(currentNode.equals(destinationNode)){
                return reconstructPath(currentNode);
            }


            List<Node> neighbours = generateNeigboursList(currentNode.getX(), currentNode.getY());
            explored.add(currentNode);

            for(Node neighbour : neighbours){

                if(explored.contains(neighbour)){
                    continue;
                }


                chooseWeight(neighbour);
                int h = calculateHeuristic(neighbour, destinationNode);
                int g = neighbour.getWeight() + currentNode.getCostToReach();
                int f = g + h;


                if(!openNodes.contains(neighbour) && (!explored.contains(neighbour))){
                    neighbour.setCost(f);
                    neighbour.setCostToReach(g);
                    neighbour.setHeuristic(h);
                    neighbour.setParent(currentNode);
                    openNodes.add(neighbour);


                }
                else if(openNodes.contains(neighbour)){
                    Optional<Node> result = openNodes.stream().filter(obj -> obj.equals(neighbour)).findFirst();
                    if(result.isPresent()){
                        Node aNode = result.get();
                        if (aNode.getCost() > f){
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

    void chooseWeight(Node aNode){
        aNode.setWeight(1);
//        if (aNode.getX() == 1 && aNode.getY() == 0){
//            aNode.setWeight(9999);
//        }
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
    int calculateHeuristic(Node currentNode, Node destinationNode){
       int xCost = abs(currentNode.getX() - destinationNode.getX());
       int yCost = abs(currentNode.getY() - destinationNode.getY());
       return xCost + yCost;
    }
    public Optional<Creature> getCreature(final Point aPoint) {
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
        return board.getCreature(point)
                .isPresent()
                && distance < 2 && distance > 0;
    }

    public boolean isCurrentCreature(Point aPoint) {
        return Optional.of(turnQueue.getCurrentCreature()).equals(board.getCreature(aPoint));
    }

    public Point getPosition( Creature aCreature )
    {
       return board.getPosition(aCreature);
    }
}


