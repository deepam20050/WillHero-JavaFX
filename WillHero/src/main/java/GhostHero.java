import javafx.scene.image.Image;

import java.util.ArrayList;

public class GhostHero extends GameObject
{
    private Hero heroToFollow;
    private double size;
    private double followDelay;

    public GhostHero(Hero heroToFollow)
    {
        this.heroToFollow = heroToFollow;
        this.size = heroToFollow.getSize();
        this.followDelay = 1.75;
        String imagePath = "file:assets/GhostHeroSprite.png";
        this.setImagePath(imagePath);
        this.loadImageView();

        this.setActive(false);
    }

    @Override
    public void loadImageView()
    {
        super.loadImageView();

        double w = getImageView().getImage().getWidth();
        double h = getImageView().getImage().getHeight();
        getImageView().setFitWidth(size);
        getImageView().setPreserveRatio(true);
        getImageView().setSmooth(true);
    }

    @Override
    public void updateFrame(double cameraPosition)
    {
        ArrayList<Vector2D> positionLogs = heroToFollow.getPositionLogs();
        while(positionLogs.size() > followDelay * WillHero.frameRate)
        {
            this.setActive(true);
            this.setPosition(positionLogs.get(0));
            positionLogs.remove(0);
        }

        if(isActive())
        {
            double w = getImageView().getImage().getWidth();
            double h = getImageView().getImage().getHeight();
            getImageView().setX(getPosition().getX() - cameraPosition);
            // Image is not a perfect square but the Hero object is treated like a square
            // Image is moved up slightly so the bottom portion represents the object area
            getImageView().setY(getPosition().getY() - (h - w) * (size / w));

            if_collides(heroToFollow);
        }
    }
    @Override
    public void if_collides(Hero hero)
    {
        double dx = this.getPosition().getX() - hero.getPosition().getX();
        double dy = this.getPosition().getY() - hero.getPosition().getY();

        if(-this.size < dx && dx < hero.getSize() &&
            -this.size < dy && dy < hero.getSize())
        {
            hero.lose_game();
        }
    }
}