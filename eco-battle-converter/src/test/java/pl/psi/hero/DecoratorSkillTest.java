package pl.psi.hero;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;
import pl.psi.converter.EcoBattleConverter;
import pl.psi.creatures.Creature;
import pl.psi.creatures.EconomyNecropolisFactory;
import pl.psi.creatures.SkillEnum;
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
        ecoHero.addSkill(new OffenceSkill(SkillEnum.BASIC));

        final List<Creature> convertedCreatures = EcoBattleConverter.convert( ecoHero )
                .getCreatures();

        Creature skeleton = convertedCreatures.get(0);
        Creature walking_dead = convertedCreatures.get(1);

        int minPredictedHp  = predictHp((int)(maxDamageWithoutSkills * ARMOUR_SKILL_BASIC * OFFENCE_SKILL_ADVANCED), defenderMaxHp);
        int maxPredictedHp = predictHp((int)(minDamageWithoutSkills * ARMOUR_SKILL_BASIC * OFFENCE_SKILL_ADVANCED) , defenderMaxHp);

        skeleton.attack(walking_dead);
        Range<Integer> myRange = Range.closed(minPredictedHp, maxPredictedHp);

        assertTrue(myRange.contains(walking_dead.getCurrentHp()));

    }
}
