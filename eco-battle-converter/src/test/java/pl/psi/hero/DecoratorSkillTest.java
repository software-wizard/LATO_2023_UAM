package pl.psi.hero;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;
import pl.psi.converter.EcoBattleConverter;
import pl.psi.creatures.Creature;
import pl.psi.creatures.EconomyNecropolisFactory;
import pl.psi.skills.SkillEnum;
import pl.psi.skills.ArcherySkill;
import pl.psi.skills.ArmourSkill;
import pl.psi.skills.OffenceSkill;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DecoratorSkillTest {
    int defenderMaxHp = 15;
    int minDamageWithoutSkills = 1;
    int maxDamageWithoutSkills = 3;

    double ARMOUR_SKILL_BASIC = 0.95;
    double OFFENCE_SKILL_ADVANCED = 1.2;
    double ARCHERY_SKILL_EXPERT = 1.5;

    int predictHp(final int aDamage, int maxHp) {
        int hpToSubtract = aDamage % maxHp;
        int hp = maxHp - hpToSubtract;
        if (hp <= 0) {
            return maxHp - hp;
        }
        else{
            return hp;
        }
    }
    @Test
    void shouldApplyOffenceAndArmourSkillCorrectly()
    {
        final EconomyHero ecoHero = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 1000 );
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        ecoHero.addCreature( factory.create( false, 1, 1 ) );
        ecoHero.addCreature( factory.create( false, 2, 2 ) );

        ecoHero.addSkill(new ArmourSkill(SkillEnum.BASIC));
        ecoHero.addSkill(new OffenceSkill(SkillEnum.ADVANCED));

        final List<Creature> convertedCreatures = EcoBattleConverter.convert( ecoHero )
                .getCreatures();

        Creature skeleton = convertedCreatures.get(0);
        Creature walking_dead = convertedCreatures.get(1);


        int minPredictedHp1  = predictHp((int)(maxDamageWithoutSkills * ARMOUR_SKILL_BASIC), defenderMaxHp);
        int maxPredictedHp1 = predictHp((int)(minDamageWithoutSkills * ARMOUR_SKILL_BASIC) , defenderMaxHp);

        int maxPredictedHp2 = predictHp((int)(minPredictedHp1 * OFFENCE_SKILL_ADVANCED), defenderMaxHp);
        int minPredictedHp2 = predictHp((int)(maxPredictedHp1 * OFFENCE_SKILL_ADVANCED), defenderMaxHp);

        skeleton.attack(walking_dead);
        Range<Integer> myRange = Range.closed(minPredictedHp2, maxPredictedHp2);

        assertTrue(myRange.contains(walking_dead.getCurrentHp()));

    }
    @Test
    void shouldApplyOffenceAndArmourAndArcherySkillCorrectly()
    {
        final EconomyHero ecoHero = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 1000 );
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        ecoHero.addCreature( factory.create( false, 1, 1 ) );
        ecoHero.addCreature( factory.create( false, 2, 2 ) );

        ecoHero.addSkill(new ArcherySkill(SkillEnum.EXPERT));
        ecoHero.addSkill(new OffenceSkill(SkillEnum.ADVANCED));

        final List<Creature> convertedCreatures = EcoBattleConverter.convert( ecoHero )
                .getCreatures();

        Creature skeleton = convertedCreatures.get(0);
        Creature walking_dead = convertedCreatures.get(1);

        int minPredictedHp1  = predictHp((int)(maxDamageWithoutSkills * ARCHERY_SKILL_EXPERT), defenderMaxHp);
        int maxPredictedHp1 = predictHp((int)(minDamageWithoutSkills * ARCHERY_SKILL_EXPERT) , defenderMaxHp);

        int maxPredictedHp2 = predictHp((int)(minPredictedHp1 * OFFENCE_SKILL_ADVANCED), defenderMaxHp);
        int minPredictedHp2 = predictHp((int)(maxPredictedHp1 * OFFENCE_SKILL_ADVANCED), defenderMaxHp);

        skeleton.attack(walking_dead);
        Range<Integer> myRange = Range.closed(maxPredictedHp2, minPredictedHp2);
        assertTrue(myRange.contains(walking_dead.getCurrentHp()));

    }
}
