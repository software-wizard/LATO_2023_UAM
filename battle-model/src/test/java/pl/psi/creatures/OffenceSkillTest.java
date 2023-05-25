package pl.psi.creatures;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OffenceSkillTest {

    @Test
    void DamageShouldBeIncreasedBy10Percent(){
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

        int expectedHp = dragon.getCurrentHp() - (int)(30 * 1.1);
        OffenceSkill offenceSkillBasic = new OffenceSkill(OffenceSkill.OffenceEnum.BASIC);
        creatureList.add(angel);

        offenceSkillBasic.apply(creatureList);
        angel.attack(dragon);
        assertThat(dragon.getCurrentHp()).isEqualTo(expectedHp);

    }

    @Test
    void DamageShouldBeIncreasedBy20Percent(){
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

        int expectedHp = dragon.getCurrentHp() - (int)(30 * 1.2);
        OffenceSkill offenceSkill = new OffenceSkill(OffenceSkill.OffenceEnum.ADVANCED);
        creatureList.add(angel);

        offenceSkill.apply(creatureList);
        angel.attack(dragon);
        assertThat(dragon.getCurrentHp()).isEqualTo(expectedHp);

    }

    @Test
    void DamageShouldBeIncreasedBy30Percent(){
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

        int expectedHp = dragon.getCurrentHp() - (int)(30 * 1.3);
        OffenceSkill offenceSkill = new OffenceSkill(OffenceSkill.OffenceEnum.EXPERT);
        creatureList.add(angel);

        offenceSkill.apply(creatureList);
        angel.attack(dragon);
        assertThat(dragon.getCurrentHp()).isEqualTo(expectedHp);

    }

}
