package pl.psi;

import java.util.List;

import pl.psi.creatures.BattleUnit;

import lombok.Getter;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class Hero
{
    @Getter
    private final List<BattleUnit> battleUnits;

    public Hero( final List< BattleUnit > aBattleUnits )
    {
        battleUnits = aBattleUnits;
    }

    public boolean isAlly(BattleUnit aBattleUnit1, BattleUnit aBattleUnit2){
        return battleUnits.contains(aBattleUnit1) == battleUnits.contains(aBattleUnit2);
    }
}
