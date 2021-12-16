public abstract class Weapon extends GameObject
{
    private int level;

    Weapon(double x, double y)
    {
        super(new Vector2D(x, y), new Vector2D(0,0));
        level = 5;
    }

    public int getLevel()
    {
        return level;
    }

    public abstract void use_weapon();
}
