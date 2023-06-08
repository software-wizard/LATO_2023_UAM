package pl.psi.hero;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;
import pl.psi.converter.EcoBattleConverter;
import pl.psi.creatures.Creature;
import pl.psi.creatures.EconomyNecropolisFactory;
import pl.psi.skills.SkillEnum;
import pl.psi.skills.OffenceSkill;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OffenceSkillTest {

    int defenderMaxHp = 15;
    int minDamageWithoutSkills = 1;
    int maxDamageWithoutSkills = 3;

    double OFFENCE_SKILL_BASIC = 1.1;
    double OFFENCE_SKILL_ADVANCED = 1.2;
    double OFFENCE_SKILL_EXPERT = 1.3;

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
    void shouldApplyBasicOffenceSkillCorrectly()
    {
        final EconomyHero ecoHero = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 1000 );
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        ecoHero.addCreature( factory.create( false, 1, 1 ) );
        ecoHero.addCreature( factory.create( false, 2, 2 ) );

        ecoHero.addSkill(new OffenceSkill(SkillEnum.BASIC));

        final List<Creature> convertedCreatures = EcoBattleConverter.convert( ecoHero )
                .getCreatures();

        Creature skeleton = convertedCreatures.get(0);
        Creature walking_dead = convertedCreatures.get(1);

        int minPredictedHp  = predictHp((int)(maxDamageWithoutSkills * OFFENCE_SKILL_BASIC), defenderMaxHp);
        int maxPredictedHp = predictHp((int)(minDamageWithoutSkills * OFFENCE_SKILL_BASIC) , defenderMaxHp);

        skeleton.attack(walking_dead);
        Range<Integer> myRange = Range.closed(minPredictedHp, maxPredictedHp);

        assertTrue(myRange.contains(walking_dead.getCurrentHp()));

    }
    @Test
    void shouldApplyAdvancedOffenceSkillCorrectly()
    {
        final EconomyHero ecoHero = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 1000 );
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        ecoHero.addCreature( factory.create( false, 1, 1 ) );
        ecoHero.addCreature( factory.create( false, 2, 2 ) );

        ecoHero.addSkill(new OffenceSkill(SkillEnum.ADVANCED));

        final List<Creature> convertedCreatures = EcoBattleConverter.convert( ecoHero )
                .getCreatures();

        Creature skeleton = convertedCreatures.get(0);
        Creature walking_dead = convertedCreatures.get(1);

        int minPredictedHp = predictHp((int)(maxDamageWithoutSkills * OFFENCE_SKILL_ADVANCED), defenderMaxHp);
        int maxPredictedHp = predictHp((int)(minDamageWithoutSkills * OFFENCE_SKILL_ADVANCED) , defenderMaxHp);

        skeleton.attack(walking_dead);
        Range<Integer> myRange = Range.closed(minPredictedHp, maxPredictedHp);

        assertTrue(myRange.contains(walking_dead.getCurrentHp()));

    }
    @Test
    void shouldApplyExpertOffenceSkillCorrectly()
    {
        final EconomyHero ecoHero = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 1000 );
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        ecoHero.addCreature( factory.create( false, 1, 1 ) );
        ecoHero.addCreature( factory.create( false, 2, 2 ) );

        ecoHero.addSkill(new OffenceSkill(SkillEnum.EXPERT));

        final List<Creature> convertedCreatures = EcoBattleConverter.convert( ecoHero )
                .getCreatures();

        Creature skeleton = convertedCreatures.get(0);
        Creature walking_dead = convertedCreatures.get(1);

        int minPredictedHp = predictHp((int)(maxDamageWithoutSkills * OFFENCE_SKILL_EXPERT), defenderMaxHp);
        int maxPredictedHp = predictHp((int)(minDamageWithoutSkills * OFFENCE_SKILL_EXPERT) , defenderMaxHp);

        skeleton.attack(walking_dead);
        Range<Integer> myRange = Range.closed(minPredictedHp, maxPredictedHp);

        assertTrue(myRange.contains(walking_dead.getCurrentHp()));

    }
}

//    SKELETON( "Skeleton", 5, 4, 6, 4,Range.closed( 1, 3 ), 1, new SpellProtection.spellProtectionBuilder()
//            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
//        "Average lvl1 foot soldier, but always in huge numbers thanks to necromancy skill and skeleton transformer.", false ), //
//    WALKING_DEAD( "Walking Dead", 5, 5, 15, 3, Range.closed( 2, 3 ), 2,  new SpellProtection.spellProtectionBuilder()
//            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
