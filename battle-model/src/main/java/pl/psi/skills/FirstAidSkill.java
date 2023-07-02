package pl.psi.skills;

import pl.psi.Hero;
import pl.psi.creatures.WarMachine;
import pl.psi.interfaces.SkillsInterface;

import java.util.List;

public class FirstAidSkill implements SkillsInterface {
    private final SkillEnum skillEnum;

    public FirstAidSkill(SkillEnum skillEnum){
        this.skillEnum = skillEnum;

    }

    double getValueFromEnum() {
        if (skillEnum == SkillEnum.BASIC){
            return 1;
        }else if (skillEnum == SkillEnum.ADVANCED) {
            return 2;
        }else {
            return 3;
        }
    }
    @Override
    public void apply(Hero hero) {
        List<WarMachine> warMachines = hero.getWarMachines();
        warMachines.forEach(w -> w.parseSkill("First Aid", getValueFromEnum()));
    }
}
