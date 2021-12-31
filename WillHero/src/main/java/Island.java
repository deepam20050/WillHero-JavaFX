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
        this(x,y,length, true);
    }
    protected Island(double x, double y, double length, boolean setUpImageView)
    {
        super(new Vector2D(x,y), new Vector2D(0,0));
        this.length = length;
        backgroundObjects = new ArrayList<ImageView>();
        backgroundObjectPositions = new ArrayList<Double>();

        // Setting up ImageView
        if(setUpImageView)
        {
            imagePath = "file:assets/IslandSprite.png";
            setImage(new Image(imagePath));
            getImageView().setX(x);
            getImageView().setY(y);
            getImageView().setFitWidth(length);
            getImageView().setPreserveRatio(true);
        }
    }

    @Override
    public void updateFrame(double cameraPosition)
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
    {
        boolean hasLanded = false;

        // Checking if the hero has landed on the island
        double xdist = hero.getPosition().getX() - this.getPosition().getX();
        if(xdist >= -hero.getSize() && xdist <= this.getLength())
        {
            double ydist = hero.getPosition().getY() - this.getPosition().getY();
            if(ydist >= -hero.getSize() && ydist < 0)
            {
                hasLanded = true;
            }
        }
        if(hasLanded)
        {
            hero.jump_up();
        }
    }
}
