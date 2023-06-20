package pl.psi.specialFields;

import lombok.Getter;
@Getter
public enum ObstaclesWeight {
    BOULDER("Boulder",7),
    SPIKES("Spikes",8),
    FIREWALL("Firewall",5),
    WEAKNESS_TOTEM("Weakness Totem",4),
    HEALING_TOTEM("Healing Totem",6),
    ROCK("Rock",10),
    TREE("Tree",9);


    private final String name;
    private final int weight;


    ObstaclesWeight(final String name, final int weight) {
        this.name = name;
        this.weight = weight;
    }
}
