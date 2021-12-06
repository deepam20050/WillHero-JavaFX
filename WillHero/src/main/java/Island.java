import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Island extends GameObject
{
    private double length;

    // ImageView Attributes
    private String imagePath;
    private ImageView imageView;

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
    }

    public double getLength() {
        return length;
    }

    public ImageView getImageView()
    {
        return imageView;
    }

    @Override
    public void if_collides(Hero hero)
    {

    }
}
