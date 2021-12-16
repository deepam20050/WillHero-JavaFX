public class Sword extends Weapon
{
    Sword(double x, double y)
    {
        super(x,y);
    }

    @Override
    public void use_weapon()
    {
        System.out.println("Sword go WHOOOSH");
    }

    @Override
    public void updatePosition(double cameraPosition)
    {}
    @Override
    public void if_collides(Hero hero)
    {}
}
