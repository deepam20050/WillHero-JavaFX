public class ThrowingKnives extends Weapon
{
    public ThrowingKnives(double x, double y)
    {
        super(x,y);
    }

    @Override
    public void use_weapon()
    {
        System.out.println("Knife go WAAAA");
    }

    @Override
    public void updatePosition(double cameraPosition)
    {}
    @Override
    public void if_collides(Hero hero)
    {}
}
