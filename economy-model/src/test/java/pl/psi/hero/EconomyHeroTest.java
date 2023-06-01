package pl.psi.hero;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pl.psi.creatures.ArmourSkill;
import pl.psi.creatures.EconomyNecropolisFactory;
import pl.psi.creatures.OffenceSkill;
import pl.psi.creatures.SkillEnum;

class EconomyHeroTest
{

    private EconomyHero hero;

    @BeforeEach
    void init()
    {
        hero = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 3000 );
    }

    @Test
    void shouldThrowExceptionWhileHeroHas7CreatureAndYoTryToAddNextOne()
    {
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );

        assertThrows( IllegalStateException.class, () -> hero.addCreature( factory.create( true, 1, 1 ) ) );
    }

    @Test
    void shouldThrowExceptionWhileYouTrySubstractMoreGoldThanHeroHas()
    {
        assertThrows( IllegalStateException.class, () -> hero.substractGold( 3001 ) );
    }

    @Test
    void shouldAddListOfSkillsToHero()
    {
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addSkill(new OffenceSkill(SkillEnum.BASIC));
        hero.addSkill(new ArmourSkill(SkillEnum.ADVANCED));

        assertTrue(hero.getSkills().get(0) instanceof OffenceSkill);
        assertTrue(hero.getSkills().get(1) instanceof ArmourSkill);

    }
}