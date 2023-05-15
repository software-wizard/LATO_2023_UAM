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

    public Class<?> getType(){
        if(null!=creatureVal){
            return Creature.class;
        }else if(null!=warMachineVal){
            return WarMachine.class;
        }else{
            return null;
        }
    }
}
