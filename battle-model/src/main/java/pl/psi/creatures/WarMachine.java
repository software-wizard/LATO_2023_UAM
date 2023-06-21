package pl.psi.creatures;

import lombok.Getter;
import lombok.Setter;
import pl.psi.Defendable;
import pl.psi.warmachines.WarMachineStatisticIf;

import java.util.*;

@Getter
public class WarMachine implements Defendable {
    private WarMachineStatisticIf stats;
    @Setter
    private int currentHp;

    private HashMap<String, Double> relevantSkills = new HashMap<>(Map.of(
            "Archery", (double)0,
            "Artillery", (double)0,
            "Attack", (double)0,
            "Ballistics", (double)0,
            "First Aid", (double)0
    ));

    WarMachine(){
    }

    private WarMachine(final WarMachineStatisticIf aStats){
        currentHp = aStats.getMaxHp();
        stats = aStats;
    }

    public void heal(final Creature creature){
        if(isAlive()) {
            final Random tmpRand = new Random();

            creature.setCurrentHp(creature.getCurrentHp() + (tmpRand.nextInt(25 + (25 * relevantSkills.get("First Aid").intValue())) + 1));
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

    public WarMachine parseSkill(String aName, Double aValue){
        relevantSkills.put(aName, aValue);
        return this;
    }

    public boolean isAlive(){
        return getCurrentHp() > 0;
    }
    public String getName(){
        return stats.getName();
    }

    public boolean canAttack(){
        return getStats().getType().equals(WarMachineType.ATTACK);
    }
    public boolean canHeal() { return getStats().getType().equals(WarMachineType.HEAL);}

    public boolean canSiege() { return getStats().getType().equals(WarMachineType.SIEGE);}

    @Override
    public int getMaxHp() {
        return getStats().getMaxHp();
    }

    public WarMachineType getType(){
        return getStats().getType();
    }

    public Double getControlSkill(){
        switch(getType()){
            case HEAL:
                return relevantSkills.get("First Aid");
            case ATTACK:
                return relevantSkills.get("Ballistics");
            case SIEGE:
                return relevantSkills.get("Artillery");
            default:
                return (double)0;
        }
    }

    @Override
    public void attack(Defendable aDefender) {
        if(isAlive()){
            //TODO: once Hero skills are done, prepare the formula for calculating damage. base is range(2-3)*(hero's attack+1), 0-10%-25%-50% additional depending on Archery, 0% chance to inflict double damage, 50% chance to inflict double damage, 75% to inflict double damage + shoots twice, 100% double damage and shoots twice
            final int aDamage = 10;
            aDefender.applyDamage(aDamage);
        }
    }

    @Override
    public void applyDamage(final int aDamage){
        setCurrentHp(getCurrentHp()-aDamage);
    }

    @Override
    public void counterAttack(Creature aDefender) {
        //does nothing, added only for Defendable interface compatibility
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
        return getControlSkill() != 0;
    }

    @Override
    public boolean isTransparent() {
        return false;
    }

    public static class Builder{

        private WarMachineStatisticIf statistic;

        public Builder statistic(final WarMachineStatisticIf aStatistic){
            statistic = aStatistic;
            return this;
        }

        public WarMachine build(){
            return new WarMachine(statistic);
        }
    }


    @Override
    public String toString(){
        return getName();
    }
}
