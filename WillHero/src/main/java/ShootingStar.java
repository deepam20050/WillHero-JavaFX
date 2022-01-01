import javafx.scene.image.Image;

public class ShootingStar extends GameObject
{
    private double shootingSpeed;
    private double shootingAngle; // Clockwise angle from vertical down
    private String imagePath;

    public ShootingStar(double x, double y)
    {
        super(new Vector2D(x,y), new Vector2D(0,0));
        shootingSpeed = 12;
        shootingAngle = 45;

        imagePath = "file:assets/ShootingStarSprite.png";
        this.setImage(new Image(imagePath));
        getImageView().setX(x);
        getImageView().setY(y);
        getImageView().setFitWidth(40);
        getImageView().setPreserveRatio(true);
        getImageView().setSmooth(true);

        this.setVelocity(shootingSpeed * -Math.sin(shootingAngle * Math.PI / 180),
                shootingSpeed * Math.cos(shootingAngle * Math.PI / 180));
    }

    @Override
    public void updateFrame(double cameraPosition)
    {
        this.getPosition().setX(this.getPosition().getX() + this.getVelocity().getX());
        this.getPosition().setY(this.getPosition().getY() + this.getVelocity().getY());

        getImageView().setX(this.getPosition().getX() - cameraPosition);
        getImageView().setY(this.getPosition().getY());

        if(this.getPosition().getY() > WillHero.sceneHeight)
            this.setActive(false);
    }

    public void if_collides_with_orc(Orc orc)
    {
        double dx = this.getPosition().getX() - orc.getPosition().getX();
        double dy = this.getPosition().getY() - orc.getPosition().getY();

        if(this.getImageView().getImage() == null)
            return;
        if(-this.getImageView().getImage().getWidth() <= dx && dx <= orc.get_size() &&
                -this.getImageView().getImage().getHeight() <= dy && dy <= orc.get_size())
        {
            orc.is_attacked();
            this.setActive(false);
        }
    }

    @Override
    public void if_collides(Hero hero)
    {}
}
