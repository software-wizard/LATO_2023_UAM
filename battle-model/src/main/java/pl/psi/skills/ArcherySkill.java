package pl.psi.skills;

import pl.psi.Hero;
import pl.psi.creatures.Creature;
import pl.psi.creatures.DamageCalculatorIf;
import pl.psi.creatures.DefaultDamageCalculator;
import pl.psi.interfaces.SkillsInterface;

import java.util.List;
import java.util.Random;

public class ArcherySkill extends DefaultDamageCalculator implements SkillsInterface {

    private final SkillEnum skillEnum;

    public ArcherySkill(SkillEnum skillEnum) {
        super(new Random());
        this.skillEnum = skillEnum;
    }

    double getValueFromEnum() {
        if (skillEnum == SkillEnum.BASIC){
            return 1.1;
        }else if (skillEnum == SkillEnum.ADVANCED) {
            return 1.25;
        }else {
            return 1.5;
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
