package pl.psi.creatures;

import pl.psi.converter.SkillsInterface;

import java.util.List;
import java.util.Random;

public class ArmourSkill extends DefaultDamageCalculator implements SkillsInterface {


    public enum ArmourEnum {
        BASIC(0.95),
        ADVANCED(0.9),
        EXPERT(0.85);

        private final double value;

        ArmourEnum(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }
    }
    private final ArmourSkill.ArmourEnum armourEnum;

    public ArmourSkill(ArmourSkill.ArmourEnum armourEnum) {

        super(new Random());
        this.armourEnum = armourEnum;
    }

    @Override
    public int applyDamageStrategy(int i)
    {
        return (int)(i * (armourEnum.getValue()));
    }

    @Override
    public void apply(List<Creature> creatures) {
        creatures.forEach(s  -> s.setDemageCalculator(this));
    }
}
