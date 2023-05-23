package pl.psi.creatures;

import lombok.Getter;

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

    @Override
    public String toString(){
        if(isCreature()){
            return getCreatureVal().toString();
        }else{
            return getWarMachineVal().toString();
        }
    }
}
