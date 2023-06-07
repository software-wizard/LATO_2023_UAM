package pl.psi.specialFields;

import lombok.Getter;

@Getter
public enum ObstacleStatistic implements ObstacleStatisticIf {
    //special fields
    ROCK("Rock", 4, 1,"basic rock obstacle"),
    BOULDER("Boulder", 7, 2, "a rock but much bigger and harder");


    private final String name;
    private final int maxHp;
    private final int tier;
    private final String description;

    ObstacleStatistic(final String aName, final int aMaxHp, final int aTier, final String aDescription){
        name = aName;
        maxHp = aMaxHp;
        tier = aTier;
        description = aDescription;
    }
}
