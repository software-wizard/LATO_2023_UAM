package pl.psi.creatures;

import com.google.common.collect.Range;
import pl.psi.Defendable;

public class MultipleCounterAttacksCreature extends Creature{
    private final Creature decorated;

    public MultipleCounterAttacksCreature(Creature decorated, int numberOfCounterAttacks) {
        this.decorated = decorated;
        this.decorated.setCounterAttackCounter(numberOfCounterAttacks);
    }
    @Override
    public CreatureStatisticIf getStats() {
        return decorated.getStats();
    }

    @Override
    public int getAmount() {
        return decorated.getAmount();
    }

    @Override
    public int getCounterAttackCounter() {
        return decorated.getCounterAttackCounter();
    }

    @Override
    public DamageCalculatorIf getCalculator() {
        return decorated.getCalculator();
    }

    @Override
    public void attack(final Defendable aDefender) {
        decorated.attack(aDefender);
    }


    @Override
    public boolean isAlive() {
        return decorated.isAlive();
    }

    @Override
    public int getCurrentHp() {
        return decorated.getCurrentHp();
    }

    @Override
    public Range<Integer> getDamage() {
        return decorated.getDamage();
    }

    @Override
    public int getAttack() {
        return decorated.getAttack();
    }

    @Override
    public int getArmor() {
        return decorated.getArmor();
    }

    @Override
    protected void restoreCurrentHpToMax() {
        decorated.restoreCurrentHpToMax();
    }

    @Override
    public int getMaxHp() {
        return decorated.getMaxHp();
    }

    @Override
    public void setCurrentHp(int aCurrentHp) {
        decorated.setCurrentHp(aCurrentHp);
    }

    @Override
    public void setAmount(int amount) {
        decorated.setAmount(amount);
    }
}
