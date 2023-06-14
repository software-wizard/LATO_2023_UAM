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
            "High in population, griffins become castle's main unit for the midgame.", false),
    SWORDSMAN( "Swordsman", 10, 12, 35, 5, Range.closed( 6, 9 ), 4,  new SpellProtection.spellProtectionBuilder()
        .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
        "A unit that counts only in the upgraded version.\n", false ),
    MONK( "Monk", 12, 7, 30, 5, Range.closed( 10, 12 ), 5,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
            "From a technical standpoint, this unit is weaker than the previous tier, but it is capable of shooting, which puts it in an advantageous position\n", false ),
    CAVALIER( "Cavalier", 15, 15, 100, 7, Range.closed( 15, 25 ), 6,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
            "One of the best units at this level - fast, high health, excellent attack and defense parameters, with above-average damage.\nSpecial : +5% to damage for every step in battlefield.\n", false ),
    ANGEL( "Angel", 20, 20, 200, 12, Range.closed( 50, 50 ), 7,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
            "They are blessed, which is why they deal constant, very high damage.\nSpecial : They raise the army's morale by 1 point and have a 50% damage bonus against demons.\n", false ),
    HALBERDIER("Halberdier", 6, 5, 10, 5, Range.closed(2, 3),1, new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
            "Physically, it is the strongest unit at this level.", true),
    MARKSMAN("Marksman",6,3,10,6,Range.closed(2,3),2,new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
            "Crossbowman is two times better than archer because it is shooting two times and has double amount of arrows.\n", true),
    ROYAL_GRIFFIN("Royal Griffin",9,9,25,9,Range.closed(3,6),3,new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
            "Counterattack is limitless.\n", true),
    CRUSADER( "Crusader", 12, 12, 35, 6, Range.closed( 7, 10 ), 4,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
            "Better option of Armed unit - has double attack.\n", true ),
    ZEALOT( "Zealot", 12, 10, 30, 7, Range.closed( 10, 12 ), 5,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
            "Double amount of arrows and no penalties in close combat\n", true ),
    CHAMPION( "Champion", 16, 16, 100, 9, Range.closed( 20, 25 ), 6,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
            "Upgraded Cavalryman gives more minimal damage, movement range, attack and defend.\nSpecial : +5% to damage for every step in battlefield.\n", true ),
    ARCHANGEL( "Archangel", 30, 30, 250, 18, Range.closed( 50, 50 ), 7,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
            "They are blessed, which is why they deal constant, very high damage.\nSpecial : They raise the army's morale by 1 point, are capable of resurrecting fallen units, and have a 50% damage bonus against demons.\n", true ),
    //Bastion faction
    CENTAUR( "Centaur", 5, 3, 8, 6, Range.closed( 2, 3 ), 1,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
            "The best unit at lvl1.\n", false ),
    DWARF( "Dwarf", 6, 7, 20, 3, Range.closed( 2, 4 ), 2,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
            "Dwarves are very strong units, with both good attack and defense capabilities but slow.\nSpecial : +20% magic resistance ", false ),
    WOOD_ELF( "Wood elf", 9, 5, 15, 6, Range.closed( 3, 5 ), 3,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
            "One of the best 3lvl units in upgraded version.\n", false ),
    PEGASUS( "Pegasus", 9, 8, 30, 8, Range.closed( 5, 9 ), 4,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
            "The presence of a Pegasus on the battlefield increases the cost of spells for the enemy hero by 2 points.\n", false ),
    DENDROID_GUARD( "Dendroid guard", 9, 12, 55, 3, Range.closed( 10, 14 ), 5,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
            "It immobilizes the enemy, has good stats, but has low movement range.\n", false ),
    UNICORN( "Unicorn", 15, 14, 90, 7, Range.closed( 18, 22 ), 6,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
            "Units that come into contact with a unicorn gain a 20% magic resistance. Additionally, when a unicorn attacks, it has a 20% chance to cast Blindness on the enemy.\n", false ),
    GREEN_DRAGON( "Green dragon", 18, 18, 180, 10, Range.closed( 40, 50 ), 7,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
            "Slightly weaker and cheaper than the red ones, green dragons perform well in the midgame.\n", false ),
    CENTAUR_CAPTAIN( "Centaur captain", 6, 3, 10, 8, Range.closed( 2, 3 ), 1,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
            "Upgraded is not much worse than halberdier.\n", true ),
    BATTLE_DWARF( "Battle dwarf", 7, 7, 20, 5, Range.closed( 2, 4 ), 2,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
            "+66 % more movement range than not upgraded.\nSpecial : +40% magic resistance ", true ),
    GRAND_ELF( "Grand elf", 9, 5, 15, 7, Range.closed( 3, 5 ), 3,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
            "Capable of double attack.\n", true ),
    SILVER_PEGASUS( "Silver pegasus", 9, 10, 30, 12, Range.closed( 5, 9 ), 4,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
            "Move range increased 1.5 times. The presence of a Pegasus on the battlefield increases the cost of spells for the enemy hero by 2 points.\n", true ),
    DENDROID_SOLDIER( "Dendroid soldier", 9, 12, 65, 4, Range.closed( 10, 14 ), 5,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
            "This upgraded version on dendroid guard is useful.\n", true ),
    WAR_UNICORN( "War unicorn", 15, 14, 110, 9, Range.closed( 18, 22 ), 6,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
            "It has the same advantages as the unicorn, plus it receives a +20 HP and a larger movement range.\n", true ),
    GOLD_DRAGON( "Gold dragon", 27, 27, 250, 16, Range.closed( 40, 50 ), 7,  new SpellProtection.spellProtectionBuilder()
            .airProtection(0).fireProtection(0).earthProtection(0).waterProtection(0).build(),
            "They gain a lot of attack and defense, 70 life and a lot more movement range.\n", false ),
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
