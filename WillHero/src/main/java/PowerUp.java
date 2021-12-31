public abstract class PowerUp extends GameObject
{
    private double duration;
    private boolean inUse;
    private Hero equippingHero;

    public PowerUp(double x, double y, double duration)
    {
        super(new Vector2D(x,y), new Vector2D(0,0));
        this.duration = duration;
        this.inUse = false;
    }

    public abstract void usePowerUp();

    public void setEquippingHero(Hero hero)
    {
        equippingHero = hero;
    }
    public Hero getEquippingHero()
    {
        return equippingHero;
    }
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