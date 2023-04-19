package pl.psi.creatures;


public class OffenceSkill extends Creature{
    private static final double OFFENCE_BASIC = 0.1; // 10%
    //private static final double OFFENCE_ADVANCED = 0.2; // 20%
    //private static final double OFFENCE_EXPERT = 0.3; // 30%

    public OffenceSkill(Creature creature) {
        super(creature.getStats(), creature.getCalculator(), creature.getAmount());
    }

    @Override
    public void attack(Creature aDefender) {
        final int damage = (int) Math.round(getCalculator().calculateDamage(this, aDefender) * (1 + OFFENCE_BASIC));
        applyDamage(aDefender, damage);
        if (canCounterAttack(aDefender)) {
            counterAttack(aDefender);
        }
    }

}
