package pl.psi;

import java.util.*;

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
    private final BiMap<Point, Defendable> overrideMap = HashBiMap.create();

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
            final Point oldPoint = map.inverse().get(aCreature);
            overridePositionOnMap(map, overrideMap, aPoint);
            map.inverse()
                    .remove(aCreature);
            map.put(aPoint, aCreature);
            overridePositionOnMap(overrideMap, map, oldPoint);
        }
    }

    public Optional<Creature> getCreature(Point aPoint) {
        return Optional.of(map.get(aPoint))
                .filter(Creature.class::isInstance)
                .map(Creature.class::cast);
    }

    boolean canMove(final Creature aCreature, final Point aPoint) {
        if (map.containsKey(aPoint)) {
            if (map.get(aPoint).isTransparent()){
                final Point oldPosition = getPosition( aCreature );
                return aPoint.distance( oldPosition.getX(), oldPosition.getY() ) < aCreature.getMoveRange();
            }
            return false;
        }
        final Point oldPosition = getPosition(aCreature);
        return aPoint.distance(oldPosition.getX(), oldPosition.getY()) < aCreature.getMoveRange();
    }

    void overridePositionOnMap(BiMap<Point, Defendable> fromMap, BiMap<Point, Defendable> toMap, Point ref){
        if (fromMap.containsKey(ref)){
            toMap.put(ref, fromMap.get(ref));
            fromMap.remove(ref);
        }
    }

    boolean objectsStandAtSamePoint(Point aPoint){
        if (map.containsKey(aPoint) && overrideMap.containsKey(aPoint)){
            return true;
        }
        return false;
    }

    void performOnTouch(Point aPoint){
        if (objectsStandAtSamePoint(aPoint)){
            this.overrideMap.get(aPoint).applyEffectOnTouch(this.map.get(aPoint));
        }
    }

    Point getPosition(Defendable aDefendable) {
        return map.inverse()
                .get(aDefendable);
    }
}
