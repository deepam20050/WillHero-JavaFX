import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

public class ThrowingKnives extends Weapon
{
    private ImageView imageView;
    private Image throwingKnivesImage;
    private Rotate rotate;

    public ThrowingKnives(double x, double y, Helmet helmet)
    {
        super(x,y,helmet);
        throwingKnivesImage = new Image("file:assets/WeaponThrowingKnives.png");
        imageView = new ImageView(throwingKnivesImage);
        this.selectWeapon(false);

        imageView.setFitWidth(30);
        imageView.setPreserveRatio(true);
        imageView.setTranslateX(getPosition().getX());
        imageView.setTranslateY(getPosition().getY());

        rotate = new Rotate();
        rotate.setPivotX(imageView.getFitWidth()/2);
        rotate.setPivotY(0);
        imageView.getTransforms().add(rotate);
        rotate.setAngle(90);
    }

    @Override
    public void useWeapon()
    {
        System.out.println("Knife go WAAAA");
        Projectile projectile = new ThrowingKnivesProjectile(getPosition().getX(), getPosition().getY());
        getHelmet().launchProjectile(projectile);
    }

    @Override
    public void selectWeapon(boolean selected)
    {
        if(selected)
        {
            this.setActive(true);
            imageView.setImage(throwingKnivesImage);
        }
        else
        {
            this.setActive(false);
            imageView.setImage(null);
        }
    }

    @Override
    public ImageView getImageView()
    {
        return imageView;
    }

    @Override
    public void updatePosition(double cameraPosition)
    {
        imageView.setTranslateX(this.getPosition().getX() - cameraPosition);
        imageView.setTranslateY(this.getPosition().getY());
    }
    @Override
    public void if_collides(Hero hero)
    {}
}
