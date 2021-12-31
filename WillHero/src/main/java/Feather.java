import javafx.scene.image.Image;

public class Feather extends PowerUp
{
    public static final double flySpeed = 4;

    private double imageWidth;
    private String imagePath;

    public Feather(double x, double y)
    {
        super(x,y,6);

        this.imageWidth = 50;
        imagePath = "file:assets/FeatherSprite.png";
        this.setImage(new Image(imagePath));
        getImageView().setX(x);
        getImageView().setY(y);
        getImageView().setFitWidth(imageWidth);
        getImageView().setPreserveRatio(true);
        getImageView().setSmooth(true);
    }

    @Override
    public void usePowerUp()
    {}

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
//            this.getImageView().setImage(null);
            this.setActive(false);
            hero.equipPowerUp(this);
        }
    }

    @Override
    public void updateFrame(double cameraPosition)
    {
        getImageView().setX(this.getPosition().getX() - cameraPosition);
    }
}
