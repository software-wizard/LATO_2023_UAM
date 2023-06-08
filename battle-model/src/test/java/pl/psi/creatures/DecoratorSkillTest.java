//package pl.psi.creatures;
//
//import com.google.common.collect.Range;
//import org.junit.jupiter.api.Test;
//import pl.psi.hero.OffenceSkill;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class DecoratorSkillTest {
//
//    @Test
//    void DamageShouldBeReducedAccordingToListOfSkills(){
//        List<Creature> creatureList = new ArrayList<>();
//        int ATTACK_VALUE_WITHOUT_SKILLS = 30;
//        final Creature angel = new Creature.Builder().statistic(
//                        CreatureStats.builder()
//                                .maxHp(100)
//                                .damage(Range.closed(10, 10))
//                                .attack(50)
//                                .armor(0)
//                                .build())
//                .build();
//        final Creature dragon = new Creature.Builder().statistic(CreatureStats.builder()
//                        .maxHp(100)
//                        .damage(Range.closed(0,0))
//                        .attack(0)
//                        .armor(10)
//                        .build())
//                .build();
//
//        int expectedHp = dragon.getCurrentHp() - (int)((ATTACK_VALUE_WITHOUT_SKILLS * 1.1) * 0.9);
//
//        ArmourSkill armourSkill = new ArmourSkill(SkillEnum.ADVANCED);
//        OffenceSkill offenceSkill = new OffenceSkill(SkillEnum.BASIC);
//
//        creatureList.add(angel);
//
//        offenceSkill.apply(creatureList);
//        armourSkill.apply(creatureList);
//
//        angel.attack(dragon);
//        assertThat(dragon.getCurrentHp()).isEqualTo(expectedHp);
//
//    }
//}
