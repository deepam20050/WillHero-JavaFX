public class GameObject
{
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
