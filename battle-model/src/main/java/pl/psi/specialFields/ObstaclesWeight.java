package pl.psi.specialFields;

public enum ObstaclesWeight {
    BOULDER(7),
    SPIKES(8),
    FIREWALL(5),
    WEAKNESS_TOTEM(4),
    HEALING_TOTEM(6);

    private final int weight;

    ObstaclesWeight(final int weight) {
        this.weight = weight;
    }
}
