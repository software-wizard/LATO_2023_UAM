package pl.psi.specialFields;

import lombok.Getter;
import lombok.Setter;
import pl.psi.Defendable;
import pl.psi.creatures.Creature;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
@Getter
public class FreeStandingObject implements PropertyChangeListener, Defendable {
    private FreeStandingStatisticIf stats;
    @Setter
    private int amount;
    private int currentHp;

    //FreeStandingObject(){}

    private FreeStandingObject(final FreeStandingStatisticIf aStats, final int aAmount){
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

    public String getName() {
        return stats.getName();
    }
    public boolean isAlive() {
        return getAmount() > 0;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    @Override
    public boolean canCounterAttack() {
        return false;
    }

    public static class Builder {
        private int amount = 1;
        private FreeStandingStatisticIf statistic;
        
        public Builder statistic(final FreeStandingStatisticIf aStatistic){
            statistic = aStatistic;
            return this;
        }
        public Builder amount(final int aAmount) {
            amount = aAmount;
            return this;
        }
        public FreeStandingObject Build(){
            return new FreeStandingObject(statistic, amount);
        }
    }

    @Override
    public String toString() {
        return getName() + System.lineSeparator() + getAmount();
    }
}
