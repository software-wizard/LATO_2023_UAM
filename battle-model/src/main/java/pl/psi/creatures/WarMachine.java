package pl.psi.creatures;

import lombok.Getter;
import lombok.Setter;
import pl.psi.Defendable;
import pl.psi.specialFields.Obstacle;
import pl.psi.warmachines.WarMachineStatisticIf;
import pl.psi.warmachines.WarMachineType;

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

    protected void siege(final Obstacle obstacle){
        if(isAlive()) {
            obstacle.applyDamage(1);
            if(relevantSkills.get("Ballistics")>0){
                obstacle.applyDamage(1);
            }
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
                return relevantSkills.get("Artillery");
            case SIEGE:
                return relevantSkills.get("Ballistics");
            default:
                return (double)0;
        }
    }

    @Override
    public void attack(Defendable aDefender) {
        if(isAlive()){
            WarMachineDamageCalculator tmpCalculator = new WarMachineDamageCalculator(new Random());
            int aDamage = tmpCalculator.calculateDamage(this, aDefender);
            aDefender.applyDamage(aDamage);
            Random tmpRand = new Random();
            if(tmpRand.nextInt(100)<relevantSkills.get("Artillery").intValue()){
                aDamage = tmpCalculator.calculateDamage(this, aDefender);
                aDefender.applyDamage(aDamage);
            }

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
