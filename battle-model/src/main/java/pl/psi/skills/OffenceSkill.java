package pl.psi.skills;

import pl.psi.Hero;
import pl.psi.creatures.*;
import pl.psi.interfaces.SkillsInterface;

import java.util.List;
import java.util.Random;

public class OffenceSkill extends DefaultDamageCalculator implements SkillsInterface {

    private final SkillEnum skillEnum;

    public OffenceSkill(SkillEnum skillEnum) {
        super(new Random());
        this.skillEnum = skillEnum;
    }

    @Override
    public void apply(Hero hero) {
        List<Creature> creatures = hero.getCreatures();
        for (Creature s : creatures) {
            DamageCalculatorIf currentCalculator = s.getDamageCalculator();
            s.setDamageCalculator(new OffenceDecorator(currentCalculator, getValueFromEnum()));
        }
    }

    double getValueFromEnum() {
        if (skillEnum == SkillEnum.BASIC){
            return 1.1;
        }else if (skillEnum == SkillEnum.ADVANCED) {
            return 1.2;
        }else {
            return 1.3;
        }
    }

}


