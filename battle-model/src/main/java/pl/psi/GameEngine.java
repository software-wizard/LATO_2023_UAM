package pl.psi;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

import pl.psi.creatures.Creature;

import static java.lang.Thread.sleep;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class GameEngine {

    public static final String CREATURE_MOVED = "CREATURE_MOVED";
    private final TurnQueue turnQueue;
    private final Board board;
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);

    private final Map<Point,List<Point>> graph = generateGraph();

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
//        Point startingPosition = board.getPosition(turnQueue.getCurrentCreature());
////        Point point = new Point(aPoint.getX()-1,aPoint.getY());
//        List<Point> movesList = generateMovesList(startingPosition, aPoint);
////        board.move(turnQueue.getCurrentCreature(), point);
////        observerSupport.firePropertyChange(CREATURE_MOVED, null, point);

        board.move(turnQueue.getCurrentCreature(), aPoint);
        observerSupport.firePropertyChange(CREATURE_MOVED, null, aPoint);

    }
    public List<Point> generateNeigboursList(Point aPoint){
        List<Point> list = new ArrayList<>();
        if(aPoint.getY() != 0){
            //UP
            list.add(new Point(aPoint.getX(),aPoint.getY()+1));
        }
        if(aPoint.getY() != 9){
            //DOWN
            list.add(new Point(aPoint.getX(),aPoint.getY()-1));
        }
        if(aPoint.getX() != 0){
            //LEFT
            list.add(new Point(aPoint.getX()-1,aPoint.getY()));
        }
        if(aPoint.getX() != 14){
            //RIGHT
            list.add(new Point(aPoint.getX()+1,aPoint.getY()));
        }
        return list;
    }
//    private int[] generateUpNeighbour(Point aPoint){
//        if(aPoint.getY() != 0){
//            return new int[] {number1, number2}
//        }
//        return
//    }

    public Map<Point,List<Point>> generateGraph(){
        Map<Point,List<Point>> graph = new HashMap<>();
        for( int x = 0; x < 15; x++ )
        {
            for( int y = 0; y < 10; y++ )
            {
                Point currentPoint = new Point( x, y );
                graph.put(currentPoint, generateNeigboursList(currentPoint));
            }
        }
        return graph;
    }
    private List<Point> generateMovesList(Point startingPosition, Point aPoint) {
        List<Point> movesList = new ArrayList<>();



        return  movesList;
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
