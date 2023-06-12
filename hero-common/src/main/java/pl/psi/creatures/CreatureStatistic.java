package pl.psi.creatures;

import com.google.common.collect.Range;

import lombok.Getter;

@Getter
public enum CreatureStatistic implements CreatureStatisticIf
{
    // NECROPILIS FRACTION
    SKELETON( "Skeleton", 5, 4, 6, 4, Range.closed( 1, 3 ), 1, new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
        "Average lvl1 foot soldier, but always in huge numbers thanks to necromancy skill and skeleton transformer.", false ), //
    WALKING_DEAD( "Walking Dead", 5, 5, 15, 3, Range.closed( 2, 3 ), 2,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
        "Basically its the same skeleton with more hit points. I prefer buying 2 skeletons instead.", false ), //
    WIGHT( "Wight", 7, 7, 18, 5, Range.closed( 3, 5 ), 3,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
        "Regenerating ability is really good when fighting weak enemies, especially shooters.\nSpecial: top wight of the stack regenerates all lost damage in the beginning of each round", false ), //
    VAMPIRE( "Vampire", 10, 9, 30, 6, Range.closed( 5, 8 ), 4,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
        "NOTHING compared to their upgraded brothers. Keep the population growing and recruit after the upgrade.\nSpecial: no enemy retaliation.", false ), //
    LICH( "Lich", 13, 10, 30, 6, Range.closed( 11, 15 ), 5,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
        "Now they last longer and are able to do more damage! A must for good necropolis army.\nSpecial: death cloud range attack - damages living creatures on adjacent hexes to target.\n", false ), //
    BLACK_KNIGHT( "Black Knight", 16, 16, 120, 7, Range.closed( 15, 30 ), 6, new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
        "Awesome ground unit. As any undead it cannot be blinded, so your enemies will have to look out.\nSpecial: 20% chance to curse enemy.\n", false ), //
    BONE_DRAGON( "Bone Dragon", 17, 15, 150, 9, Range.closed( 25, 50 ), 7,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
        "They are truly fearsome for enemies with low morale. Simply keeping them on battlefield scares enemies.\nSpecial: -1 to enemy morale.\n", false ), //
    SKELETON_WARRIOR( "Skeleton Warrior", 6, 6, 6, 5, Range.closed( 1, 3 ), 1, new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
        "Numerous skeletons become even better, but running back to town and upgrading is a problem... If there is no room in your army for ordinary skeletons, necromancy skill will resurrect skeleton warriors, but there will be less of them than normal skeletons, so it might be a good idea not to upgrade cursed temple at all.", true ), //
    ZOMBIE( "Zombie", 5, 5, 20, 4, Range.closed( 2, 3 ), 2,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
        "Attack ratings are way too low... In my opinion, necropolis has the worst lvl2 creature.\nSpecial: 20% chance to disease enemies (-2Att -2Def for 3 rounds)\n", true ), //
    WRAITH( "Wraith", 7, 7, 18, 5, Range.closed( 3, 5 ), 3,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
        "Regenerating ability is really good when fighting weak enemies, especially shooters.\nSpecial: top wight of the stack regenerates all lost damage in the beginning of each round\n", true ), //
    VAMPIRE_LORD( "Vampire Lord", 10, 10, 40, 9, Range.closed( 5, 8 ), 4,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
        "My favorite necropolis unit. Use them as main striking unit and you might end up with no losses!\nSpecial: no enemy retaliation ; resurrects members of their own stack by restoring health equal to the amount of damage they do to living enemies.\n", true ), //
    POWER_LICH( "Power Lich", 13, 10, 40, 7, Range.closed( 11, 15 ), 5,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
        "Now they last longer and are able to do more damage! A must for good necropolis army.\nSpecial: death cloud range attack - damages living creatures on adjacent hexes to target.\n", true ), //
    DREAD_KNIGHT( "Dread Knight", 18, 18, 120, 9, Range.closed( 15, 30 ), 6,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
        "I think it's the best lvl6 unit in the game! Double damage ability puts Dread Knights above Naga Queens.\nSpecial: 20% chance to curse enemy ; 20% chance to do double damage.\n", true ), //
    GHOST_DRAGON( "Ghost Dragon", 19, 17, 200, 14, Range.closed( 25, 50 ), 7,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
        "When situation seems hopeless, take a chance on the best enemy stack! If you'll get lucky, half their hit points will be gone instantly!! Ageing ability makes ghost dragons as dangerous as other lvl7 creatures.\nSpecial: -1 to enemy morale ; 20% chance to age enemy (halve hit points of all stack members).\n", true ),//
    //Castle faction
    PIKEMAN("Pikeman", 4, 5, 10, 4, Range.closed(1, 3),1, new SpellProtection.spellProtectionBuilder()
        .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
    "Toughest lvl1 unit, but a bit slow.", false),
    ARCHER("Archer",6,3,10,4,Range.closed(2,3),2,new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
            "Archer's upgrade is literally 2 times better. Upgrade them quickly.", false),
    GRIFFIN("Griffin",8,8,25,6,Range.closed(3,6),3,new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
            "High in population, griffins become castle's main unit for the midgame.", false);

    private final String name;
    private final int attack;
    private final int armor;
    private final int maxHp;
    private final int moveRange;
    private final Range< Integer > damage;
    private final int tier;
    private final String description;
    private final boolean isUpgraded;
    private final SpellProtection spellDamageProtection;

    CreatureStatistic( final String aName, final int aAttack, final int aArmor, final int aMaxHp,
        final int aMoveRange, final Range< Integer > aDamage, final int aTier, final SpellProtection aSpellDamageProtection, final String aDescription,
        final boolean aIsUpgraded )
    {
        name = aName;
        attack = aAttack;
        armor = aArmor;
        maxHp = aMaxHp;
        moveRange = aMoveRange;
        damage = aDamage;
        tier = aTier;
        spellDamageProtection = aSpellDamageProtection;
        description = aDescription;
        isUpgraded = aIsUpgraded;
    }
    String getTranslatedName()
    {
        return name;
    }

    @Override
    public CreatureStatisticIf plus(CreatureStatisticIf stats) {
        return null;
    }
}
