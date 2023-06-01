package pl.psi.creatures;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ArmourSkillTest {

    @Test
    void DamageShouldBeReducedBy5Percent(){
        List<Creature> creatureList = new ArrayList<>();
        final Creature angel = new Creature.Builder().statistic(
                        CreatureStats.builder()
                                .maxHp(100)
                                .damage(Range.closed(10, 10))
                                .attack(50)
                                .armor(0)
                                .build())
                .build();
        final Creature dragon = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(0,0))
                        .attack(0)
                        .armor(10)
                        .build())
                .build();

        int expectedHp = dragon.getCurrentHp() - (int)(30 * 0.95);  //30 - bo tyle wynosi atak bez skillów
        ArmourSkill armourSkill = new ArmourSkill(SkillEnum.BASIC);
        creatureList.add(angel);

        armourSkill.apply(creatureList);
        angel.attack(dragon);
        assertThat(dragon.getCurrentHp()).isEqualTo(expectedHp);

    }

    @Test
    void DamageShouldBeReducedBy10Percent(){
        List<Creature> creatureList = new ArrayList<>();
        final Creature angel = new Creature.Builder().statistic(
                        CreatureStats.builder()
                                .maxHp(100)
                                .damage(Range.closed(10, 10))
                                .attack(50)
                                .armor(0)
                                .build())
                .build();
        final Creature dragon = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(0,0))
                        .attack(0)
                        .armor(10)
                        .build())
                .build();

        int expectedHp = dragon.getCurrentHp() - (int)(30 * 0.9);
        ArmourSkill armourSkill = new ArmourSkill(SkillEnum.ADVANCED);
        creatureList.add(angel);

        armourSkill.apply(creatureList);
        angel.attack(dragon);
        assertThat(dragon.getCurrentHp()).isEqualTo(expectedHp);

    }

    @Test
    void DamageShouldBeReducedBy15Percent(){
        List<Creature> creatureList = new ArrayList<>();
        final Creature angel = new Creature.Builder().statistic(
                        CreatureStats.builder()
                                .maxHp(100)
                                .damage(Range.closed(10, 10))
                                .attack(50)
                                .armor(0)
                                .build())
                .build();
        final Creature dragon = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(0,0))
                        .attack(0)
                        .armor(10)
                        .build())
                .build();

        int expectedHp = dragon.getCurrentHp() - (int)(30 * 0.85);
        ArmourSkill armourSkill = new ArmourSkill(SkillEnum.EXPERT);
        creatureList.add(angel);

        armourSkill.apply(creatureList);
        angel.attack(dragon);
        assertThat(dragon.getCurrentHp()).isEqualTo(expectedHp);

    }
}
