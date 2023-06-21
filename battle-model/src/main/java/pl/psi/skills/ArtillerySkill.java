package pl.psi.skills;

import pl.psi.Hero;
import pl.psi.creatures.WarMachine;
import pl.psi.interfaces.SkillsInterface;

import java.util.List;

public class ArtillerySkill implements SkillsInterface {

    private final SkillEnum skillEnum;

    public ArtillerySkill(SkillEnum skillEnum){
        this.skillEnum = skillEnum;

    }

    double getValueFromEnum() {
        if (skillEnum == SkillEnum.BASIC){
            return 50;
        }else if (skillEnum == SkillEnum.ADVANCED) {
            return 75;
        }else {
            return 100;
        }
    }

    @Override
    public void apply(Hero hero) {
        List<WarMachine> warMachines = hero.getWarMachines();
        warMachines.forEach(w -> w.parseSkill("Artillery", getValueFromEnum()));
    }
}
