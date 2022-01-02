import javafx.scene.image.Image;

public class ShootingStar extends GameObject
{
    private double shootingSpeed;
    private double shootingAngle; // Clockwise angle from vertical down
    private double imageWidth;

    public ShootingStar(double x, double y)
    {
        super(new Vector2D(x,y), new Vector2D(0,0));
        shootingSpeed = 12;
        shootingAngle = 45;
        imageWidth = 40;

        String imagePath = "file:assets/ShootingStarSprite.png";
        this.setImagePath(imagePath);
        this.loadImageView();

        this.setVelocity(shootingSpeed * -Math.sin(shootingAngle * Math.PI / 180),
                shootingSpeed * Math.cos(shootingAngle * Math.PI / 180));
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
        if(this.getImageView().getImage() == null)
            return;

        double dx = this.getPosition().getX() - orc.getPosition().getX();
        double dy = this.getPosition().getY() - orc.getPosition().getY();
        double aspectRatio = this.getImageView().getImage().getHeight()/this.getImageView().getImage().getWidth();

        if(-this.imageWidth <= dx && dx <= orc.get_size() &&
                -aspectRatio*imageWidth <= dy && dy <= orc.get_size())
        {
            orc.is_attacked();
            this.setActive(false);
        }
    }

    @Override
    public void if_collides(Hero hero)
    {}
}
