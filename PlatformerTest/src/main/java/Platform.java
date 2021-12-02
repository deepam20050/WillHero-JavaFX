// Platforms are GameObjects that the player can land on.

public class Platform extends GameObject
{
    // Length of the platform
    private double length;

    public Platform(Vector2D pos, double length)
    {
        // Platforms have the position specified by the parameter, and zero velocity.
        super(pos, new Vector2D(0,0));
        this.length = length;
    }

    // Get the length of the platform (Used by Mario object when determining if collision has taken place)
    public double getLength()
    {
        return length;
    }
}
