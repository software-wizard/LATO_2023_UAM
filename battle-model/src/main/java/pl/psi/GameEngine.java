package pl.psi;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Optional;

import pl.psi.creatures.BattleUnit;

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

    private AutonomousUnitController autonomousUnitController;


    public GameEngine(final Hero aHero1, final Hero aHero2) {
        hero1 = aHero1;
        hero2 = aHero2;
        turnQueue = new TurnQueue(hero1.getBattleUnits(), hero2.getBattleUnits());
        board = new Board(hero1.getBattleUnits(), hero2.getBattleUnits());

        autonomousUnitController = new AutonomousUnitController(aHero1, aHero2, this::pass);
        addObserver(autonomousUnitController);
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
        board.move(turnQueue.getCurrentBattleUnit(), aPoint);
        observerSupport.firePropertyChange(UNIT_MOVED, null, aPoint);
    }

    public Optional<BattleUnit> getBattleUnit(final Point aPoint) {
        return board.getBattleUnit(aPoint);
    }

    public void pass() {
        turnQueue.next();
    }

    public void addObserver(final PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aObserver);
        turnQueue.addObserver(aObserver);
    }

    public boolean canAttack(final Point point) {
        if (board.getBattleUnit(point).isPresent()) {
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

    public boolean isCurrentBattleUnit(Point aPoint) {
        return Optional.of(turnQueue.getCurrentBattleUnit()).equals(board.getBattleUnit(aPoint));
    }
}
