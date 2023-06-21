package pl.psi.gui.heroGUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.util.*;

public class HeroGUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    private Node getNodeByRowColumnIndex(int row, int column, GridPane gridPane) {
        for (Node node : gridPane.getChildren()) {
            int nodeColumn = GridPane.getColumnIndex(node) == null ? 0 : GridPane.getColumnIndex(node);
            int nodeRow = GridPane.getRowIndex(node) == null ? 0 : GridPane.getRowIndex(node);
            if (nodeColumn == column && nodeRow == row) {
                return node;
            }
        }
        return null;
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Artifact Window");

        Hero hero = new Hero("Knight");


        Effect effect_apoh = new Effect();
        effect_apoh.addEffect(" units_immune_curse", 1);

        Effect effect_ardft = new Effect();
        effect_ardft.addEffect("attack", 2);
        effect_ardft.addEffect("defense", 2);

        Effect effect_adg = new Effect();
        effect_adg.addEffect("spell_power", 1);
        effect_adg.addEffect("knowledge", 1);


        Effect effect_arov = new Effect();
        effect_arov.addEffect("units_health", 1);


        Effect effect_ash2 = new Effect();
        effect_ash2.addEffect("all_spells_5th", 1);

        Effect effect_airbook = new Effect();
        effect_airbook.addEffect("air_spells", 5);

        Effect effect_earthbook = new Effect();
        effect_earthbook.addEffect("earth_spells", 5);

        Effect effect_firebook = new Effect();
        effect_firebook.addEffect("fire_spells", 5);

        Effect effect_waterbook = new Effect();
        effect_waterbook.addEffect("water_spells", 5);

        Effect effect_acoc = new Effect();
        effect_acoc.addEffect("spell_duration", 3);

        Effect effect_alsoc = new Effect();
        effect_alsoc.addEffect("attack", 4);
        effect_alsoc.addEffect("defense", 4);
        effect_alsoc.addEffect("spell_power", 4);
        effect_alsoc.addEffect("knowledge", 4);


        Effect effect_apoc = new Effect();
        effect_apoc.addEffect("luck", 3);
        effect_apoc.addEffect("morale", 3);
        Effect effect_acof = new Effect();
        effect_acof.addEffect("luck", 1);
        Effect effect_aboc = new Effect();
        effect_aboc.addEffect("morale", 1);

        Artifact[] artifacts = {
                new Artifact("apoh", "Pendant of Holiness",effect_apoh, Artifact.Type.NECK, 1000, Artifact.RareClass.TREASURE),
                new Artifact("ardft","Red Dragon Flame Tongue", effect_ardft, Artifact.Type.RIGHT_HAND,4000, Artifact.RareClass.MINOR),
                new Artifact("adg","Dragonbone Greaves", effect_adg, Artifact.Type.FEET,100, Artifact.RareClass.TREASURE),
                new Artifact("arov","Ring of Vitality", effect_arov, Artifact.Type.FINGERS,100, Artifact.RareClass.TREASURE),
                new Artifact("ash2","Spellbinder's Hat", effect_ash2, Artifact.Type.HEAD,100, Artifact.RareClass.RELIC),
                new Artifact("airbook","Tome of Air Magic", effect_airbook, Artifact.Type.SPELL_BOOK,2543, Artifact.RareClass.RELIC),
                new Artifact("earthbook","Tome of Earth Magic", effect_earthbook, Artifact.Type.SPELL_BOOK,2355, Artifact.RareClass.RELIC),
                new Artifact("firebook","Tome of Fire Magic", effect_firebook, Artifact.Type.SPELL_BOOK,1355, Artifact.RareClass.RELIC),
                new Artifact("waterbook","Tome of Water Magic", effect_waterbook, Artifact.Type.SPELL_BOOK,6543, Artifact.RareClass.RELIC),
                new Artifact("acoc","Cape of Conjuring", effect_acoc, Artifact.Type.SHOULDERS,2345, Artifact.RareClass.TREASURE),
                new Artifact("alsoc","Lion's Shield of Courage", effect_alsoc, Artifact.Type.LEFT_HAND,1346, Artifact.RareClass.RELIC),
                new Artifact("apoc","Pendant of Courage", effect_apoc, Artifact.Type.NECK,1146, Artifact.RareClass.MAJOR),
                new Artifact("acof","Clover of Fortune", effect_acof, Artifact.Type.MISCELLANEOUS,1326, Artifact.RareClass.TREASURE),
                new Artifact("aboc","Badge of Courage", effect_aboc, Artifact.Type.MISCELLANEOUS,3460, Artifact.RareClass.TREASURE),
        };

