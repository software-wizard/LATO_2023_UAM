package pl.psi.creatures;

import com.google.common.collect.Range;

public interface SpellStatisticIf {
    String getName();
    int getAttack();
    int getMoveRange();
    int getDamage();
    int getTier();
    String getDescription();
}
