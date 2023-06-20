package pl.psi;

import org.junit.jupiter.api.Test;
import pl.psi.creatures.BattleUnit;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;
import pl.psi.creatures.WarMachine;
import pl.psi.warmachines.WarMachineStatistic;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AutonomousUnitControllerTest {

    final BattleUnit aCreature1 = new BattleUnit(new Creature.Builder().statistic( CreatureStats.builder()
                    .build() )
            .build());
    final BattleUnit aWarMachine = new BattleUnit(new WarMachine.Builder().statistic(WarMachineStatistic.FIRST_AID_TENT).controlSkill(0).build());
    final BattleUnit aCreature2 = new BattleUnit(new Creature.Builder().statistic( CreatureStats.builder()
                    .build() )
            .build());


    @Test
    void turnShouldPassWhenWarMachineIsNotControllable(){
        final Hero aHero1 = new Hero(List.of(aCreature1, aWarMachine), Collections.emptyList());
        final Hero aHero2 = new Hero(List.of(aCreature2), Collections.emptyList());

        final TurnQueue turnQueue = new TurnQueue( aHero1.getBattleUnits(), aHero2.getBattleUnits());

        AutonomousUnitController aAutonomousUnitController = new AutonomousUnitController(aHero1, aHero2, turnQueue::next);
        turnQueue.addObserver(aAutonomousUnitController);

        assertThat(turnQueue.getCurrentBattleUnit()).isEqualTo(aCreature1);
        turnQueue.next();
        assertThat(turnQueue.getCurrentBattleUnit()).isEqualTo(aCreature2);

    }
}
