package pl.psi.creatures;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;
import pl.psi.warmachines.WarMachineStatistic;
import pl.psi.warmachines.WarMachineStats;
import pl.psi.warmachines.WarMachineType;

import static org.assertj.core.api.Assertions.assertThat;

public class WarMachineTest {

    @Test
    void builderAndStatisticsShouldReturnValidObject(){
        final WarMachine aWarMachine = new WarMachine.Builder().statistic(WarMachineStats.builder().maxHp(100).attack(0).type(WarMachineType.ATTACK).build()).build();
        assertThat(aWarMachine.getCurrentHp()).isEqualTo(aWarMachine.getStats().getMaxHp());
        assertThat(aWarMachine.getStats().getAttack()).isEqualTo(0);
        assertThat(aWarMachine.getControlSkill()).isEqualTo(0);
    }

    @Test
    void warMachineShouldTakeDamage(){
        final WarMachine aWarMachine = new WarMachine.Builder().statistic(WarMachineStats.builder().maxHp(100).build()).build();
        assertThat(aWarMachine.getCurrentHp()).isEqualTo(aWarMachine.getStats().getMaxHp());
        aWarMachine.applyDamage(10);
        assertThat(aWarMachine.getCurrentHp()).isEqualTo(aWarMachine.getStats().getMaxHp()-10);
    }
    @Test
    void hurtCreatureShouldBeHealed(){
        final WarMachine aFirstAidTent = new WarMachine.Builder().statistic(WarMachineStatistic.FIRST_AID_TENT).build();
        final Creature aCreature1 = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .build())
                .build();
        aCreature1.setCurrentHp(50);
        aFirstAidTent.heal(aCreature1);
        assertThat(aCreature1.getCurrentHp()).isGreaterThan(50);
        assertThat(aCreature1.getCurrentHp()).isLessThan(76);
        //heal with 0 first aid skill should heal between 1 and 25
    }
    @Test
    void creatureShouldBeAbleToAttackWarMachine(){
        final Creature aCreature = new Creature.Builder().statistic(CreatureStats.builder()
                        .damage(Range.closed(10, 10))
                        .attack(50)
                        .build())
                .build();
        final WarMachine aWarMachine = new WarMachine.Builder().statistic(WarMachineStats.builder().maxHp(100).armor(10).build()).build();
        aCreature.attack(aWarMachine);
        assertThat(aWarMachine.getCurrentHp()).isEqualTo(70);
        //checks whether .attack() is compliant with the Creature implementation
    }
    @Test
    void warMachineShouldBeAbleToAttackCreature(){
        final Creature aCreature = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .build())
                .build();
        final WarMachine aWarMachine = new WarMachine.Builder().statistic(WarMachineStats.builder()
                        .maxHp(100) //hp needs to be above 0 due to isAlive() check
                        .build())
                .build();
        aWarMachine.attack(aCreature);
        assertThat(aCreature.getCurrentHp()).isEqualTo(90);
        //TODO: correct this test once the .attack() method is corrected, pending skills implementation
    }

    @Test
    void warMachineShouldBeAbleToAttackWarMachine(){
        final WarMachine aWarMachine1 = new WarMachine.Builder().statistic(WarMachineStats.builder()
                        .maxHp(100) //hp needs to be above 0 due to isAlive() check
                        .build())
                .build();
        final WarMachine aWarMachine2 = new WarMachine.Builder().statistic(WarMachineStats.builder()
                        .maxHp(100) //hp needs to be above 0 due to isAlive() check
                        .build())
                .build();
        aWarMachine1.attack(aWarMachine2);
        assertThat(aWarMachine2.getCurrentHp()).isEqualTo(90);
        //TODO: correct this test once the .attack() method is corrected, pending skills implementation
    }
}
