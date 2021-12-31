import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

public class ThrowingKnivesProjectile extends Projectile
{
    private double knifeSpeed;
    private double knifeWidth;
    private double knifeHeight;
    private String imagePath;

    public ThrowingKnivesProjectile(double x, double y)
    {
        super(x,y);
        knifeSpeed = 35;
        knifeWidth = 10;
        imagePath = "file:assets/ProjectileKnife.png";
        setImage(new Image(imagePath));
        getImageView().setTranslateX(-50);
        getImageView().setTranslateY(y);
        getImageView().setFitHeight(knifeWidth);
        getImageView().setPreserveRatio(true);
        knifeHeight = getImageView().getFitWidth();
    }

    @Override
    public void moveProjectile()
    {
        this.getPosition().setX(getPosition().getX() + knifeSpeed);
    }
    @Override
    public void ifAttacks(Orc orc)
    {
        double xdist = orc.getPosition().getX() - (getPosition().getX() + knifeHeight);
        double ydist = orc.getPosition().getY() - getPosition().getY();

        if((-orc.get_size() <= xdist && xdist <= 0) && (-orc.get_size() <= ydist && ydist <= knifeWidth))
        {
            orc.is_attacked();
            this.setActive(false);
        }
    }
    @Override
    public void updateFrame(double cameraPosition)
    {
        moveProjectile();
        getImageView().setTranslateX(getPosition().getX() - cameraPosition);
        getImageView().setTranslateY(getPosition().getY());
    }
    @Override
    public void if_collides(Hero hero)
    {

    }
}
