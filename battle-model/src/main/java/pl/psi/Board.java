package pl.psi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import pl.psi.creatures.BattleUnit;
import pl.psi.creatures.Creature;
import pl.psi.specialFields.Obstacle;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class Board {
    private static final int MAX_WITDH = 14;
    private final BiMap<Point, Defendable> map = HashBiMap.create();

    public Board( final List< BattleUnit > aBattleUnits1, final List< BattleUnit > aBattleUnits2, final Map<Point, Obstacle> aObstacles )
    {
        addBattleUnits(aBattleUnits1.stream().filter(BattleUnit::isWarMachine).collect(Collectors.toList()), 0 );
        addBattleUnits(aBattleUnits1.stream().filter(BattleUnit::isCreature).collect(Collectors.toList()), 1 );
        addBattleUnits(aBattleUnits2.stream().filter(BattleUnit::isCreature).collect(Collectors.toList()), MAX_WITDH-1 );
        addBattleUnits(aBattleUnits2.stream().filter(BattleUnit::isWarMachine).collect(Collectors.toList()), MAX_WITDH );
        addObstacleByPoint(aObstacles);
    }

    private void addBattleUnits( final List< BattleUnit > aBattleUnits, final int aXPosition )
    {
        for( int i = 0; i < aBattleUnits.size(); i++ )
        {
            map.put( new Point( aXPosition, i * 2 + 1 ), aBattleUnits.get( i ) );
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

    void move( final BattleUnit aBattleUnit, final Point aPoint )
    {
        if( canMove(aBattleUnit, aPoint ) )
        {
            map.inverse()
                .remove(aBattleUnit);
            map.put( aPoint, aBattleUnit);
        }
    }

    public Optional<BattleUnit> getBattleUnit(Point aPoint) {
        return Optional.of(map.get(aPoint))
                .filter(BattleUnit.class::isInstance)
                .map(BattleUnit.class::cast);
    }

    boolean canMove( final BattleUnit aBattleUnit, final Point aPoint )
    {
        if (map.containsKey(aPoint)) {
            if (map.get(aPoint).isTransparent()) {
                final Point oldPosition = getPosition(aBattleUnit);
                return aPoint.distance(oldPosition.getX(), oldPosition.getY()) < aBattleUnit.getMoveRange();
            }
            return false;
        }
        final Point oldPosition = getPosition(aBattleUnit);
        return aPoint.distance(oldPosition.getX(), oldPosition.getY()) < aBattleUnit.getMoveRange();
    }

    Point getPosition(Defendable aDefendable) {
        return map.inverse()
                .get(aDefendable);
    }
}
