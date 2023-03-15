// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.game;

import com.google.common.collect.Range;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Getter
public class Creature
{

    private final int maxHp;
    private final Range< Integer > damage;
    private int currentHp;
    private Random rand;

    private final int attack;
    private final int armor;
    @Setter
    private DamageCalcualteStrategy damageCalculateStrategy;

    public Creature( int aMaxHp, Range< Integer > aDamage )
    {
        this( aMaxHp, aDamage, new Random() );
    }

    public Creature( int aMaxHp, Range< Integer > aDamage, Random aRand )
    {
        this( aMaxHp, aDamage, 0, 0, aRand );
    }

    public Creature( int aMaxHp, Range< Integer > aDamage, int aAttack, int aArmor )
    {
        this( aMaxHp, aDamage, aAttack, aArmor, new Random() );
    }

    public Creature( int aMaxHp, Range< Integer > aDamage, int aAttack, int aArmor, Random aRand )
    {
        maxHp = aMaxHp;
        damage = aDamage;
        currentHp = maxHp;
        attack = aAttack;
        armor = aArmor;
        rand = aRand;
        damageCalculateStrategy = new DamageCalcualteStrategy( rand );
    }

    public void attack( Creature aDefender )
    {
        int randDmg = damageCalculateStrategy.calculateDmmg( this, aDefender );
        aDefender.currentHp = aDefender.getCurrentHp() - randDmg;

        aDefender.attack(this);
    }

}
