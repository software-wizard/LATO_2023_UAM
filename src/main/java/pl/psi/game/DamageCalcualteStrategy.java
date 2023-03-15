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
public class DamageCalcualteStrategy
{
    private Random rand;

    public DamageCalcualteStrategy(Random aRand) {
        rand = aRand;
    }

    int calculateDmmg(Creature aAttacker, Creature aDefender )
    {
        int diff = getDiff(aAttacker, aDefender);
        double factor;
        if( diff > 0 )
        {
            factor = 0.05 * diff + 1;
        }
        else
        {
            factor = -0.025 * diff + 1;
        }

        int randDmg = (int)(factor * ((rand.nextDouble() * (aAttacker.getDamage()
            .upperEndpoint()
            - aAttacker.getDamage()
                .lowerEndpoint()))
            + aAttacker.getDamage()
                .lowerEndpoint()));
        return randDmg;
    }

    int getDiff(Creature aAttacker, Creature aDefender) {
        return aAttacker.getAttack() - aDefender.getArmor();
    }
}
