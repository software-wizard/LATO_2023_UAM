package pl.psi.creatures;

import lombok.Getter;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class BattleUnit {
    //wrapper for Creature and WarMachine to co-exist within turnQueue etc.
    @Getter
    private final Creature creatureVal;
    @Getter
    private final WarMachine warMachineVal;

    public BattleUnit(Creature cVal){
        creatureVal = cVal;
        warMachineVal = null;
    }

    public BattleUnit(WarMachine wVal){
        warMachineVal = wVal;
        creatureVal = null;
    }

    public boolean isCreature(){
        return null!=creatureVal;
    }
    public boolean isWarMachine(){
        return null!=warMachineVal;
    }

    public int getMoveRange(){
        if(isCreature()){
            return getCreatureVal().getMoveRange();
        }else{
            return 0;
        }
    }

    public int getCurrentHp(){
        if(isCreature()){
            return getCreatureVal().getCurrentHp();
        }else{
            return getWarMachineVal().getCurrentHp();
        }
    }

    public int getMaxHp(){
        if(isCreature()){
            return getCreatureVal().getMaxHp();
        }else{
            return getWarMachineVal().getStats().getMaxHp();
        }
    }

    public void attack(final BattleUnit defender){
        if(defender.isCreature()){
            if(isCreature()){
                getCreatureVal().attack(defender.getCreatureVal());
            }else{
                if(getWarMachineVal().canAttack()) {
                    getWarMachineVal().attack(defender.getCreatureVal());
                }
            }
        }else{
            if(isCreature()){
                getCreatureVal().attack(defender.getWarMachineVal());
            }
        }
    }

    public void heal(final BattleUnit patient){
        if(patient.isCreature() && isWarMachine()){
            if(getWarMachineVal().canHeal()){
                getWarMachineVal().heal(patient.getCreatureVal());
            }
        }
    }

    public boolean canAttack(){
        if(isCreature()){
            return true;
        }else{
            return getWarMachineVal().canAttack();
        }
    }

    public boolean canHeal(){
        if(isCreature()){
            return false;
        }else{
            return getWarMachineVal().canHeal();
        }
    }

    public boolean canSiege(){
        if(isCreature()){
            return false;
        }else{
            return getWarMachineVal().canSiege();
        }
    }

    public boolean isControllable(){
        if(isCreature()){
            return true;
        }else{
            return getWarMachineVal().isControllable();
        }
    }

    public void selfAct(List<BattleUnit> allies, List<BattleUnit> enemies){
        Random tmpRand = new Random();
        if(canAttack()){
            if(enemies.size()>0) {
                attack(enemies.get(tmpRand.nextInt(enemies.size())));
            }
        }else if(canHeal()){
            List<BattleUnit> patients = allies.stream().filter(b -> { return b.getCurrentHp() < b.getMaxHp(); }).collect(Collectors.toList());
            if(patients.size()>0) {
                heal(patients.get(tmpRand.nextInt(patients.size())));
            }
        }else if(canSiege()){
            //nothing to siege yet
        }
    }

    public void applyDamage(final BattleUnit aDefender, final int aDamage){
        if(isCreature()){
            getCreatureVal().applyDamage(aDefender.getCreatureVal(), aDamage);
        }else{
            getWarMachineVal().applyDamage(aDefender.getWarMachineVal(), aDamage);
        }
    }

    @Override
    public String toString(){
        if(isCreature()){
            return getCreatureVal().toString();
        }else{
            return getWarMachineVal().toString();
        }
    }
}
