import javafx.scene.image.ImageView;

public abstract class Weapon extends GameObject
{
    private int level;
    private Helmet helmet;

    Weapon(double x, double y, Helmet helmet)
    {
        super(new Vector2D(x, y), new Vector2D(0,0));
        this.helmet = helmet;
        level = 0;
    }

    public abstract void selectWeapon(boolean selected);
    public abstract void useWeapon();
    public abstract void ifAttacks(Orc orc);

    public int getLevel()
    {
        return level;
    }
    public void setLevel(int newLevel)
    {
        this.level = newLevel;
    }

    public void incrementLevel()
    {
        this.level++;
    }

    public Helmet getHelmet()
    {
        return helmet;
    }
}
