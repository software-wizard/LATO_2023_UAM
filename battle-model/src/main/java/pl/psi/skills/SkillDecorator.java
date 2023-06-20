package pl.psi.skills;

import pl.psi.Defendable;
import pl.psi.creatures.Creature;
import pl.psi.creatures.DamageCalculatorIf;

public class SkillDecorator implements DamageCalculatorIf {

    private final DamageCalculatorIf decorated;
    private final double enumValue;

    public SkillDecorator(DamageCalculatorIf calculatorIf, double enumValue) {
        this.decorated = calculatorIf;
        this.enumValue = enumValue;
    }

    @Override
    public int calculateDamage(Creature attacker, Defendable defender) {
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




