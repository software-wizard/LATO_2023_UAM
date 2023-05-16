package pl.psi.creatures;

import org.junit.jupiter.api.Test;
import pl.psi.warmachines.WarMachineStats;

import static org.assertj.core.api.Assertions.assertThat;

public class BattleUnitTest {
    @Test
    void unitShouldKnowItsUnderlyingType(){
        final Creature aCreature = new Creature.Builder().statistic(CreatureStats.builder()
                        .build())
                .build();
        final BattleUnit battleUnit1 = new BattleUnit(aCreature);
        assertThat(battleUnit1.isCreature()).isTrue();
        assertThat(battleUnit1.isWarMachine()).isFalse();

        final WarMachine aWarMachine = new WarMachine.Builder().statistic(WarMachineStats.builder().build()).build();
        final BattleUnit battleUnit2 = new BattleUnit(aWarMachine);
        assertThat(battleUnit2.isWarMachine()).isTrue();
        assertThat(battleUnit2.isCreature()).isFalse();
    }
}
