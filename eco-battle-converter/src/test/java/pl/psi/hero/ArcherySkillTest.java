package pl.psi.hero;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;
import pl.psi.converter.EcoBattleConverter;
import pl.psi.creatures.Creature;
import pl.psi.creatures.EconomyNecropolisFactory;
import pl.psi.skills.SkillEnum;
import pl.psi.skills.ArcherySkill;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArcherySkillTest {
    int defenderMaxHp = 15;
    int minDamageWithoutSkills = 1;
    int maxDamageWithoutSkills = 3;

    double ARCHERY_SKILL_BASIC = 1.1;
    double ARCHERY_SKILL_ADVANCED = 1.2;
    double ARCHERY_SKILL_EXPERT = 1.3;

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
    void shouldApplyBasicArcherySkillCorrectly()
    {
        final EconomyHero ecoHero = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 1000 );
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        ecoHero.addCreature( factory.create( false, 1, 1 ) );
        ecoHero.addCreature( factory.create( false, 2, 2 ) );

        ecoHero.addSkill(new ArcherySkill(SkillEnum.BASIC));

        final List<Creature> convertedCreatures = EcoBattleConverter.convert( ecoHero )
                .getCreatures();

        Creature skeleton = convertedCreatures.get(0);
        Creature walking_dead = convertedCreatures.get(1);

        int minPredictedHp  = predictHp((int)(maxDamageWithoutSkills * ARCHERY_SKILL_BASIC), defenderMaxHp);
        int maxPredictedHp = predictHp((int)(minDamageWithoutSkills * ARCHERY_SKILL_BASIC) , defenderMaxHp);

        skeleton.attack(walking_dead);
        Range<Integer> myRange = Range.closed(minPredictedHp, maxPredictedHp);

        assertTrue(myRange.contains(walking_dead.getCurrentHp()));

    }
    @Test
    void shouldApplyAdvancedArcherySkillCorrectly()
    {
        final EconomyHero ecoHero = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 1000 );
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        ecoHero.addCreature( factory.create( false, 1, 1 ) );
        ecoHero.addCreature( factory.create( false, 2, 2 ) );

        ecoHero.addSkill(new ArcherySkill(SkillEnum.ADVANCED));

        final List<Creature> convertedCreatures = EcoBattleConverter.convert( ecoHero )
                .getCreatures();

        Creature skeleton = convertedCreatures.get(0);
        Creature walking_dead = convertedCreatures.get(1);

        int minPredictedHp = predictHp((int)(maxDamageWithoutSkills * ARCHERY_SKILL_ADVANCED), defenderMaxHp);
        int maxPredictedHp = predictHp((int)(minDamageWithoutSkills * ARCHERY_SKILL_ADVANCED) , defenderMaxHp);

        skeleton.attack(walking_dead);
        Range<Integer> myRange = Range.closed(minPredictedHp, maxPredictedHp);

        assertTrue(myRange.contains(walking_dead.getCurrentHp()));

    }
    @Test
    void shouldApplyExpertArcherySkillCorrectly()
    {
        final EconomyHero ecoHero = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 1000 );
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        ecoHero.addCreature( factory.create( false, 1, 1 ) );
        ecoHero.addCreature( factory.create( false, 2, 2 ) );

        ecoHero.addSkill(new ArcherySkill(SkillEnum.EXPERT));

        final List<Creature> convertedCreatures = EcoBattleConverter.convert( ecoHero )
                .getCreatures();

        Creature skeleton = convertedCreatures.get(0);
        Creature walking_dead = convertedCreatures.get(1);

        int minPredictedHp = predictHp((int)(maxDamageWithoutSkills * ARCHERY_SKILL_EXPERT), defenderMaxHp);
        int maxPredictedHp = predictHp((int)(minDamageWithoutSkills * ARCHERY_SKILL_EXPERT) , defenderMaxHp);

        skeleton.attack(walking_dead);
        Range<Integer> myRange = Range.closed(minPredictedHp, maxPredictedHp);

        assertTrue(myRange.contains(walking_dead.getCurrentHp()));

    }
}
