public abstract class GameObject
{
    private static int nextID = 0;
    private int id;
    private Vector2D position;
    private Vector2D velocity;
    private boolean active;

    public GameObject(Vector2D position, Vector2D velocity)
    {
        this.id = nextID++;
        this.position = position;
        this.velocity = velocity;
        this.active = true;
    }

    public abstract void if_collides(Hero hero);
    public abstract void updatePosition(double cameraPosition);

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
    }

    // *** TO BE IMPLEMENTED ***
    public boolean equals(GameObject obj) {
        return true;
    }
}
