import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Island extends GameObject
{
    private double length;

    private String imagePath;

    private ArrayList<ImageView> backgroundObjects;
    private ArrayList<Double> backgroundObjectPositions;

    public Island(double x, double y, double length)
    {
        super(new Vector2D(x,y), new Vector2D(0,0));
        this.length = length;

        // Setting up ImageView
        imagePath = "file:assets/IslandSprite.png";
        setImage(new Image(imagePath));
        getImageView().setX(x);
        getImageView().setY(y);
        getImageView().setFitWidth(length);
        getImageView().setPreserveRatio(true);

        backgroundObjects = new ArrayList<ImageView>();
        backgroundObjectPositions = new ArrayList<Double>();
    }

    public void updatePosition(double cameraPosition)
    {
        getImageView().setX(getPosition().getX() - cameraPosition);
        for(int i = 0; i < backgroundObjects.size(); i++)
        {
            backgroundObjects.get(i).setX(getPosition().getX() + backgroundObjectPositions.get(i) - cameraPosition);
        }
    }

    public double getLength() {
        return length;
    }

    public void addBackgroundObject(String imagePath, double x, double height)
    {
        ImageView newElement = new ImageView(new Image(imagePath));
        newElement.setX(getPosition().getX() + x);
        newElement.setY(getPosition().getY() - height);
        newElement.setFitHeight(height);
        newElement.setPreserveRatio(true);

        backgroundObjects.add(newElement);
        backgroundObjectPositions.add(x);
    }

    public ArrayList<ImageView> getBackgroundObjects()
    {
        return backgroundObjects;
    }

    @Override
    public void if_collides(Hero hero)
    {}
}
