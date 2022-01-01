import javafx.scene.image.Image;

public class Pipe extends Island
{
    boolean upsideDown = false;

    public Pipe(double x, double y, boolean upsideDown)
    {
        super(x,y,100, false);
        this.upsideDown = upsideDown;
        if(!upsideDown)
        {
            String imagePath = "file:assets/Pipe.png";
            this.setImagePath(imagePath);
            this.loadImageView();
        }
        else
        {
            String imagePath = "file:assets/PipeUpsideDown.png";
            this.setImagePath(imagePath);
            this.loadImageView();
        }
    }

    @Override
    public void loadImageView()
    {
        super.loadImageView();

        if(!upsideDown)
        {
            getImageView().setFitWidth(this.getLength());
            getImageView().setPreserveRatio(true);
            getImageView().setX(this.getPosition().getX());
            getImageView().setY(this.getPosition().getY());
        }
        else
        {
            getImageView().setFitWidth(this.getLength());
            getImageView().setPreserveRatio(true);
            getImageView().setX(this.getPosition().getX());
            double aspectratio = getImageView().getImage().getHeight()/getImageView().getImage().getWidth();
            getImageView().setY(this.getPosition().getY() - aspectratio * getLength());
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