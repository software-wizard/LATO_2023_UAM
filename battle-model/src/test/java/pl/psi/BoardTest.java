package pl.psi;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import pl.psi.creatures.BattleUnit;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

class BoardTest
{
    @Test
    void unitsMoveProperly()
    {
        final BattleUnit battleUnit = new BattleUnit(new Creature.Builder().statistic( CreatureStats.builder()
            .moveRange( 5 )
            .build() )
            .build());
        final List<BattleUnit> b1 = List.of(battleUnit);
        final List< BattleUnit > b2 = List.of();
        final Board board = new Board(b1, b2);

        board.move(battleUnit, new Point( 3, 3 ) );

        assertThat( board.getBattleUnit( new Point( 3, 3 ) )
            .isPresent() ).isTrue();
    }

}