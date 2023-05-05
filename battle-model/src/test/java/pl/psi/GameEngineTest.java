package pl.psi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import pl.psi.creatures.CastleCreatureFactory;






/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class GameEngineTest
{
    @Test
    void shoudWorksHeHe()
    {
        final CastleCreatureFactory creatureFactory = new CastleCreatureFactory();
        final GameEngine gameEngine =
            new GameEngine( new Hero( List.of( creatureFactory.create( 1, false, 5 ) ) ),
                new Hero( List.of( creatureFactory.create( 1, false, 5 ) ) ) );

        gameEngine.attack( new Point( 1, 1 ) );
    }

    @Test
    void shouldGenerateNeighboursGivenPoint(){
        final CastleCreatureFactory creatureFactory = new CastleCreatureFactory();
        final GameEngine gameEngine =
                new GameEngine( new Hero( List.of( creatureFactory.create( 1, false, 5 ) ) ),
                        new Hero( List.of( creatureFactory.create( 1, false, 5 ) ) ) );

        List<Point> actual = gameEngine.generateNeigboursList(new Point(0, 0));
        List<Point> expected = Arrays.asList(new Point(0, 1),
                new Point(1, 0));
        Assertions.assertIterableEquals(actual, expected);
    }
}
