public abstract class PowerUp extends GameObject
{
    private double duration;
    private boolean inUse;

    public PowerUp(double x, double y, double duration)
    {
        super(new Vector2D(x,y), new Vector2D(0,0));
        this.duration = duration;
        this.inUse = false;
    }

    public abstract void usePowerUp();

    public double getDuration()
    {
        return duration;
    }
    public boolean isInUse()
    {
        return inUse;
    }

    public void decrementDuration(double amount)
    {
        this.duration -= amount;
    }
}