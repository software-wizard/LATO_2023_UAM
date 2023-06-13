package pl.psi.skills;

import pl.psi.Hero;
import pl.psi.interfaces.SkillsInterface;

public class MagicSkill implements SkillsInterface {
    private final SkillEnum airSkillEnum;
    private final SkillEnum earthSkillEnum;
    private final SkillEnum waterSkillEnum;
    private final SkillEnum fireSkillEnum;

    public MagicSkill(SkillEnum airSkillEnum, SkillEnum earthSkillEnum, SkillEnum waterSkillEnum, SkillEnum fireSkillEnum) {

        this.airSkillEnum = airSkillEnum;
        this.earthSkillEnum = earthSkillEnum;
        this.waterSkillEnum = waterSkillEnum;
        this.fireSkillEnum = fireSkillEnum;
    }
    @Override
    public void apply(Hero hero) {

    }
}
