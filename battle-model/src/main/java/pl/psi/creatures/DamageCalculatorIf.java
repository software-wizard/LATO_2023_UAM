package pl.psi.creatures;

public interface DamageCalculatorIf
{
    int calculateDamage( Creature aAttacker, Creature aDefender );
    int applyDamageStrategy(int i);
}
