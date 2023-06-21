package pl.psi.skills;

import pl.psi.Hero;
import pl.psi.creatures.WarMachine;
import pl.psi.interfaces.SkillsInterface;

import java.util.List;

public class BallisticsSkill implements SkillsInterface {

    private final SkillEnum skillEnum;

    public BallisticsSkill(SkillEnum skillEnum){
        this.skillEnum = skillEnum;

    }

    double getValueFromEnum() {
        if (skillEnum == SkillEnum.BASIC){
            return 0;
        }else if (skillEnum == SkillEnum.ADVANCED) {
            return 1;
        }else {
            return 1;
        }
    }

    @Override
    public void apply(Hero hero) {
        List<WarMachine> warMachines = hero.getWarMachines();
        warMachines.forEach(w -> w.parseSkill("Ballistics", getValueFromEnum()));
    }
}
