package pl.psi.creatures;

public interface SpellStatisticIf {
    String getName();
    int getClassOfSpell();
    int getCost();
    int getSpellDamage();
    int getArmorChange();
    int getDamageChange();
    int getMoveRangeChange();
    int getSpellProtectionChange();
    int getTier();
    String getDescription();
}


