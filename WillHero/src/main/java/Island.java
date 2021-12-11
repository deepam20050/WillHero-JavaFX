import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Island extends GameObject
{
    private double length;

    // ImageView Attributes
    private String imagePath;
    private ImageView imageView;

    private ArrayList<ImageView> backgroundObjects;

    public Island(double x, double y, double length)
    {
        super(new Vector2D(x,y), new Vector2D(0,0));
        this.length = length;

        // Setting up ImageView
        imagePath = "file:assets/IslandSprite.png";
        imageView = new ImageView(new Image(imagePath));
        imageView.setX(x);
        imageView.setY(y);
        imageView.setFitWidth(length);
        imageView.setPreserveRatio(true);

        backgroundObjects = new ArrayList<ImageView>();
    }

    public double getLength() {
        return length;
    }

    public ImageView getImageView()
    {
        return imageView;
    }

    public void addBackgroundObject(String imagePath, double x, double height)
    {
        ImageView newElement = new ImageView(new Image(imagePath));
        newElement.setX(getPosition().getX() + x);
        newElement.setY(getPosition().getY() - height);
        newElement.setFitHeight(height);
        newElement.setPreserveRatio(true);

        backgroundObjects.add(newElement);
    }

    public ArrayList<ImageView> getBackgroundObjects()
    {
        return backgroundObjects;
    }

    @Override
    public void if_collides(Hero hero)
    {

    }
}
