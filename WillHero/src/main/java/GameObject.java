import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class GameObject
{
    private static int nextID = 0;
    private int id;
    private Vector2D position;
    private Vector2D velocity;

    // ImageView and Image are now part of GameObject as they are needed in every object
    private ImageView imageView;
    private Image image;

    private boolean active;

    public GameObject()
    {
        this(new Vector2D(0,0), new Vector2D(0,0));
    }
    public GameObject(Vector2D position, Vector2D velocity)
    {
        this.id = nextID++;
        this.position = position;
        this.velocity = velocity;
        this.active = true;

        this.image = null;
        this.imageView = new ImageView();
        imageView.setImage(image);
    }

    public abstract void if_collides(Hero hero);
    public abstract void updatePosition(double cameraPosition);

    public void setImage(Image image) {
        this.image = image;
        if(active)
            imageView.setImage(image);
    }
    public ImageView getImageView() {
        return imageView;
    }
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public int getId()
    {
        return id;
    }

    public Vector2D getPosition() {
        return position;
    }
    public Vector2D getVelocity() {
        return velocity;
    }
    public void setPosition(Vector2D pos) {
        this.position.setX(pos.getX());
        this.position.setY(pos.getY());
    }
    public void setPosition(double x, double y) {
        position.setX(x);
        position.setY(y);
    }
    public void setVelocity(Vector2D vel) {
        this.velocity.setX(vel.getX());
        this.position.setY(vel.getY());
    }
    public void setVelocity(double x, double y) {
        velocity.setX(x);
        velocity.setY(y);
    }

    public boolean isActive()
    {
        return active;
    }
    public void setActive(boolean active)
    {
        this.active = active;
        // Add/Remove image based on if active
        if(active)
            imageView.setImage(image);
        else
            imageView.setImage(null);
    }

    // *** TO BE IMPLEMENTED ***
    public boolean equals(GameObject obj) {
        return true;
    }
}
