import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

public class ThrowingKnivesProjectile extends Projectile
{
    private double knifeSpeed;
    private double knifeWidth;
    private ImageView imageView;
    private Image projectileImage;

    public ThrowingKnivesProjectile(double x, double y)
    {
        super(x,y);
        knifeSpeed = 35;
        knifeWidth = 10;
        projectileImage = new Image("file:assets/ProjectileKnife.png");
        imageView = new ImageView(projectileImage);
        imageView.setTranslateX(-50);
        imageView.setTranslateY(y);
        imageView.setFitHeight(knifeWidth);
        imageView.setPreserveRatio(true);
    }

    @Override
    public void moveProjectile()
    {
        this.getPosition().setX(getPosition().getX() + knifeSpeed);
    }
    @Override
    public void ifAttacks(Orc orc)
    {

    }

    @Override
    public ImageView getImageView()
    {
        return imageView;
    }
    @Override
    public void updatePosition(double cameraPosition)
    {
        moveProjectile();
        imageView.setTranslateX(getPosition().getX() - cameraPosition);
        imageView.setTranslateY(getPosition().getY());
    }
    @Override
    public void if_collides(Hero hero)
    {

    }
}
