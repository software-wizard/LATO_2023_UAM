package pl.psi.creatures;

import pl.psi.Defendable;

import java.util.Random;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
class ReduceDefenceCalculator extends AbstractCalculateDamageStrategy
{

    private final double factor;

    public ReduceDefenceCalculator()
    {
        super( new Random() );
        factor = 0.2;
    }

    @Override
    protected int getArmor( final Defendable aDefender )
    {
        return (int)(aDefender.getArmor() * factor);
    }
}
