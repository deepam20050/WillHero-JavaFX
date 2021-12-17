import javafx.scene.image.ImageView;

public abstract class Projectile extends GameObject
{
    public Projectile(double x, double y)
    {
        super(new Vector2D(x,y), new Vector2D(0,0));
    }

    public abstract void moveProjectile();
    public abstract void ifAttacks(Orc orc);
    public abstract ImageView getImageView();
}
