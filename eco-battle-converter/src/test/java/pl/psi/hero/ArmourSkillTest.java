package pl.psi.hero;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;
import pl.psi.converter.EcoBattleConverter;
import pl.psi.creatures.Creature;
import pl.psi.creatures.EconomyNecropolisFactory;
import pl.psi.creatures.SkillEnum;
import pl.psi.skills.ArmourSkill;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

public class ArmourSkillTest {

    int defenderMaxHp = 15;
    int minDamageWithoutSkills = 1;
    int maxDamageWithoutSkills = 3;

    double ARMOUR_SKILL_BASIC = 0.95;
    double ARMOUR_SKILL_ADVANCED = 0.9;
    double ARMOUR_SKILL_EXPERT = 0.85;

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
    void shouldApplyBasicArmourSkillCorrectly()
    {
        final EconomyHero ecoHero = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 1000 );
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        ecoHero.addCreature( factory.create( false, 1, 1 ) );
        ecoHero.addCreature( factory.create( false, 2, 2 ) );

        ecoHero.addSkill(new ArmourSkill(SkillEnum.BASIC));

        final List<Creature> convertedCreatures = EcoBattleConverter.convert( ecoHero )
                .getCreatures();

        Creature skeleton = convertedCreatures.get(0);
        Creature walking_dead = convertedCreatures.get(1);

        int minPredictedHp  = predictHp((int)(maxDamageWithoutSkills * ARMOUR_SKILL_BASIC), defenderMaxHp);
        int maxPredictedHp = predictHp((int)(minDamageWithoutSkills * ARMOUR_SKILL_BASIC) , defenderMaxHp);

        skeleton.attack(walking_dead);
        Range<Integer> myRange = Range.closed(minPredictedHp, maxPredictedHp);

        assertTrue(myRange.contains(walking_dead.getCurrentHp()));

    }
    @Test
    void shouldApplyAdvancedArmourSkillCorrectly()
    {
        final EconomyHero ecoHero = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 1000 );
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        ecoHero.addCreature( factory.create( false, 1, 1 ) );
        ecoHero.addCreature( factory.create( false, 2, 2 ) );

        ecoHero.addSkill(new ArmourSkill(SkillEnum.ADVANCED));

        final List<Creature> convertedCreatures = EcoBattleConverter.convert( ecoHero )
                .getCreatures();

        Creature skeleton = convertedCreatures.get(0);
        Creature walking_dead = convertedCreatures.get(1);

        int minPredictedHp = predictHp((int)(maxDamageWithoutSkills * ARMOUR_SKILL_ADVANCED), defenderMaxHp);
        int maxPredictedHp = predictHp((int)(minDamageWithoutSkills * ARMOUR_SKILL_ADVANCED) , defenderMaxHp);

        skeleton.attack(walking_dead);
        Range<Integer> myRange = Range.closed(minPredictedHp, maxPredictedHp);

        assertTrue(myRange.contains(walking_dead.getCurrentHp()));

    }
    @Test
    void shouldApplyExpertArmourSkillCorrectly()
    {
        final EconomyHero ecoHero = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 1000 );
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        ecoHero.addCreature( factory.create( false, 1, 1 ) );
        ecoHero.addCreature( factory.create( false, 2, 2 ) );

        ecoHero.addSkill(new ArmourSkill(SkillEnum.EXPERT));

        final List<Creature> convertedCreatures = EcoBattleConverter.convert( ecoHero )
                .getCreatures();

        Creature skeleton = convertedCreatures.get(0);
        Creature walking_dead = convertedCreatures.get(1);

        int minPredictedHp = predictHp((int)(maxDamageWithoutSkills * ARMOUR_SKILL_EXPERT), defenderMaxHp);
        int maxPredictedHp = predictHp((int)(minDamageWithoutSkills * ARMOUR_SKILL_EXPERT) , defenderMaxHp);

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
