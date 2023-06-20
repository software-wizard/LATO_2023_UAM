package pl.psi.creatures;

import lombok.Getter;
import lombok.Setter;
import pl.psi.Defendable;
import pl.psi.warmachines.WarMachineStatisticIf;

import java.util.Random;

@Getter
public class WarMachine implements Defendable {
    private WarMachineStatisticIf stats;
    @Setter
    private int currentHp;

    private int relevantSkill;


    WarMachine(){
    }

    private WarMachine(final WarMachineStatisticIf aStats, final int aRelevantSkill){
        currentHp = aStats.getMaxHp();
        stats = aStats;
        relevantSkill = aRelevantSkill;
    }

    public void heal(final Creature creature){
        if(isAlive()) {
            final Random tmpRand = new Random();

            creature.setCurrentHp(creature.getCurrentHp() + (tmpRand.nextInt(25 + (25 * getRelevantSkill())) + 1));
            if (creature.getCurrentHp() > creature.getMaxHp()) {
                creature.setCurrentHp(creature.getMaxHp());
            }
        }
    }

    protected void siege(){
        if(isAlive()) {
            //TODO: method related to catapult - needs actual targets to be implemented.
        }
    }

    public boolean isAlive(){
        return getCurrentHp() > 0;
    }
    public String getName(){
        return stats.getName();
    }

    public boolean canAttack(){
        return getStats().getName().equals("Ballista");
    }
    public boolean canHeal() { return getStats().getName().equals("First Aid Tent");}

    public boolean canSiege() { return getStats().getName().equals("Catapult");}

    @Override
    public int getMaxHp() {
        return getStats().getMaxHp();
    }

    @Override
    public void attack(Defendable aDefender) {
        if(isAlive()){
            //TODO: once Hero skills are done, prepare the formula for calculating damage. base is range(2-3)*(hero's attack+1), 0-10%-25%-50% additional depending on Archery, 0% chance to inflict double damage, 50% chance to inflict double damage, 75% to inflict double damage + shoots twice, 100% double damage and shoots twice
            final int aDamage = 10;
            aDefender.applyDamage(aDamage);
        }
    }

    public void applyDamage(final int aDamage){
        setCurrentHp(getCurrentHp()-aDamage);
    }

    @Override
    public void counterAttack(Creature aDefender) {
        //does nothing, added only for Defendable compatibility
    }

    @Override
    public int getArmor() {
        return getStats().getArmor();
    }

    @Override
    public int getCounterAttackCounter() {
        return 0;
    }

    public boolean isControllable() {
        return getRelevantSkill() != 0;
    }

    @Override
    public boolean isTransparent() {
        return false;
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
