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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Getter
public class Creature implements PropertyChangeListener
{

    private int currentHp;
    private Random rand;

    private CreatureStats stats;

    @Setter
    private DamageCalcualteStrategy damageCalculateStrategy;

    public Creature( CreatureStats aStats )
    {
        stats = aStats;
        damageCalculateStrategy = new DamageCalcualteStrategy( rand );
        currentHp = stats.getMaxHp();
    }

    public void attack( Creature aDefender )
    {
        applyDamage(aDefender);

//        aDefender.applyDamage( this );
    }

    private void applyDamage(Creature aDefender) {
        int randDmg = damageCalculateStrategy.calculateDmmg( this, aDefender);
        aDefender.currentHp = aDefender.getCurrentHp() - randDmg;
    }

    public Range< Integer > getDamage()
    {
        return stats.getDamage();
    }

    public int getAttack()
    {
        return stats.getAttack();
    }

    public int getArmor()
    {
        return stats.getArmor();
    }

    @Override
    public String toString()
    {
        return stats.getName();
    }

    public int getMoveRange() {
        return stats.getMoveRange();
    }

    private void onEndOfTurn() {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(TurnQueue.END_OF_TURN)){
            onEndOfTurn();
        }
    }
}
