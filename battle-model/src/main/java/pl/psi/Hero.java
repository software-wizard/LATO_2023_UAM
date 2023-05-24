package pl.psi;

import java.util.ArrayList;
import java.util.List;

import pl.psi.creatures.Creature;

import lombok.Getter;
import pl.psi.creatures.Spell;
import pl.psi.creatures.SpellStatistic;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class Hero
{
    @Getter
    private final List< Creature > creatures;
    @Getter
    private final List< Spell > spellBook;

    public Hero( final List< Creature > aCreatures, final List<Spell> aSpellBook )
    {
        creatures = aCreatures;
        spellBook = aSpellBook;
    }
}
