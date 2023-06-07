package pl.psi.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.psi.GameEngine;
import pl.psi.Hero;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import pl.psi.Point;
import pl.psi.creatures.Creature;
import pl.psi.creatures.Spell;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainBattleController implements PropertyChangeListener {
    private final GameEngine gameEngine;
    @FXML
    private GridPane gridMap;
    @FXML
    private Button passButton;
    @FXML
    public Button spellButton;
    private Boolean isSpellSelected = false;
    private Spell spellToReturn;

    private final AtomicBoolean canRefresh = new AtomicBoolean(true);

    private Point destinationPoint;

    public MainBattleController(final Hero aHero1, final Hero aHero2) {
        gameEngine = new GameEngine(aHero1, aHero2);
    }

    @FXML
    private void initialize() {
        refreshGui();
        gameEngine.addObserver(this);
        prepareSpellbookWindow();
    }

    private void refreshGui() {
        gridMap.getChildren()
                .clear();
        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 10; y++) {
                Point currentPoint = new Point(x, y);
                Optional<Creature> creature = gameEngine.getCreature(currentPoint);
                final MapTile mapTile = new MapTile("");
                creature.ifPresent(c -> mapTile.setName(c.toString()));
                if (gameEngine.isCurrentCreature(currentPoint)) {
                    mapTile.setBackground(Color.GREENYELLOW);

                }
                if (gameEngine.canMove(currentPoint)) {

                    mapTile.setBackground(Color.GREY);

                    if (canRefresh.get()) {
                        mapTile.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                            gameEngine.move(currentPoint);
                            canRefresh.set(false);
                            destinationPoint = currentPoint;

                        });
                    }
                }
                if (isSpellSelected && gameEngine.getCreature(currentPoint).isPresent()) {
                    mapTile.setBackground(Color.BLUE);
                    mapTile.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                        gameEngine.castSpell(currentPoint, spellToReturn);
                    });
                } else if (gameEngine.canAttack(currentPoint)) {
                    mapTile.setBackground(Color.RED);
                    mapTile.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                        gameEngine.attack(currentPoint);
                    });
                }

                if (gameEngine.canAttack(currentPoint)) {
                    mapTile.setBackground(Color.RED);
                    mapTile.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                        gameEngine.attack(currentPoint);
                    });
                }
                gridMap.add(mapTile, x, y);
            }

        }
        isSpellSelected = false;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(this::refreshGui);
        if (evt.getNewValue().equals(destinationPoint)) {
            canRefresh.set(true);
        }
    }

    private void prepareSpellbookWindow() {
        final AtomicBoolean isOpened = new AtomicBoolean();

        spellButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            if (!isOpened.compareAndSet(false, true)) {
                return;
            }
            List<Spell> spellBook = gameEngine.getSpellBook();
//            FXMLLoader fxmlLoader = new FXMLLoader();
//            fxmlLoader.setLocation(getClass().getResource("NewWindow.fxml"));
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Spell Book");

            Button closeButton = new Button("X");
            closeButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    isOpened.set(false);
                    stage.close();
                }
            });

            List<Button> spellButtonList = new ArrayList<>();

            for (Spell spell : spellBook) {
                Button spellButton = new Button(spell.getName());
                try {
                    Image image = new Image("imagesSpell/" + spell.getName() + ".png");
                    ImageView imageView = new ImageView(image);
                    spellButton.setGraphic(imageView);
                    spellButton.setContentDisplay(ContentDisplay.TOP);
                } catch (IllegalArgumentException ex) {
                    ex.printStackTrace();
                }

                spellButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        isSpellSelected = true;
                        stage.close();
                        isOpened.set(false);
                        refreshGui();
                        spellToReturn = spell;
                    }
                });
                spellButtonList.add(spellButton);
            }

            GridPane spellBookGrid = new GridPane();
            spellBookGrid.setMinSize(350, 200);
            spellBookGrid.setPadding(new Insets(30, 10, 10, 10));
            spellBookGrid.setVgap(5);
            spellBookGrid.setHgap(5);
            spellBookGrid.setAlignment(Pos.CENTER);

            int gridWidth = (int) Math.ceil(Math.sqrt(spellButtonList.size()));
            int counter = 0;

            for (int i = 0; i < gridWidth; i++) {
                for (int k = 0; k < gridWidth; k++) {
                    if (counter < spellButtonList.size()) {
                        spellBookGrid.add(spellButtonList.get(counter), k, i);
                        counter++;
                    }
                }
            }

            spellBookGrid.add(closeButton, gridWidth - 1, gridWidth);
            GridPane.setHalignment(closeButton, HPos.RIGHT);

            stage.setScene(new Scene(spellBookGrid));
            stage.show();
        });
    }
}
