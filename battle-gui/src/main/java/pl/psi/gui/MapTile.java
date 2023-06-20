package pl.psi.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.File;
import java.net.URI;

class MapTile extends StackPane
{

    private final Rectangle rect;
    private final Label label;

    private final ImageView imageView;

    MapTile( final String aName )
    {
        rect = new Rectangle( 60, 60 );
        rect.setFill( Color.WHITE );
        rect.setStroke( Color.RED );
        getChildren().add( rect );

        imageView = new ImageView();
        imageView.setFitWidth(60);
        imageView.setFitHeight(60);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        getChildren().add(imageView);

        label = new Label( aName );
        getChildren().add( label );
    }

    void setName( final String aName )
    {
        label.setText( aName );
    }

    void setImage(final String imagePath, final String aName) {
        try {
            File imageFile = new File(imagePath);
            URI imageURI = imageFile.toURI();
            Image image = new Image(imageURI.toString());
            imageView.setImage(image);
            label.setVisible(false);
        } catch (Exception e) {
            imageView.setImage(null);
            label.setVisible(true);
        }
    }
    boolean hasImage(String path) {
        File file = new File(path);
        return file.exists() && !file.isDirectory();
    }

    void setBackground( final Color aColor )
    {
        rect.setFill( aColor );
    }
}
