import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Island extends GameObject
{
    private double length;

    private transient ArrayList<ImageView> backgroundObjects;
//    private ArrayList<Double> backgroundObjectPositions;
    private ArrayList<IslandBackgroundObjDetails> backgroundObjDetails;

    public Island(double x, double y, double length)
    {
        this(x,y,length, true);
    }
    protected Island(double x, double y, double length, boolean setUpImageView)
    {
        super(new Vector2D(x,y), new Vector2D(0,0));
        this.length = length;
        backgroundObjects = new ArrayList<ImageView>();
//        backgroundObjectPositions = new ArrayList<Double>();
        backgroundObjDetails = new ArrayList<IslandBackgroundObjDetails>();

        // Setting up ImageView
        if(setUpImageView)
        {
            String imagePath = "file:assets/IslandSprite.png";
            this.setImagePath(imagePath);
            this.loadImageView();
        }
    }

    @Override
    public void loadImageView()
    {
        super.loadImageView();
        getImageView().setX(this.getPosition().getX());
        getImageView().setY(this.getPosition().getY());
        getImageView().setFitWidth(length);
        getImageView().setPreserveRatio(true);
    }

    @Override
    public void updateFrame(double cameraPosition)
    {
        getImageView().setX(getPosition().getX() - cameraPosition);
        for(int i = 0; i < backgroundObjects.size(); i++)
        {
//            backgroundObjects.get(i).setX(getPosition().getX() + backgroundObjectPositions.get(i) - cameraPosition);
            backgroundObjects.get(i).setX(getPosition().getX() + backgroundObjDetails.get(i).getPosition() - cameraPosition);
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
        backgroundObjDetails.add(new IslandBackgroundObjDetails(imagePath, x, height));
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

    public void loadBackgroundImageViews()
    {
        super.loadImageView();
        if(backgroundObjects == null)
            backgroundObjects = new ArrayList<ImageView>();
        backgroundObjects.removeAll(backgroundObjects);

        for(IslandBackgroundObjDetails details: backgroundObjDetails)
        {
            ImageView newElement = new ImageView(new Image(details.getImagePath()));
            newElement.setX(getPosition().getX() + details.getPosition());
            newElement.setY(getPosition().getY() - details.getHeight());
            newElement.setFitHeight(details.getHeight());
            newElement.setPreserveRatio(true);

            backgroundObjects.add(newElement);
        }
    }

    public class IslandBackgroundObjDetails implements Serializable
    {
        private String imagePath;
        private double position;
        private double height;

        public IslandBackgroundObjDetails(String imagePath, double position, double height)
        {
            this.imagePath = imagePath;
            this.position = position;
            this.height = height;
        }

        public String getImagePath()
        {
            return imagePath;
        }
        public double getPosition()
        {
            return position;
        }
        public double getHeight()
        {
            return height;
        }
    }
}