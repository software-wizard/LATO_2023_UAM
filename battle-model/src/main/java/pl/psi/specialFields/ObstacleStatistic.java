package pl.psi.specialFields;

import lombok.Getter;

@Getter
public enum ObstacleStatistic implements ObstacleStatisticIf {
    //special fields
    ROCK("Rock", 4, 1,Integer.MAX_VALUE,"basic rock obstacle"),
    TREE("Tree", 4, 2, Integer.MAX_VALUE, "freestanding tree obstacle"),
    BOULDER("Boulder", 7, 3, Integer.MAX_VALUE,"a rock but much bigger and harder"),
    SPIKES("Spikes", 4, 4,500, "apply damage when being stomped at");
    //firewall


    private final String name;
    private final int maxHp;
    private final int tier;
    private final String description;
    private final Integer weight;

    ObstacleStatistic(final String aName, final int aMaxHp,
                      final int aTier, final Integer aWeight,
                      final String aDescription
                      ){
        name = aName;
        maxHp = aMaxHp;
        tier = aTier;
        description = aDescription;
        weight = aWeight;
    }
}
