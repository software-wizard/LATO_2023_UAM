package pl.psi.hero;

import org.junit.jupiter.api.Test;
import pl.psi.converter.EcoBattleConverter;
import pl.psi.creatures.Creature;
import pl.psi.creatures.EconomyNecropolisFactory;
import pl.psi.skills.ResistanceSkill;
import pl.psi.skills.SkillEnum;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class ResistanceSkillTest {

    int RESISTANCE_SKILL_BASIC = 5;
    int RESISTANCE_SKILL_ADVANCED = 10;
    int RESISTANCE_SKILL_EXPERT= 20;

    @Test
    void shouldApplyBasicResistanceSkillCorrectly(){
        final EconomyHero ecoHero = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 1000 );
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        ecoHero.addCreature( factory.create( false, 1, 1 ) );
        ecoHero.addCreature( factory.create( false, 2, 2 ) );

        ecoHero.addSkill(new ResistanceSkill(SkillEnum.BASIC));

        final List<Creature> convertedCreatures = EcoBattleConverter.convert( ecoHero )
                .getCreatures();

        Creature skeleton = convertedCreatures.get(0);
        Creature walking_dead = convertedCreatures.get(1);

        assertThat(skeleton.getPercentOfSpellResistance()).isEqualTo(RESISTANCE_SKILL_BASIC);
        assertThat(walking_dead.getPercentOfSpellResistance()).isEqualTo(RESISTANCE_SKILL_BASIC);
    }

    @Test
    void shouldApplyAdvancedResistanceSkillCorrectly(){
        final EconomyHero ecoHero = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 1000 );
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        ecoHero.addCreature( factory.create( false, 1, 1 ) );
        ecoHero.addCreature( factory.create( false, 2, 2 ) );

        ecoHero.addSkill(new ResistanceSkill(SkillEnum.ADVANCED));

        final List<Creature> convertedCreatures = EcoBattleConverter.convert( ecoHero )
                .getCreatures();

        Creature skeleton = convertedCreatures.get(0);
        Creature walking_dead = convertedCreatures.get(1);

        assertThat(skeleton.getPercentOfSpellResistance()).isEqualTo(RESISTANCE_SKILL_ADVANCED);
        assertThat(walking_dead.getPercentOfSpellResistance()).isEqualTo(RESISTANCE_SKILL_ADVANCED);
    }

    @Test
    void shouldApplyExpertResistanceSkillCorrectly(){
        final EconomyHero ecoHero = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 1000 );
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        ecoHero.addCreature( factory.create( false, 1, 1 ) );
        ecoHero.addCreature( factory.create( false, 2, 2 ) );

        ecoHero.addSkill(new ResistanceSkill(SkillEnum.EXPERT));

        final List<Creature> convertedCreatures = EcoBattleConverter.convert( ecoHero )
                .getCreatures();

        Creature skeleton = convertedCreatures.get(0);
        Creature walking_dead = convertedCreatures.get(1);

        assertThat(skeleton.getPercentOfSpellResistance()).isEqualTo(RESISTANCE_SKILL_EXPERT);
        assertThat(walking_dead.getPercentOfSpellResistance()).isEqualTo(RESISTANCE_SKILL_EXPERT);
    }
}
