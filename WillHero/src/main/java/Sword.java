import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

public class Sword extends Weapon
{
    private double swordAngle;
    private double swordSwingSpeed; // Degrees/frame
    private double length;

    private ImageView imageView;
    private Image swordImage;
    private Rotate rotate;

    private double rotationsLeft = 0;

    Sword(double x, double y, Helmet helmet)
    {
        super(x,y,helmet);
        swordImage = new Image("file:assets/WeaponSword.png");
        imageView = new ImageView(swordImage);
        this.selectWeapon(false);
        swordAngle = 90;
        swordSwingSpeed = 25;
        length = 60;

        imageView.setFitHeight(length);
        imageView.setPreserveRatio(true);
        imageView.setTranslateX(getPosition().getX());
        imageView.setTranslateY(getPosition().getY());

        rotate = new Rotate();
        rotate.setPivotX(imageView.getFitWidth()/2);
        rotate.setPivotY(0);
        imageView.getTransforms().add(rotate);
        rotate.setAngle(swordAngle);

        rotationsLeft = 0;
    }

    @Override
    public void useWeapon()
    {
        System.out.println("Sword go WHOOOSH");
        rotationsLeft = this.getLevel();
    }

    @Override
    public void selectWeapon(boolean selected)
    {
        if(selected)
        {
            this.setActive(true);
            imageView.setImage(swordImage);
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

        rotate.setAngle(swordAngle);

        if(rotationsLeft > 0)
        {
            swordAngle += swordSwingSpeed;
            if(swordAngle > 360 + 90)
            {
                rotationsLeft--;
                swordAngle -= 360;
                if(rotationsLeft <= 0)
                    swordAngle = 90;
            }
        }
    }

    @Override
    public void if_collides(Hero hero)
    {}
}