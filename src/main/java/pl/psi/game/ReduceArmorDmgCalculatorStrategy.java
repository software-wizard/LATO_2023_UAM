// ******************************************************************
//  
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//  
// ******************************************************************

package pl.psi.game;

import java.util.Random;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class ReduceArmorDmgCalculatorStrategy extends DamageCalcualteStrategy
{
    private final double factor;

    public ReduceArmorDmgCalculatorStrategy( double aFactor )
    {
        super( new Random() );
        factor = aFactor;
    }

    @Override
    int getDiff( Creature aAttacker, Creature aDefender )
    {
        return aAttacker.getAttack() - (int)(aDefender.getArmor() * 0.5);
    }
}
