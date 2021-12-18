import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

public class ThrowingKnives extends Weapon
{
    private String imagePath;
    private Rotate rotate;

    public ThrowingKnives(double x, double y, Helmet helmet)
    {
        super(x,y,helmet);
        imagePath = "file:assets/WeaponThrowingKnives.png";
        this.setImage(new Image(imagePath));
        this.selectWeapon(false);

        getImageView().setFitWidth(30);
        getImageView().setPreserveRatio(true);
        getImageView().setTranslateX(getPosition().getX());
        getImageView().setTranslateY(getPosition().getY());

        rotate = new Rotate();
        rotate.setPivotX(getImageView().getFitWidth()/2);
        rotate.setPivotY(0);
        getImageView().getTransforms().add(rotate);
        rotate.setAngle(90);
    }

    @Override
    public void useWeapon()
    {
        Projectile projectile = new ThrowingKnivesProjectile(getPosition().getX(), getPosition().getY());
        getHelmet().launchProjectile(projectile);
    }

    @Override
    public void selectWeapon(boolean selected)
    {
        this.setActive(selected);
    }

    @Override
    public void updatePosition(double cameraPosition)
    {
        getImageView().setTranslateX(this.getPosition().getX() - cameraPosition);
        getImageView().setTranslateY(this.getPosition().getY());
    }
    @Override
    public void ifAttacks(Orc orc)
    {}
    @Override
    public void if_collides(Hero hero)
    {}
}