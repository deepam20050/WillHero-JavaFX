import javafx.scene.image.Image;

import java.util.Random;

public class Feather extends PowerUp
{
    public static final double flySpeed = 4;

    private double imageWidth;
    private double starLaunchTime;
    private double timeBeforeLastLaunch;

    public Feather(double x, double y)
    {
        super(x,y,6);
        this.starLaunchTime = 0.1;
        this.timeBeforeLastLaunch = 0;

        this.imageWidth = 50;
        String imagePath = "file:assets/FeatherSprite.png";
        this.setImagePath(imagePath);
        this.loadImageView();
    }

    @Override
    public void loadImageView()
    {
        super.loadImageView();

        getImageView().setX(this.getPosition().getX());
        getImageView().setY(this.getPosition().getY());
        getImageView().setFitWidth(imageWidth);
        getImageView().setPreserveRatio(true);
        getImageView().setSmooth(true);
    }

    @Override
    public void usePowerUp()
    {
        decrementDuration(1/WillHero.frameRate);
        timeBeforeLastLaunch += 1/WillHero.frameRate;
        if(timeBeforeLastLaunch >= starLaunchTime)
        {
            timeBeforeLastLaunch -= starLaunchTime;
            double offset = Math.random();
            ShootingStar star = new ShootingStar((offset * WillHero.sceneWidth * 1.2) + getEquippingHero().getPlayer().getGame().getCameraPosition(), -30);

            getEquippingHero().getPlayer().getGame().get_current_level().getShootingStars().add(star);
        }
    }

    @Override
    public void if_collides(Hero hero)
    {
        if(!isActive())
            return;
        double dx = this.getPosition().getX() - hero.getPosition().getX();
        double dy = this.getPosition().getY() - hero.getPosition().getY();

        if(-this.getImageView().getImage().getWidth() <= dx && dx <= hero.getSize()
            && -this.getImageView().getImage().getHeight() <= dy && dy <= hero.getSize())
        {
            this.setActive(false);
            hero.equipPowerUp(this);
        }
        this.setEquippingHero(hero);
    }

    @Override
    public void updateFrame(double cameraPosition)
    {
        getImageView().setX(this.getPosition().getX() - cameraPosition);
    }
}
