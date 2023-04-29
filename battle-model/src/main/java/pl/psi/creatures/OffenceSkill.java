package pl.psi.creatures;

import java.util.Random;

public class OffenceSkill extends DefaultDamageCalculator{
    private static final double OFFENCE_BASIC = 0.1;

    protected OffenceSkill() {

        super(new Random());
    }

    @Override
    public int applyDamageStrategy(int i)
    {
        return (int)(i * (1 + OFFENCE_BASIC));
    }
}

