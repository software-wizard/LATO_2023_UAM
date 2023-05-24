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

    public Hero( final List< Creature > aCreatures )
    {
        creatures = aCreatures;
    }

    Spell spell1 = new Spell.spellBuilder().statistic(SpellStatistic.ICE_BOLT).build();
    Spell spell2 = new Spell.spellBuilder().statistic(SpellStatistic.CURE).build();

    private final List<String>/*List<Spell>*/ spellBook = new ArrayList</*Spell*/>();

    public List<String>/*List<Spell>*/ heroSpells() {
        spellBook.add(spell1.getName());
        spellBook.add(spell2.getName());
        return spellBook;
    }

}
