package pl.psi.skills;

import pl.psi.Hero;
import pl.psi.creatures.Creature;
import pl.psi.interfaces.SkillsInterface;

import java.util.List;

public class ResistanceSkill implements SkillsInterface {
    private final SkillEnum skillEnum;

    public ResistanceSkill(SkillEnum skillEnum) {
        this.skillEnum = skillEnum;
    }

    int getValueFromEnum() {
        if (skillEnum == SkillEnum.BASIC){
            return 5;
        }else if (skillEnum == SkillEnum.ADVANCED) {
            return 10;
        }else {
            return 20;
        }
    }

    @Override
    public void apply(Hero hero) {
        List<Creature> creatures = hero.getCreatures();
        for (Creature s : creatures) {
          s.setPercentOfSpellResistance(getValueFromEnum());
        }
    }
}
