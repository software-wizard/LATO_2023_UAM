package pl.psi.gui;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import pl.psi.Hero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.psi.creatures.BattleUnit;
import pl.psi.ObstaclePlacementList;
import pl.psi.Point;
import pl.psi.creatures.NecropolisFactory;
import pl.psi.creatures.WarMachine;
import pl.psi.warmachines.WarMachineStatistic;
import pl.psi.creatures.Spell;
import pl.psi.creatures.SpellStatistic;
import pl.psi.specialFields.Obstacle;
import pl.psi.specialFields.ObstacleFactory;
import pl.psi.warmachines.WarMachineStats;

public class Start extends Application
{

    public Start()
    {

    }

    static void main( final String[] args )
    {
        launch( args );
    }

    @Override
    public void start( final Stage primaryStage )
    {
        Scene scene = null;
        try
        {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation( Start.class.getClassLoader()
                .getResource( "fxml/main-battle.fxml" ) );
            loader.setController( new MainBattleController( createP1(), createP2(),createObstacles() ) );
            scene = new Scene( loader.load() );
            primaryStage.setScene( scene );
            primaryStage.setX( 5 );
            primaryStage.setY( 5 );
            primaryStage.show();
        }
        catch( final IOException aE )
        {
            aE.printStackTrace();
        }
    }

    private Hero createP2()
    {
        final Hero ret = new Hero( List.of(
                new BattleUnit(new NecropolisFactory().create( true, 1, 5 ) ),
                new BattleUnit(new NecropolisFactory().create(false, 2, 3)),
                new BattleUnit(new WarMachine.Builder().statistic(WarMachineStatistic.BALLISTA).build().parseSkill("Attack", (double)25)),
                new BattleUnit(new WarMachine.Builder().statistic(WarMachineStatistic.FIRST_AID_TENT).build().parseSkill("First Aid", (double)2)),
                new BattleUnit(new WarMachine.Builder().statistic(WarMachineStatistic.CATAPULT).build())
                ),
                List.of(new Spell.spellBuilder().statistic(SpellStatistic.ICE_BOLT).build(), new Spell.spellBuilder().statistic(SpellStatistic.FIREBALL).build(),
                    new Spell.spellBuilder().statistic(SpellStatistic.BLOODLUST).build(),
                    new Spell.spellBuilder().statistic(SpellStatistic.SHIELD).build(),
                    new Spell.spellBuilder().statistic(SpellStatistic.FLY).build(),
                    new Spell.spellBuilder().statistic(SpellStatistic.BLIND).build(),
                    new Spell.spellBuilder().statistic(SpellStatistic.WEAKNESS).build()
        ));
        return ret;
    }

    private Hero createP1()
    {
        final Hero ret = new Hero( List.of(
                new BattleUnit(new NecropolisFactory().create( false, 1, 5 )),
                new BattleUnit(new NecropolisFactory().create(true, 7, 1)),
                new BattleUnit(new WarMachine.Builder().statistic(WarMachineStatistic.FIRST_AID_TENT).build()),
                new BattleUnit(new WarMachine.Builder().statistic(WarMachineStatistic.BALLISTA).build().parseSkill("Ballistics", (double)1).parseSkill("Archery", 1.5).parseSkill("Artillery", (double)50)),
                new BattleUnit(new WarMachine.Builder().statistic(WarMachineStatistic.CATAPULT).build().parseSkill("Ballistics", (double)1))
                ),
                List.of( new Spell.spellBuilder().statistic(SpellStatistic.LIGHTNING_BOLT).build(),
                        new Spell.spellBuilder().statistic(SpellStatistic.FLY).build(),
                        new Spell.spellBuilder().statistic(SpellStatistic.BLIND).build(),
                        new Spell.spellBuilder().statistic(SpellStatistic.WEAKNESS).build(),
                        new Spell.spellBuilder().statistic(SpellStatistic.CURE).build()) );
        return ret;
    }

    private ObstaclePlacementList createObstacles()
    {
        HashMap<Point, Obstacle> aObstaclePlacement = new HashMap<>();
        aObstaclePlacement.put(new Point(3,4),new ObstacleFactory().create("Rock") );
        aObstaclePlacement.put(new Point(6,7),new ObstacleFactory().create("Tree") );
        aObstaclePlacement.put(new Point(10,3),new ObstacleFactory().create("Boulder") );
        aObstaclePlacement.put(new Point(5,5),new ObstacleFactory().create("Spikes") );

        final ObstaclePlacementList ret = new ObstaclePlacementList(aObstaclePlacement
        );
        return ret;
    }

}
