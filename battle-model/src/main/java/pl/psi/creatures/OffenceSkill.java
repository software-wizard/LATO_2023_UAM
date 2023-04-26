package pl.psi.creatures;

import java.util.Random;

public class OffenceSkill extends DefaultDamageCalculator{
    private static final double OFFENCE_BASIC = 0.1;

    protected OffenceSkill(Random aRand) {
        super(aRand);
    }

    @Override
    public int calculateStrategy(int i)
    {
        return (int)(i*(1 + OFFENCE_BASIC));
    }
}

