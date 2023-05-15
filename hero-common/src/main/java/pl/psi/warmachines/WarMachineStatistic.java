package pl.psi.warmachines;

import com.google.common.collect.Range;
import lombok.Getter;

@Getter
public enum WarMachineStatistic implements WarMachineStatisticIf{


    FIRST_AID_TENT("First Aid Tent", 0, 0, 75, Range.closed(0, 0), "A tent with the ability to heal injured units."),
    BALLISTA("Ballista", 10, 10, 250, Range.closed(2, 3), "A missile weapon incapable of retaliation."),
    CATAPULT("Catapult", 10, 10, 1000, Range.closed(0, 0), "A siege machine designed to destroy structures.");
    private final String name;
    private final int attack;
    private final int armor;
    private final int maxHp;
    private final Range< Integer > damage;
    private final String description;

    WarMachineStatistic(final String aName, final int aAttack, final int aArmor,
                        final int aMaxHp, final Range< Integer > aDamage, final String aDescription){
        name = aName;
        attack = aAttack;
        armor = aArmor;
        maxHp = aMaxHp;
        damage = aDamage;
        description = aDescription;
    }
}
