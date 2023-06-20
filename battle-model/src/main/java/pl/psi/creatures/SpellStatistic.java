package pl.psi.creatures;

import lombok.Getter;

@Getter
public enum SpellStatistic implements SpellStatisticIf
{
    LIGHTNING_BOLT( "Lightning bolt", 0, 10, 5,0,0, 0, 0,1, "Causes a bolt of lightning to strike the selected unit."),//
    DISRUPTING_RAY( "Disrupting ray", 0, 10, 0,-5,0, 0, 0,2, "Reduces the selected enemy unit's defense strength. A single enemy may be targeted multiple times by this spell."),//
    PROTECTION_FROM_AIR( "Protection from air", 0, 5, 0,0,0, 0, 10,3, "Protects the selected unit, reducing damage received from Air spells."),//
    FLY( "Fly", 0, 20, 0,0,0, 2147483647, 0,4, "You may teleport your hero to a visible, unoccupied location on the adventure map."),//

    FIREBALL( "Fireball", 1, 15, 15,0,0, 0, 0,1, "Causes the selected target to burst into flames, inflicting fire damage to the target and any adjacent units."),//
    BLOODLUST( "Bloodlust", 1, 5, 0,0,3, 0, 0,2, "Increases the hand-to-hand damage inflicted by the selected unit."),//
    PROTECTION_FROM_FIRE( "Protection from fire", 1, 5, 0,0,0, 0, 10,3, "Increases the hand-to-hand damage inflicted by the selected unit."),//
    BLIND( "Blind", 1, 10, 0,0,0, -2147483647, 0,4, "Increases the hand-to-hand damage inflicted by the selected unit."),//

    METEOR_SHOWER("Meteor shower", 2, 16, 20,0,0,0,0,1, "Causes a meteor shower to rain down on the selected target"),//
    SHIELD("Shield", 2, 5, 0,5,0,0,0,2, "Shields a selected unit, reducing the amount of damage received from hand-to-hand attacks."),//
    PROTECTION_FROM_EARTH("Protection from earth", 2, 5, 0,0,0,0,10,3, "Shields a selected unit, reducing the amount of damage received from hand-to-hand attacks."),//
    EARTHQUAKE("Earthquake", 2, 20, 5,0,0,0,0,4, "Sends a wave of death across the battlefield which damages all non-undead units."),//

    ICE_BOLT("Ice bolt", 3,8,10,0,0,0,0,1,"Drains the body heat from the selected enemy unit"),//
    WEAKNESS("Weakness", 3,8,0,0,-3,0,0,2,"Reduces the selected enemy unit's attack strength."),//
    PROTECTION_FROM_WATER("Protection from water", 3, 5, 0,0,0,0,10,3, "Protects the selected unit, reducing damage received from Water spells."),//
    CURE("Cure", 3, 6, -5,0,0,0,0,4, "Healing."),//
    ;
    private final String name;
    private final int classOfSpell;
    private final int cost;
    private final int spellDamage;
    private final int armorChange;
    private final int damageChange;
    private final int moveRangeChange;
    private final int spellProtectionChange;
    private final int tier;
    private final String description;

    SpellStatistic(String name, int classOfSpell, int cost, int spellDamage, int armorChange, int damageChange, int moveRangeChange, int spellProtectionChange, int tier, String description) {
        this.name = name;
        this.classOfSpell = classOfSpell;
        this.cost = cost;
        this.spellDamage = spellDamage;
        this.armorChange = armorChange;
        this.damageChange = damageChange;
        this.moveRangeChange = moveRangeChange;
        this.spellProtectionChange = spellProtectionChange;
        this.tier = tier;
        this.description = description;
    }
}
