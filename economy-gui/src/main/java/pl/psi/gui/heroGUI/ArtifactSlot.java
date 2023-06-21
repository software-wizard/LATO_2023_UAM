package pl.psi.gui.heroGUI;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ArtifactSlot extends ImageView {
    private Artifact.Type type;
    private Artifact artifact;
    private Image emptySlotIcon;

    public ArtifactSlot(Artifact.Type type) {
        this.type = type;
        this.artifact = null;
        this.emptySlotIcon = new Image(HeroGUI.class.getClassLoader().getResource("artifacts/emptySlot.png").toString());

        setImage(emptySlotIcon);
        setFitHeight(43);
        setFitWidth(43);
        setPickOnBounds(true);
        setPreserveRatio(true);

    }

    public Artifact.Type getType() {
        return type;
    }

    public boolean isOccupied() {
        return artifact != null;
    }

    public void equip(Artifact artifact) {
        this.artifact = artifact;
        setImage(new Image(HeroGUI.class.getClassLoader().getResource("artifacts/" + artifact.getName() + ".jpg").toString()));
    }

    public Artifact unequip() {
        if (artifact != null) {
            Artifact unequippedArtifact = artifact;
            artifact = null;
            setImage(emptySlotIcon);
            return unequippedArtifact;
        }
        return null;
    }

    public void positionInGridPane(GridPane gridPane) {
        switch (type) {
            case HEAD:
                GridPane.setConstraints(this, 1, 0);
                break;
            case NECK:
                GridPane.setConstraints(this, 1, 1);
                break;
            case TORSO:
                GridPane.setConstraints(this, 1, 2);
                break;
            case SHOULDERS:
                GridPane.setConstraints(this, 1, 3);
                break;
            case RIGHT_HAND:
                GridPane.setConstraints(this, 0, 1);
                break;
            case LEFT_HAND:
                GridPane.setConstraints(this, 2, 1);
                break;
            case FINGERS:
                GridPane.setConstraints(this, 0, 2);
                break;
            case FEET:
                GridPane.setConstraints(this, 2, 2);
                break;
            case MISCELLANEOUS:
                GridPane.setConstraints(this, 0, 3);
                break;
            case BALLISTA:
                GridPane.setConstraints(this, 2, 3);
                break;
            case AMMO_CART:
                GridPane.setConstraints(this, 0, 4);
                break;
            case FIRST_AID_TENT:
                GridPane.setConstraints(this, 1, 4);
                break;
            case CATAPULT:
                GridPane.setConstraints(this, 2, 4);
                break;
            case SPELL_BOOK:
                GridPane.setConstraints(this, 1, 5);
                break;
            default:
                break;
        }
        gridPane.getChildren().add(this);
    }
}