        GridPane mainGridPane = new GridPane();
        mainGridPane.setHgap(10);
        mainGridPane.setVgap(10);
        mainGridPane.setPadding(new Insets(0, 10, 0, 10));

        ImageView heroImage = new ImageView(HeroGUI.class.getClassLoader().getResource("artifacts/papdoll.jpg").toString());
        mainGridPane.add(heroImage, 0, 0);


        GridPane artifactsGridPane = new GridPane();
        artifactsGridPane.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        artifactsGridPane.setHgap(10);
        artifactsGridPane.setVgap(10);
        artifactsGridPane.setPadding(new Insets(0, 10, 0, 10));
        List<ImageView> backpackArtifacts = new ArrayList<>();
        HashMap<Artifact, ImageView> artifactImages = new HashMap<>();

        ArtifactSlot[] artifactSlots = new ArtifactSlot[Artifact.Type.values().length];


        GridPane backpackGridPane = new GridPane();
        backpackGridPane.setHgap(1);
        backpackGridPane.setVgap(1);
        ScrollPane scrollPane = new ScrollPane(backpackGridPane);
        scrollPane.setPrefSize(180, 200);

        VBox backpackVBox = new VBox();
        backpackVBox.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        backpackVBox.getChildren().addAll(new Label("Backpack"), scrollPane);

        VBox statsVBox = new VBox();
        statsVBox.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        statsVBox.setSpacing(10);
        statsVBox.setPadding(new Insets(10, 10, 10, 10));







        for (int i = 0; i < artifactSlots.length; i++) {
            artifactSlots[i] = new ArtifactSlot(Artifact.Type.values()[i]);

            int colIndex = i % 2;
            int rowIndex = i / 2;
            artifactsGridPane.add(artifactSlots[i], colIndex, rowIndex);







            artifactSlots[i].setOnMouseClicked(e -> {
                ArtifactSlot slot = (ArtifactSlot) e.getSource();
                if (slot.isOccupied()) {
                    Artifact unequippedArtifact = slot.unequip();


                    ImageView artifactImage = artifactImages.get(unequippedArtifact);
                    hero.removeArtifact(unequippedArtifact);
                    updateStats(hero, statsVBox);

                    int columnIndex = -1;
                    int rowIndex2 = -1;
                    boolean emptySlotFound = false;

                    int maxRows = Math.max(1, backpackGridPane.getRowCount());

                    for (int r = 0; r < maxRows; r++) {
                        for (int c = 0; c < 4; c++) {
                            Node node = getNodeByRowColumnIndex(r, c, backpackGridPane);
                            if (node == null) {
                                columnIndex = c;
                                rowIndex2 = r;
                                emptySlotFound = true;
                                break;
                            }
                        }
                        if (emptySlotFound) {
                            break;
                        }
                    }

                    if (!emptySlotFound) {
                        columnIndex = 0;
                        rowIndex2 = maxRows;
                    }


                    if (columnIndex != -1 && rowIndex2 != -1) {
                        backpackGridPane.add(artifactImage, columnIndex, rowIndex2);

                    }

                }
            });
        }

        int artifactCount = 0;

