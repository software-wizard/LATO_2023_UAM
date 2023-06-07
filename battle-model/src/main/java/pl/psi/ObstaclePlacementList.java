package pl.psi;

import lombok.Getter;
import pl.psi.specialFields.Obstacle;

import java.util.HashMap;
import java.util.Map;

public class ObstaclePlacementList {
    @Getter
    //private Table<Integer, Integer, Obstacle> obstaclePlacement = HashBasedTable.create();
    private Map<Point, Obstacle> obstaclePlacement = new HashMap<>();

    public ObstaclePlacementList(final Map<Point, Obstacle> aObstaclePlacement) {
        obstaclePlacement = aObstaclePlacement;
    }
}
