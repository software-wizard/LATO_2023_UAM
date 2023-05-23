package pl.psi;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import pl.psi.creatures.BattleUnit;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class Board
{
    private static final int MAX_WITDH = 14;
    private final BiMap< Point, BattleUnit> map = HashBiMap.create();

    public Board( final List< BattleUnit > aBattleUnits1, final List< BattleUnit > aBattleUnits2 )
    {
        addBattleUnits(aBattleUnits1, 0 );
        addBattleUnits(aBattleUnits2, MAX_WITDH );
    }

    private void addBattleUnits( final List< BattleUnit > aBattleUnits, final int aXPosition )
    {
        for( int i = 0; i < aBattleUnits.size(); i++ )
        {
            map.put( new Point( aXPosition, i * 2 + 1 ), aBattleUnits.get( i ) );
        }
    }

    Optional< BattleUnit > getBattleUnit( final Point aPoint )
    {
        return Optional.ofNullable( map.get( aPoint ) );
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

    boolean canMove( final BattleUnit aBattleUnit, final Point aPoint )
    {
        if (map.containsKey(aPoint)) {
            return false;
        }
        final Point oldPosition = getPosition(aBattleUnit);
        return aPoint.distance(oldPosition.getX(), oldPosition.getY()) < aBattleUnit.getMoveRange();
    }

    Point getPosition( BattleUnit aBattleUnit )
    {
        return map.inverse()
            .get(aBattleUnit);
    }
}
