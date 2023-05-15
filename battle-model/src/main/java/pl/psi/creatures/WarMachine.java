package pl.psi.creatures;

import lombok.Getter;
import pl.psi.warmachines.WarMachineStatisticIf;

import java.util.Random;

@Getter
public class WarMachine {
    private WarMachineStatisticIf stats;
    private int currentHp;
    private int relevantSkill;


    WarMachine(){
    }

    //TODO: look at Creature.java and implement the missing stuff
    private WarMachine(final WarMachineStatisticIf aStats, final int aRelevantSkill){
        currentHp = aStats.getMaxHp();
        stats = aStats;
        relevantSkill = aRelevantSkill;
    }

    public void heal(final Creature creature){
        Random tmpRand = new Random();

        creature.setCurrentHp(creature.getCurrentHp() + (tmpRand.nextInt(25+(25*getRelevantSkill()))+1));
        if(creature.getCurrentHp() > creature.getMaxHp()){
            creature.setCurrentHp(creature.getMaxHp());
        }
    }

    public boolean isAlive(){
        return getCurrentHp() > 0;
    }
    public String getName(){
        return stats.getName();
    }

    public boolean isControllable() {
        return getRelevantSkill() != 0;
    }

    public static class Builder{

        private WarMachineStatisticIf statistic;
        private int relSkill;


        public Builder statistic(final WarMachineStatisticIf aStatistic){
            statistic = aStatistic;
            return this;
        }

        public Builder relSkill(final int aRelSkill){
            relSkill = aRelSkill;
            return this;
        }

        public WarMachine build(){
            return new WarMachine(statistic, relSkill);
        }
    }

    @Override
    public String toString(){
        return getName();
    }
}
