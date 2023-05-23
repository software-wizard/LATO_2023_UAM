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

    @Override
    public String toString(){
        if(isCreature()){
            return getCreatureVal().toString();
        }else{
            return getWarMachineVal().toString();
        }
    }
}