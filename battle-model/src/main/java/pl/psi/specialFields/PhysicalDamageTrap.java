package pl.psi.specialFields;

import pl.psi.Defendable;
import pl.psi.creatures.Spell;


public class PhysicalDamageTrap extends TransparentObstacle {
    private int damage;
//    private Spell spell;
    public PhysicalDamageTrap(Obstacle aObstacle, int aDamage) {
        super(aObstacle);
        this.damage = aDamage;
//        this.spell = aSpell;
    }

    @Override
    public void applyEffectOnTouch(Defendable aDefendable){
        dealPhysicalDamage(aDefendable);
    }

    private void dealPhysicalDamage(Defendable aDefendable){
        aDefendable.applyDamage(damage);
    }

//    private void applyOptionalEffect(Defendable aDefendable){
//        if (spell != null) {
//            spell.cast(aCreature);
//        }
//    }
}
