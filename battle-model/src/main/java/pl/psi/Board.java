package pl.psi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import pl.psi.creatures.Creature;
import pl.psi.specialFields.Obstacle;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class Board {
    private static final int MAX_WITDH = 14;
    private final BiMap<Point, Defendable> map = HashBiMap.create();

    public Board(final List<Creature> aCreatures1,
                 final List<Creature> aCreatures2,
                 final Map<Point, Obstacle> aObstacles) {
        addCreatures(aCreatures1, 0);
        addCreatures(aCreatures2, MAX_WITDH);
        addObstacleByPoint(aObstacles);
    }

    private void addCreatures(final List<Creature> aCreatures, final int aXPosition) {
        for (int i = 0; i < aCreatures.size(); i++) {
            map.put(new Point(aXPosition, i * 2 + 1), aCreatures.get(i));
        }
    }

    private void addObstacleByPoint(final Map<Point, Obstacle> aObstaclePlacement) {
        for (Point p : aObstaclePlacement.keySet()) {

            Obstacle obstacle = aObstaclePlacement.get(p);
            obstacle.setObstacleRemoveMethod(this::removeObstacle);
            map.put(new Point(p.getX(), p.getY()), obstacle);
        }
    }

    void removeObstacle(Obstacle aObstacle) {
        map.inverse().remove(aObstacle);
    }

    Optional<Defendable> getObject(final Point aPoint) {
        return Optional.ofNullable(map.get(aPoint));
    }

    void move(final Creature aCreature, final Point aPoint) {
        if (canMove(aCreature, aPoint)) {
            map.inverse()
                    .remove(aCreature);
            map.put(aPoint, aCreature);
        }
    }

    public Optional<Creature> getCreature(Point aPoint) {
        return Optional.of(map.get(aPoint))
                .filter(Creature.class::isInstance)
                .map(Creature.class::cast);

    }

    boolean canMove(final Creature aCreature, final Point aPoint) {
        if (map.containsKey(aPoint)) {
            return false;
        }
        final Point oldPosition = getPosition(aCreature);
        return aPoint.distance(oldPosition.getX(), oldPosition.getY()) < aCreature.getMoveRange();
    }

    Point getPosition(Defendable aDefendable) {
        return map.inverse()
                .get(aDefendable);
    }
}
