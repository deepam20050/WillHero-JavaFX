public class Helmet extends GameObject
{
    private Weapon[] weapons;
    private int selected_weapon_index;

    public Helmet(double x, double y)
    {
        super(new Vector2D(x,y), new Vector2D(0,0));
        weapons = new Weapon[2];
        weapons[0] = new Sword(x,y);
        weapons[1] = new ThrowingKnives(x,y);
        selected_weapon_index = -1;
    }

    public Weapon getWeapon(int i)
    {
        return weapons[i];
    }

    public Weapon getCurrentWeapon()
    {
        return weapons[selected_weapon_index];
    }

    public int getSelectedWeaponIndex()
    {
        return selected_weapon_index;
    }

    public void setSelectedWeapon(int i)
    {
        if(weapons[i].getLevel() > 0)
        {
            selected_weapon_index = i;
        }
    }

    @Override
    public void updatePosition(double cameraPosition)
    {}
    @Override
    public void if_collides(Hero hero)
    {}
}
