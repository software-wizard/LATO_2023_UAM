package pl.psi.specialFields;

import pl.psi.Defendable;
import pl.psi.creatures.Spell;


public class PhysicalDamageTrap extends TransparentObstacle {
    private int damage;
    private Spell optionalSpell;
    public PhysicalDamageTrap(Obstacle aObstacle, int aDamage, Spell aSpell) {
        super(aObstacle);
        this.damage = aDamage;
        this.optionalSpell = aSpell;
    }

    @Override
    public void applyEffectOnTouch(Defendable aDefendable){
        dealPhysicalDamage(aDefendable);
        //if spell provided
        applyOptionalEffect(aDefendable);
    }

    private void dealPhysicalDamage(Defendable aDefendable){
        aDefendable.applyDamage(damage);
    }

    private void applyOptionalEffect(Defendable aDefendable){
        if (optionalSpell != null) {
            optionalSpell.cast(aDefendable);
        }
    }
}
