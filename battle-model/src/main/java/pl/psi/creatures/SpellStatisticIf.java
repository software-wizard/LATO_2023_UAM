package pl.psi.creatures;

import com.google.common.collect.Range;

public interface SpellStatisticIf {
    String getName();
    int getClassOfSpell();
    int getCost();
    int getSpellDamage();
    int getArmorChange();
    int getDamageChange();
    int getMoveRangeChange();
    int getSpellProtection();
    int getTier();
    String getDescription();
}


