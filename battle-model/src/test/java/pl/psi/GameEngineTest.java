package pl.psi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

//    @Test
//    void shouldGenerateNeighboursGivenPoint(){
//        final CastleCreatureFactory creatureFactory = new CastleCreatureFactory();
//        final GameEngine gameEngine =
//                new GameEngine( new Hero( List.of( creatureFactory.create( 1, false, 5 ) ) ),
//                        new Hero( List.of( creatureFactory.create( 1, false, 5 ) ) ) );
//
//        List<Point> actual = gameEngine.generateNeigboursList(new Point(0, 0));
//        List<Point> expected = Arrays.asList(new Point(0, 1),
//                new Point(1, 0));
//        Assertions.assertIterableEquals(actual, expected);
//    }
@Test
void shouldGenerateNeighboursGivenPoint(){
    Node n1 = new Node(1,4, 1);;
    List<Node> nodeList = Arrays.asList(new Node(1,3),new Node(1,5),new Node(0, 4),
            new Node(2,4));
    Collections.singletonList(n1.getNeighbourList()).forEach(System.out::println);
    Assertions.assertIterableEquals(n1.getNeighbourList(), nodeList);
}

    @Test
    void shouldMoveToAGivenPoint(){
        final CastleCreatureFactory creatureFactory = new CastleCreatureFactory();
        final GameEngine gameEngine =
                new GameEngine( new Hero( List.of( creatureFactory.create( 1, false, 5 ) ) ),
                            new Hero( List.of( creatureFactory.create( 1, false, 5 ) ) ) );
        final Point p= new Point(1, 4);
        final Point p1= new Point(1, 4);
        final Node n = new Node(1, 4, 0);
        final Node n1 = new Node(1, 4);
        System.out.println(n1.getCost());
        if(n.equals(n1)){
            System.out.println(p1.toString());
        }
        Assertions.assertEquals(n, n1);
    }
}
