package pl.psi.creatures;

import org.junit.jupiter.api.Test;
import pl.psi.warmachines.WarMachineStatistic;

import static org.assertj.core.api.Assertions.assertThat;

public class WarMachineTest {

    @Test
    void builderAndStatisticsShouldReturnValidObject(){
        final WarMachine firstAidTent = new WarMachine.Builder().statistic(WarMachineStatistic.FIRST_AID_TENT).relSkill(0).build();
        assertThat(firstAidTent != null).isTrue();
        assertThat(firstAidTent.getCurrentHp()).isEqualTo(firstAidTent.getStats().getMaxHp());
        assertThat(firstAidTent.getStats().getAttack()).isEqualTo(0);
        assertThat(firstAidTent.getRelevantSkill()).isEqualTo(0);
    }

    @Test
    void hurtCreatureShouldBeHealed(){
        final WarMachine firstAidTent = new WarMachine.Builder().statistic(WarMachineStatistic.FIRST_AID_TENT).relSkill(0).build();
        final Creature aCreature1 = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .build())
                .build();
        aCreature1.setCurrentHp(50);
        firstAidTent.heal(aCreature1);
        assertThat(aCreature1.getCurrentHp()).isGreaterThan(50);
        assertThat(aCreature1.getCurrentHp()).isLessThan(76);
        //heal with 0 first aid skill should heal between 1 and 25
    }

}
