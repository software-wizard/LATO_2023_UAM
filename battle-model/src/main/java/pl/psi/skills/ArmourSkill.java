package pl.psi.skills;

import pl.psi.Hero;
import pl.psi.creatures.*;
import pl.psi.interfaces.SkillsInterface;

import java.util.List;
import java.util.Random;

public class ArmourSkill extends DefaultDamageCalculator implements SkillsInterface {

    private final SkillEnum skillEnum;

    public ArmourSkill(SkillEnum skillEnum) {
        super(new Random());
        this.skillEnum = skillEnum;
    }

    double getValueFromEnum() {
        if (skillEnum == SkillEnum.BASIC){
            return 0.95;
        }else if (skillEnum == SkillEnum.ADVANCED) {
            return 0.9;
        }else {
            return 0.85;
        }
    }

    @Override
    public void apply(Hero hero) {
        List<Creature> creatures = hero.getCreatures();
        for (Creature s : creatures) {
            DamageCalculatorIf currentCalculator = s.getDamageCalculator();
            s.setDamageCalculator(new SkillDecorator(currentCalculator, getValueFromEnum()));
        }
    }
}
