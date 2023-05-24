package pl.psi.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.psi.GameEngine;
import pl.psi.Hero;
import pl.psi.Point;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import pl.psi.creatures.Creature;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainBattleController implements PropertyChangeListener
{
    private final GameEngine gameEngine;
    @FXML
    private GridPane gridMap;
    @FXML
    private Button passButton;
    @FXML
    public Button spellButton;
    private Boolean canSpell = false;

    public MainBattleController( final Hero aHero1, final Hero aHero2 )
    {
        gameEngine = new GameEngine( aHero1, aHero2 );
    }

    @FXML
    private void initialize()
    {
        refreshGui();
        gameEngine.addObserver(this);
        spellButton();
    }

    private void refreshGui()
    {
        gridMap.getChildren()
                .clear();
        for( int x = 0; x < 15; x++ )
        {
            for( int y = 0; y < 10; y++ )
            {
                Point currentPoint = new Point( x, y );
                Optional< Creature > creature = gameEngine.getCreature( currentPoint );
                final MapTile mapTile = new MapTile( "" );
                creature.ifPresent( c -> mapTile.setName( c.toString() ) );
                if( gameEngine.isCurrentCreature( currentPoint ) )
                {
                    mapTile.setBackground( Color.GREENYELLOW );
                }
                if( gameEngine.canMove( currentPoint ) )
                {
                    mapTile.setBackground( Color.GREY );
                    mapTile.addEventHandler( MouseEvent.MOUSE_CLICKED, ( e ) -> {
                        gameEngine.move( currentPoint );
                    } );
                }
                if( gameEngine.canAttack( currentPoint ) )
                {
                    mapTile.setBackground( Color.RED );
                    mapTile.addEventHandler( MouseEvent.MOUSE_CLICKED, ( e ) -> {
                        gameEngine.attack( currentPoint );
                    } );
                }
                if ( canSpell && gameEngine.getCreature(currentPoint).isPresent() ) {
                    mapTile.setBackground( Color.BLUE );
                }
                gridMap.add( mapTile, x, y );
            }
        }
        canSpell = false;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        refreshGui();
    }

    private void spellButton() {
        final AtomicBoolean isOpened = new AtomicBoolean();

        spellButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            if (!isOpened.compareAndSet(false, true)) {
                return;
            }
            List<String> spellBook = gameEngine.getSpellBook();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("NewWindow.fxml"));
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
//            stage.setTitle("SPELL BOOK");

            Button closeButton = new Button("X");
            closeButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    isOpened.set(false);
                    stage.close();
                }
            });

            List<Button> spellButtonList = new ArrayList<>();

            for (String name : spellBook) {
                Button spell = new Button(name);
                spell.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        canSpell = true;
                        stage.close();
                        isOpened.set(false);
                        refreshGui();
                    }
                });
                spellButtonList.add(spell);
            }

            spellButtonList.add(closeButton);

            HBox layout = new HBox(10);
            layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
            layout.getChildren().addAll(spellButtonList);
            stage.setScene(new Scene(layout));
            stage.show();
        });
    }
}
