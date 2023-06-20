package pl.psi.specialFields;

import pl.psi.Defendable;
import pl.psi.creatures.Spell;

public class EffectStatusTrap extends TransparentObstacle {
    private Spell spell;

    public EffectStatusTrap(Obstacle aObstacle, Spell aSpell) {
        super(aObstacle);
        this.spell = aSpell;
    }
    @Override
    public void applyEffectOnTouch(Defendable aDefendable){
        castOnTarget(aDefendable);
    }

    private void castOnTarget(Defendable aDefendable){
        spell.cast(aDefendable);
    }
}
