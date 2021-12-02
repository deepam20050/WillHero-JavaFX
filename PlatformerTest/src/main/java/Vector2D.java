// Stores x and y coordinates, along with getters/setters
// Used for storing position/velocity

public class Vector2D
{
    private double x;
    private double y;

    public Vector2D(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    public Vector2D(Vector2D v)
    {
        this.x = v.x;
        this.y = v.y;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void setX(double nx)
    {
        this.x = nx;
    }
    public void setY(double ny)
    {
        this.y = ny;
    }
}
