package pl.psi.gui;

import javafx.application.Platform;
import pl.psi.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import pl.psi.creatures.BattleUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Optional;

public class MainBattleController implements PropertyChangeListener {
    private final GameEngine gameEngine;
    @FXML
    private GridPane gridMap;
    @FXML
    private Button passButton;

    public MainBattleController(final Hero aPlayer1, final Hero aPlayer2, final ObstaclePlacementList obstaclesList) {
        gameEngine = new GameEngine(aPlayer1, aPlayer2, obstaclesList);
    }

    @FXML
    private void initialize()
    {
        passButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {gameEngine.pass();});
        refreshGui();
        gameEngine.addObserver(this);
    }

    private void refreshGui()
    {
        Platform.runLater(() -> {
            gridMap.getChildren()
                    .clear();
            for (int x = 0; x < 15; x++) {
                for (int y = 0; y < 10; y++) {
                    Point currentPoint = new Point(x, y);
                    Optional<Defendable> object = gameEngine.getObject(currentPoint);
                    final MapTile mapTile = new MapTile("");
                    object.ifPresent(b -> mapTile.setName(b.toString()));
                    if (gameEngine.isCurrentBattleUnit(currentPoint)) {
                        mapTile.setBackground(Color.GREENYELLOW);
                    }
                    if (gameEngine.canMove(currentPoint)) {
                        mapTile.setBackground(Color.GREY);
                        mapTile.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                            gameEngine.move(currentPoint);
                        });
                    }
                    if (gameEngine.canAttack(currentPoint)) {
                        mapTile.setBackground(Color.RED);
                        mapTile.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                            gameEngine.attack(currentPoint);
                        });
                    }
                    if (gameEngine.canHeal(currentPoint)) {
                        mapTile.setBackground(Color.GREEN);
                        mapTile.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                            gameEngine.heal(currentPoint);
                        });
                    }
                    gridMap.add(mapTile, x, y);
                }
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        refreshGui();
    }
}
