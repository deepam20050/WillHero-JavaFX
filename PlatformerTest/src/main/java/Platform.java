public class Platform extends GameObject
{
    private double length;

    public Platform(Vector2D pos, double length)
    {
        super(pos, new Vector2D(0,0));
        this.length = length;
    }

    public double getLength()
    {
        return length;
    }
}
