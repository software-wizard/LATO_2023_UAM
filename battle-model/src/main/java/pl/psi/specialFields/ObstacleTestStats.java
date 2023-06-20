package pl.psi.specialFields;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ObstacleTestStats implements  ObstacleStatisticIf{
    private final String name;
    private final int maxHp;
    private final String description;
    private final Integer weight;
}
