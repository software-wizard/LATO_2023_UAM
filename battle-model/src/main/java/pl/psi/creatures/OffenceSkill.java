package pl.psi.creatures;

import pl.psi.converter.SkillsInterface;
import java.util.List;
import java.util.Random;

public class OffenceSkill extends DefaultDamageCalculator implements SkillsInterface {

    private final SkillEnum skillEnum;

    public OffenceSkill(SkillEnum skillEnum) {
        super(new Random());
        this.skillEnum = skillEnum;
    }

    @Override
    public void apply(List<Creature> creatures) {
        for (Creature s : creatures) {
            DamageCalculatorIf currentCalculator = s.getDamageCalculator();
            s.setDamageCalculator(new OffenceDecorator(currentCalculator, getValueFromEnum()));
        }
        //creatures.forEach(s  -> s.setDamageCalculator( new OffenceDecorator(this) ));
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