        for (Artifact artifact : artifacts) {
            ImageView artifactImage = new ImageView(HeroGUI.class.getClassLoader().getResource("artifacts/" + artifact.getName() + ".jpg").toString());
            artifactImage.setFitHeight(43);
            artifactImage.setFitWidth(43);
            artifactImage.setUserData(artifact);


            artifactImages.put(artifact, artifactImage);

            int columnIndex = artifactCount % 4;
            int rowIndex = artifactCount / 4;


            backpackGridPane.add(artifactImage, columnIndex, rowIndex);


            artifactCount++;

            artifactImage.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                if (artifactImage.getUserData() != null) {

                    Artifact artifact2 = (Artifact) artifactImage.getUserData();

                    Artifact.Type targetType = artifact2.getType();
                    ArtifactSlot targetSlot = artifactSlots[targetType.ordinal()];
                    System.out.println("Slot " + targetSlot.getType() + " is occupied: " + targetSlot.isOccupied());
                    if (!targetSlot.isOccupied()) {
                        targetSlot.equip(artifact2);
                        backpackGridPane.getChildren().remove(artifactImage);
                        hero.addArtifact(artifact);
                        updateStats(hero, statsVBox);
                    } else {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Slot Occupied");
                        alert.setHeaderText(null);
                        alert.setContentText("The slot is already occupied. Unequip the current artifact first.");
                        alert.showAndWait();
                    }
                    System.out.println("Slot " + targetSlot.getType() + " is occupied: " + targetSlot.isOccupied());
                }
            });
        }

        Image bgImage = new Image(HeroGUI.class.getClassLoader().getResource("artifacts/background.png").toString());


        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);


        BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);


        mainGridPane.setBackground(new Background(backgroundImage));
        artifactsGridPane.setBackground(new Background(backgroundImage));


        Image bgImageBackpack = new Image(HeroGUI.class.getClassLoader().getResource("artifacts/background.png").toString());
        BackgroundImage backgroundImageBackpack = new BackgroundImage(bgImageBackpack, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        backpackGridPane.setBackground(new Background(backgroundImageBackpack));
        backpackVBox.setBackground(new Background(backgroundImageBackpack));

        mainGridPane.setBackground(new Background(backgroundImage));
        artifactsGridPane.setBackground(new Background(backgroundImage));
        backpackVBox.setBackground(new Background(backgroundImage));


        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;");


        backpackGridPane.setBackground(new Background(backgroundImageBackpack));


        backpackGridPane.setStyle("-fx-background-color: transparent;");
        for (Node node : backpackGridPane.getChildren()) {
            if (node instanceof ImageView) {
                ((ImageView) node).setStyle("-fx-background-color: transparent;");
            }
        }


        backpackGridPane.setGridLinesVisible(true);








        Image bgImageStats = new Image(HeroGUI.class.getClassLoader().getResource("artifacts/background.png").toString());
        BackgroundImage backgroundImageStats = new BackgroundImage(bgImageStats, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        statsVBox.setBackground(new Background(backgroundImageStats));
        updateStats(hero, statsVBox);
        mainGridPane.add(statsVBox, 3, 0);





        mainGridPane.add(artifactsGridPane, 1, 0);
        mainGridPane.add(backpackVBox, 2, 0);

        Scene scene = new Scene(mainGridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private Map<String, Label> statLabels = new HashMap<>();

    private void updateStats(Hero hero, VBox statsVBox) {

        Set<Map.Entry<String, Integer>> statsSet = hero.getAllStats();


        for (Map.Entry<String, Integer> entry : statsSet) {
            String statName = entry.getKey();
            Integer statValue = entry.getValue();

            if (statValue == 0) {

                Label statLabel = statLabels.remove(statName);
                if (statLabel != null) {
                    statsVBox.getChildren().remove(statLabel);
                }
                continue;
            }


            Label statLabel = statLabels.get(statName);
            if (statLabel == null) {

                statLabel = new Label();
                statLabel.setTextFill(Color.WHITE);
                statsVBox.getChildren().add(statLabel);

                statLabels.put(statName, statLabel);
            }

            statLabel.setText(statName + ": " + statValue);
        }
    }


}