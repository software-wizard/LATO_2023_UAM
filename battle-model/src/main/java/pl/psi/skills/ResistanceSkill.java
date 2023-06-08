package pl.psi.skills;

import pl.psi.Hero;
import pl.psi.creatures.Spell;
import pl.psi.creatures.SpellFailureIf;
import pl.psi.interfaces.SkillsInterface;

import java.util.List;

public class ResistanceSkill implements SpellFailureIf, SkillsInterface {
    private final SkillEnum skillEnum;

    public ResistanceSkill(SkillEnum skillEnum) {
        this.skillEnum = skillEnum;
    }

    int getValueFromEnum() {
        if (skillEnum == SkillEnum.BASIC){
            return 95;
        }else if (skillEnum == SkillEnum.ADVANCED) {
            return 90;
        }else {
            return 80;
        }
    }

    @Override
    public int chancesOfSpellFailure() {
        return getValueFromEnum();
    }

    @Override
    public void apply(Hero hero) {
        List<Spell> spellBook = hero.getSpellBook();
        for (Spell s : spellBook) {
            System.out.println(chancesOfSpellFailure());
            s.chancesOfSpellFailure();
        }
    }
}
