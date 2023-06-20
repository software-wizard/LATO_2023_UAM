package pl.psi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import pl.psi.creatures.BattleUnit;

import lombok.Getter;
import pl.psi.creatures.Spell;
import pl.psi.creatures.SpellStatistic;
import pl.psi.creatures.Creature;
import pl.psi.creatures.WarMachine;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class Hero
{
    @Getter
    private final List<BattleUnit> battleUnits;
    @Getter
    private final List< Spell > spellBook;

    public Hero( final List< BattleUnit > aBattleUnits, final List<Spell> aSpellBook )
    {
        battleUnits = aBattleUnits;
        spellBook = aSpellBook;
    }
    public boolean isAlly(BattleUnit aBattleUnit1, BattleUnit aBattleUnit2){
        return battleUnits.contains(aBattleUnit1) == battleUnits.contains(aBattleUnit2);
    }

    public List<Creature> getCreatures(){
        return battleUnits.stream().filter(BattleUnit::isCreature).map(BattleUnit::getCreatureVal).collect(Collectors.toList());
    }

    public List <WarMachine> getWarMachines(){
        return battleUnits.stream().filter(BattleUnit::isWarMachine).map(BattleUnit::getWarMachineVal).collect(Collectors.toList());
    }

}
