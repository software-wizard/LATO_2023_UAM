package pl.psi.creatures;

public class ArmourDecorator implements DamageCalculatorIf {

    private final DamageCalculatorIf decorated;
    private final double enumValue;

    public ArmourDecorator(DamageCalculatorIf calculatorIf, double enumValue) {
        this.decorated = calculatorIf;
        this.enumValue = enumValue;
    }
    @Override
    public int calculateDamage(Creature attacker, Creature defender) {
        int damage = decorated.calculateDamage(attacker, defender);
        return changeAfterCalculation(damage);
    }
    @Override
    public int changeAfterCalculation(int calculateDamageResult) {
        return (int) (calculateDamageResult * enumValue);
    }

}
