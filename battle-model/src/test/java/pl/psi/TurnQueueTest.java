package pl.psi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import pl.psi.creatures.BattleUnit;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

class TurnQueueTest
{
    @Test
    void shouldAddPawnsCorrectly()
    {
        final BattleUnit battleUnit1 = new BattleUnit(new Creature.Builder().statistic( CreatureStats.builder()
            .build() )
            .build());
        final BattleUnit battleUnit2 = new BattleUnit(new Creature.Builder().statistic( CreatureStats.builder()
            .build() )
            .build());
        final BattleUnit battleUnit3 = new BattleUnit(new Creature.Builder().statistic( CreatureStats.builder()
            .build() )
            .build());
        final TurnQueue turnQueue = new TurnQueue( List.of(battleUnit1, battleUnit2), List.of(battleUnit3) );

        assertEquals( turnQueue.getCurrentBattleUnit(), battleUnit1);
        turnQueue.next();
        assertEquals( turnQueue.getCurrentBattleUnit(), battleUnit2);
        turnQueue.next();
        assertEquals( turnQueue.getCurrentBattleUnit(), battleUnit3);
        turnQueue.next();
        assertEquals( turnQueue.getCurrentBattleUnit(), battleUnit1);
    }
}