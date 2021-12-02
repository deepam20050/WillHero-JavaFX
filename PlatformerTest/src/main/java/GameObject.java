// GameObject superclass.
// All game objects that are to be DISPLAYED ON THE SCREEN should inherit this.

// Note that this does not include UI elements like buttons/simple text on the screen

public class GameObject
{
    // Position of the object on the screen, and velocity (pixels per frame)
    private Vector2D position;
    private Vector2D velocity;

    public GameObject(Vector2D pos, Vector2D vel)
    {
        this.position = new Vector2D(pos);
        this.velocity = new Vector2D(vel);
    }

    public Vector2D getPosition()
    {
        return position;
    }
    public Vector2D getVelocity()
    {
        return velocity;
    }

    public void setPosition(double x, double y)
    {
        position.setX(x);
        position.setY(y);
    }
    public void setVelocity(double x, double y)
    {
        velocity.setX(x);
        velocity.setY(y);
    }
}
