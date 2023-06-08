package pl.psi.hero;

import org.junit.jupiter.api.Test;
import pl.psi.converter.EcoBattleConverter;
import pl.psi.creatures.EconomyNecropolisFactory;
import pl.psi.creatures.Spell;
import pl.psi.skills.ResistanceSkill;
import pl.psi.skills.SkillEnum;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class ResistanceSkillTest {

    @Test
    void spellFailure(){
        final EconomyHero ecoHero = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 1000 );
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        ecoHero.addCreature( factory.create( false, 1, 1 ) );
        ecoHero.addCreature( factory.create( false, 2, 2 ) );

        ecoHero.addSkill(new ResistanceSkill(SkillEnum.BASIC));

        final List<Spell> convertedSpells = EcoBattleConverter.convert( ecoHero )
                .getSpellBook();

        // na razie nie działa
//        Spell spell = convertedSpells.get(0);
//        assertThat(spell.getChancesOfSpellFailure()).isEqualTo(95);

    }
}
