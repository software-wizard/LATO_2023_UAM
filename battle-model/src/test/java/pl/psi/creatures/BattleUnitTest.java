package pl.psi.creatures;

import org.junit.jupiter.api.Test;
import pl.psi.warmachines.WarMachineStats;

import static org.assertj.core.api.Assertions.assertThat;

public class BattleUnitTest {
    @Test
    void getTypeShouldReturnRightType(){
        final Creature aCreature = new Creature.Builder().statistic(CreatureStats.builder()
                        .build())
                .build();
        final BattleUnit battleUnit1 = new BattleUnit(aCreature);
        assertThat(battleUnit1.getType()).isEqualTo(Creature.class);

        final WarMachine aWarMachine = new WarMachine.Builder().statistic(WarMachineStats.builder().build()).build();
        final BattleUnit battleUnit2 = new BattleUnit(aWarMachine);
        assertThat(battleUnit2.getType()).isEqualTo(WarMachine.class);
    }
}
