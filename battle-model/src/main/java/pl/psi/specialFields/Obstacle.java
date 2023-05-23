package pl.psi.specialFields;

import lombok.Getter;
import lombok.Setter;
import pl.psi.Defendable;
import pl.psi.creatures.Creature;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
@Getter
public class Obstacle implements PropertyChangeListener, Defendable {
    private ObstacleStatisticIf stats;
    @Setter
    private int amount;
    private int currentHp;

    Obstacle() {}

    private Obstacle(final ObstacleStatisticIf aStats, final int aAmount){
        stats = aStats;
        amount = aAmount;
        currentHp = stats.getMaxHp();
    }
    public int getMaxHp() {
        return stats.getMaxHp();
    }

    @Override
    public void setCurrentHp(final int aCurrentHp) {
        currentHp = aCurrentHp;
    }

    @Override
    public void counterAttack(Creature aDefender) {
        //they cannot attack
    }

    @Override
    public int getArmor() {
        return 0;
    }

    @Override
    public int getCounterAttackCounter() {
        return 0;
    }

    public String getName() {
        return stats.getName();
    }
    @Override
    public void applyDamage(int dmg){
        currentHp--;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {}
    @Override
    public void attack(Defendable aDefender){
        //they do not attack
    }

    public static class Builder {
        private int amount = 1;
        private ObstacleStatisticIf statistic;
        
        public Builder statistic(final ObstacleStatisticIf aStatistic){
            statistic = aStatistic;
            return this;
        }
        public Builder amount(final int aAmount) {
            amount = aAmount;
            return this;
        }
        public Obstacle build(){
            return new Obstacle(statistic, amount);
        }
    }

    @Override
    public String toString() {
        return getName() + System.lineSeparator() + getAmount();
    }
}
