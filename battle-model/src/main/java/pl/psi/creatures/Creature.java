package pl.psi.creatures;//  ******************************************************************

//
//  Copyright 2022 PSI Software AG. All rights reserved.
//  PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
//  ******************************************************************

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import lombok.Setter;
import pl.psi.Defendable;
import pl.psi.TurnQueue;

import com.google.common.collect.Range;

import lombok.Getter;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Getter
public class Creature implements PropertyChangeListener, Defendable {
    private CreatureStatisticIf stats;
    @Setter
    private int amount;
    private int currentHp;
    private int counterAttackCounter = 1;
    private DamageCalculatorIf calculator;

    Creature() {}

    Creature(final CreatureStatisticIf aStats, final DamageCalculatorIf aCalculator,
             final int aAmount) {
        stats = aStats;
        amount = aAmount;
        currentHp = stats.getMaxHp();
        calculator = aCalculator;
    }
    @Override
    public void attack(final Defendable aDefender) {
        if (isAlive()) {
            final int damage = getCalculator().calculateDamage(this, aDefender);
            aDefender.applyDamage(damage);
            if (canCounterAttack(aDefender)) {
                aDefender.counterAttack(this);
            }
        }
    }

    protected boolean isAlive() {
        return getAmount() > 0;
    }
    @Override
    public void applyDamage(final int aDamage) {
        int hpToSubstract = aDamage % this.getMaxHp();
        int amountToSubstract = Math.round(aDamage / this.getMaxHp());

        int hp = this.getCurrentHp() - hpToSubstract;
        if (hp <= 0) {
            this.setCurrentHp(this.getMaxHp() - hp);
            this.setAmount(this.getAmount() - 1);
        }
        else{
            this.setCurrentHp(hp);
        }
        this.setAmount(this.getAmount() - amountToSubstract);
    }

    @Override
    public int getMaxHp() {
        return stats.getMaxHp();
    }

    void updateStats(CreatureStatisticIf stats){
        this.stats = this.stats.plus(stats);
    }

    protected CreatureStatisticIf getStats() {
        return stats;
    }

    public void setCurrentHp(final int aCurrentHp) {
        currentHp = aCurrentHp;
    }

    public boolean canCounterAttack(final Defendable aDefender) {
        return aDefender.getCounterAttackCounter() > 0 && aDefender.getCurrentHp() > 0;
    }

    @Override
    public void counterAttack(final Creature aAttacker) {
        final int damage = aAttacker.getCalculator()
                .calculateDamage(aAttacker, this);
        aAttacker.applyDamage(damage);
        aAttacker.lowerCounter();
    }

    protected Range<Integer> getDamage() {
        return stats.getDamage();
    }

    protected int getAttack() {
        return stats.getAttack();
    }
    @Override
    public int getArmor() {
        return stats.getArmor();
    }

    SpellProtection getSpellDamageProtection() {
        return stats.getSpellDamageProtection();
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if (TurnQueue.END_OF_TURN.equals(evt.getPropertyName())) {
            counterAttackCounter = 1;
        }
    }

    protected void restoreCurrentHpToMax() {
        currentHp = stats.getMaxHp();
    }

    public String getName() {
        return stats.getName();
    }

    public int getMoveRange() {
        return stats.getMoveRange();
    }

    private void lowerCounter() {
        counterAttackCounter--;
    }

    public void setDamageCalculator(DamageCalculatorIf calculator) {
        this.calculator = calculator;
    }

    public DamageCalculatorIf getDamageCalculator() { return calculator; }

    @Override
    public boolean isTransparent() {
        return false;
    }

    public static class Builder {
        private int amount = 1;
        private DamageCalculatorIf calculator = new DefaultDamageCalculator(new Random());
        private CreatureStatisticIf statistic;

        public Builder statistic(final CreatureStatisticIf aStatistic) {
            statistic = aStatistic;
            return this;
        }

        public Builder amount(final int aAmount) {
            amount = aAmount;
            return this;
        }

        Builder calculator(final DamageCalculatorIf aCalc) {
            calculator = aCalc;
            return this;
        }

        public Creature build() {
            return new Creature(statistic, calculator, amount);
        }
    }

    @Override
    public String toString() {
        return getName() + System.lineSeparator() + getAmount();
    }
}
