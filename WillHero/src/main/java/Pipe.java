import javafx.scene.image.Image;

public class Pipe extends Island
{
    boolean upsideDown = false;
    private String imagePath;

    public Pipe(double x, double y, boolean upsideDown)
    {
        super(x,y,100, false);
        this.upsideDown = upsideDown;
        if(!upsideDown)
        {
            imagePath = "file:assets/Pipe.png";
            setImage(new Image(imagePath));
            getImageView().setFitWidth(this.getLength());
            getImageView().setPreserveRatio(true);
            getImageView().setX(x);
            getImageView().setY(y);
        }
        else
        {
            imagePath = "file:assets/PipeUpsideDown.png";
            setImage(new Image(imagePath));
            getImageView().setFitWidth(this.getLength());
            getImageView().setPreserveRatio(true);
            getImageView().setX(x);
            double aspectratio = getImageView().getImage().getHeight()/getImageView().getImage().getWidth();
            getImageView().setY(y - aspectratio * getLength());
        }
    }

    @Override
    public void if_collides(Hero hero)
    {
        double dx = this.getPosition().getX() - hero.getPosition().getX();
        double dy = this.getPosition().getY() - hero.getPosition().getY();

        if(!upsideDown)
        {
            if(-this.getLength() <= dx && dx <= hero.getSize() &&
                dy <= hero.getSize())
            {
                hero.lose_game();
            }
        }
        else
        {
            if(-this.getLength() <= dx && dx <= hero.getSize() &&
                    dy >= 0)
            {
                hero.lose_game();
            }
        }
    }
}