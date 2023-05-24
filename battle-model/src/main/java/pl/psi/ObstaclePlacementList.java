package pl.psi;

import lombok.Getter;
import pl.psi.specialFields.Obstacle;

import java.util.HashMap;

public class ObstaclePlacementList {
    @Getter
    //private Table<Integer, Integer, Obstacle> obstaclePlacement = HashBasedTable.create();
    private HashMap<Point, Obstacle> obstaclePlacement = new HashMap<>();

    public ObstaclePlacementList(final HashMap<Point, Obstacle> aObstaclePlacement) {
        obstaclePlacement = aObstaclePlacement;
    }
}
