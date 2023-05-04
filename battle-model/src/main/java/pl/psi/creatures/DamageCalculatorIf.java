package pl.psi.creatures;

import pl.psi.Defendable;

public interface DamageCalculatorIf
{
    int calculateDamage( Creature aAttacker, Defendable aDefender );
}
