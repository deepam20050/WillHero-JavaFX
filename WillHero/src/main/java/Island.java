public class Island extends GameObject
{
    private double length;

    public Island(double x, double y, double length)
    {
        super(new Vector2D(x,y), new Vector2D(0,0));
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    @Override
    public void if_collides(Hero hero)
    {

    }
}
