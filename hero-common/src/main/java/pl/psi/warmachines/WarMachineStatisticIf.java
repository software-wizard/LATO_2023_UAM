package pl.psi.warmachines;

import com.google.common.collect.Range;
import pl.psi.creatures.WarMachineType;

public interface WarMachineStatisticIf {
    String getName();
    int getAttack();
    int getArmor();
    int getMaxHp();
    Range< Integer > getDamage();
    String getDescription();
    WarMachineType getType();
}
