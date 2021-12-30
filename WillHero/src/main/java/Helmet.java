import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Helmet extends GameObject
{
    private Weapon[] weapons;
    private int selected_weapon_index;
    private ArrayList<Projectile> launchedProjectiles;
    private Hero hero;

    public Helmet(double x, double y, Hero hero)
    {
        super(new Vector2D(x,y), new Vector2D(0,0));
        this.hero = hero;
        weapons = new Weapon[2];
        weapons[0] = new Sword(x,y,this);
        weapons[1] = new ThrowingKnives(x,y,this);
        selected_weapon_index = -1;

        launchedProjectiles = new ArrayList<Projectile>();
    }

    public void addWeapon(Weapon weapon)
    {
        if(weapon instanceof Sword)
        {
            weapons[0].incrementLevel();
            setSelectedWeapon(0);
        }
        else if(weapon instanceof ThrowingKnives)
        {
            weapons[1].incrementLevel();
            setSelectedWeapon(1);
        }
    }

    public void launchProjectile(Projectile projectile)
    {
        launchedProjectiles.add(projectile);
    }

    public void updateProjectiles(double cameraPosition)
    {
        for(int i = 0; i < launchedProjectiles.size(); i++)
        {
            Projectile projectile = launchedProjectiles.get(i);
            if(projectile.isActive())
            {
                projectile.updateFrame(cameraPosition);
            }
        }
    }

    public Weapon getWeapon(int i)
    {
        return weapons[i];
    }

    public Weapon getCurrentWeapon()
    {
        if(selected_weapon_index < 0)
            return null;
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
            if(selected_weapon_index >= 0)
                weapons[selected_weapon_index].selectWeapon(false);
            selected_weapon_index = i;
            weapons[selected_weapon_index].selectWeapon(true);
        }
    }

    public ArrayList<Projectile> getLaunchedProjectiles()
    {
        return launchedProjectiles;
    }

    @Override
    public void updateFrame(double cameraPosition)
    {
        this.updateProjectiles(cameraPosition);
        weapons[0].setPosition(this.getPosition());
        weapons[1].setPosition(this.getPosition());

        weapons[0].updateFrame(cameraPosition);
        weapons[1].updateFrame(cameraPosition);
    }
    @Override
    public void if_collides(Hero hero)
    {}
}
