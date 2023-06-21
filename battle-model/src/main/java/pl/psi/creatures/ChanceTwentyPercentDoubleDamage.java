package pl.psi.creatures;

import com.google.common.collect.Range;
import lombok.Getter;
import lombok.Setter;
import pl.psi.Defendable;
import java.util.Random;

public class ChanceTwentyPercentDoubleDamage extends Creature {
    private final Creature decorated;
    @Getter
    @Setter
    private float probability;
    public ChanceTwentyPercentDoubleDamage(Creature decorated, float probability) {
        this.decorated = decorated;
        this.probability = probability;
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
        if (isAlive()) {
            int damage = getCalculator().calculateDamage(this, aDefender);
            double probability = getProbability();
            Random random = new Random();
            double randomValue = random.nextDouble();

            if (randomValue < probability) {
                damage = damage * 2;
            }
            aDefender.applyDamage(damage);
            if (canCounterAttack(aDefender)) {
                aDefender.counterAttack(this);
            }
        }
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
