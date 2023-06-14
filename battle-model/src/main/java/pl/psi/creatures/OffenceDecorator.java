package pl.psi.creatures;

public class OffenceDecorator implements DamageCalculatorIf{

    private final DamageCalculatorIf decorated;
    private final double enumValue;

    public OffenceDecorator(DamageCalculatorIf calculatorIf, double enumValue) {
        this.decorated = calculatorIf;
        this.enumValue = enumValue;
    }

    @Override
    public int calculateDamage(Creature attacker, Creature defender) {
        int damage = decorated.calculateDamage(attacker, defender);
        return changeAfterCalculation(damage);
    }

    @Override
    public int calculateDamage(Creature aAttacker, WarMachine aDefender){
        int damage = decorated.calculateDamage(aAttacker, aDefender);
        return changeAfterCalculation(damage);
    }

    @Override
    public int changeAfterCalculation(int calculateDamageResult) {
        return (int) (calculateDamageResult * enumValue);
    }
    
}




