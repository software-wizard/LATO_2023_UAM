package pl.psi.creatures;
import pl.psi.converter.SkillsInterface;
import java.util.List;
import java.util.Random;

public class ArmourSkill extends DefaultDamageCalculator implements SkillsInterface {

    private final SkillEnum skillEnum;

    public ArmourSkill(SkillEnum skillEnum) {
        super(new Random());
        this.skillEnum = skillEnum;
    }

    @Override
    public void apply(List<Creature> creatures) {
        for (Creature s : creatures) {
            DamageCalculatorIf currentCalculator = s.getDamageCalculator();
            s.setDamageCalculator(new ArmourDecorator(currentCalculator, getValueFromEnum()));
        }
        //creatures.forEach(s  -> s.setDamageCalculator( new ArmourDecorator(this)));
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

}
