import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

import java.util.ArrayList;

public class ThrowingKnives extends Weapon
{
    private transient Rotate rotate;

    public ThrowingKnives(double x, double y, Helmet helmet)
    {
        super(x,y,helmet);
        String imagePath = "file:assets/WeaponThrowingKnives.png";
        this.setImagePath(imagePath);
        this.loadImageView();
        this.selectWeapon(false);
    }

    @Override
    public void loadImageView()
    {
        super.loadImageView();

        getImageView().setFitWidth(30);
        getImageView().setPreserveRatio(true);
        getImageView().setTranslateX(getPosition().getX());
        getImageView().setTranslateY(getPosition().getY());

        ArrayList<Transform> toBeRemoved = new ArrayList<Transform>();
        for(Transform transform: getImageView().getTransforms())
        {
            if(transform instanceof Rotate)
            {
                toBeRemoved.add(transform);
            }
        }
        for(Transform transform: toBeRemoved)
        {
            getImageView().getTransforms().remove(transform);
        }

        rotate = new Rotate();
        rotate.setPivotX(getImageView().getFitWidth()/2);
        rotate.setPivotY(0);
        getImageView().getTransforms().add(rotate);
        rotate.setAngle(90);
    }

    @Override
    public void useWeapon()
    {
        for(int i = 0; i < this.getLevel(); i++)
        {
            Projectile projectile = new ThrowingKnivesProjectile(getPosition().getX(), getPosition().getY() + i*15);
            getHelmet().launchProjectile(projectile);
        }
    }

    @Override
    public void selectWeapon(boolean selected)
    {
        this.setActive(selected);
    }

    @Override
    public void updateFrame(double cameraPosition)
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