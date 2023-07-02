package pl.psi.warmachines;

import com.google.common.collect.Range;

public interface WarMachineStatisticIf {
    String getName();
    int getAttack();
    int getArmor();
    int getMaxHp();
    Range< Integer > getDamage();
    String getDescription();
    WarMachineType getType();
}
