import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

import java.util.ArrayList;

public class Sword extends Weapon
{
    private double swordAngle;
    private double swordSwingSpeed; // Degrees/frame
    private double length;

    private transient Rotate rotate;

    private double rotationsLeft = 0;

    Sword(double x, double y, Helmet helmet)
    {
        super(x,y,helmet);
        this.selectWeapon(false);
        swordAngle = 90;
        swordSwingSpeed = 25;
        length = 60;

        String imagePath = "file:assets/WeaponSword.png";
        this.setImagePath(imagePath);
        this.loadImageView();

        rotationsLeft = 0;
    }

    @Override
    public void loadImageView()
    {
        super.loadImageView();

        getImageView().setFitHeight(length);
        getImageView().setPreserveRatio(true);
        getImageView().setTranslateX(getPosition().getX());
        getImageView().setTranslateY(getPosition().getY());

        rotate = new Rotate();
        rotate.setPivotX(getImageView().getFitWidth()/2);
        rotate.setPivotY(0);

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
        getImageView().getTransforms().add(rotate);
        rotate.setAngle(swordAngle - getImageView().getRotate());
    }

    @Override
    public void useWeapon()
    {
        rotationsLeft = this.getLevel();
    }

    @Override
    public void selectWeapon(boolean selected)
    {
        if(selected)
        {
            this.setActive(true);
        }
        else
        {
            this.setActive(false);
        }
    }

    @Override
    public void ifAttacks(Orc orc)
    {
        if(rotationsLeft > 0)
        {
            double swordX = getPosition().getX() + length*Math.cos((270 - swordAngle) * Math.PI / 180);
            double swordY = getPosition().getY() - length*Math.sin((270 - swordAngle) * Math.PI / 180);

            if((orc.getPosition().getX() <= swordX && swordX <= orc.getPosition().getX() + orc.get_size())
                && (orc.getPosition().getY() <= swordY && swordY <= orc.getPosition().getY() + orc.get_size()))
            {
                orc.is_attacked();
            }
        }
    }

    @Override
    public void updateFrame(double cameraPosition)
    {
        getImageView().setTranslateX(this.getPosition().getX() - cameraPosition);
        getImageView().setTranslateY(this.getPosition().getY());

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