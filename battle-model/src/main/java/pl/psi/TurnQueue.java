package pl.psi;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pl.psi.creatures.BattleUnit;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class TurnQueue {

    public static final String END_OF_TURN = "END_OF_TURN";
    public static final String NEXT_UNIT = "NEXT_UNIT";
    private final Collection<BattleUnit> battleUnits;
    private final Queue<BattleUnit> battleUnitsQueue;
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);
    private BattleUnit currentBattleUnit;
    private int roundNumber;

    public TurnQueue(final Collection<BattleUnit> aBattleUnitList,
                     final Collection<BattleUnit> aBattleUnitList2) {
        battleUnits = Stream.concat(aBattleUnitList.stream(), aBattleUnitList2.stream())
                .collect(Collectors.toList());
        battleUnitsQueue = new LinkedList<>();
        initQueue();
        battleUnits.stream().filter(BattleUnit::isCreature).forEach(unit -> {observerSupport.addPropertyChangeListener(unit.getCreatureVal());});
        next();
    }

    private void initQueue() {
        battleUnitsQueue.addAll(battleUnits);
    }

    public BattleUnit getCurrentBattleUnit() {
        return currentBattleUnit;
    }

    public void next() {
        BattleUnit oldBattleUnit = currentBattleUnit;
        if (battleUnitsQueue.isEmpty()) {
            endOfTurn();
        }
        currentBattleUnit = battleUnitsQueue.poll();
        observerSupport.firePropertyChange(NEXT_UNIT, oldBattleUnit, currentBattleUnit);
    }

    private void endOfTurn() {
        roundNumber++;
        initQueue();
        observerSupport.firePropertyChange(END_OF_TURN, roundNumber - 1, roundNumber);
    }

    void addObserver(PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aObserver);
    }
}
