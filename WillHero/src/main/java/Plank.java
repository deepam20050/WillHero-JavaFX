import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Plank extends GameObject {
//    private String imagePath;
    private final double length;
    private final double LIMIT = 1000;

    Plank (double x, double y, double length) {
        super(new Vector2D(x, y), new Vector2D(0, 0));
        this.length = length;
        String imagePath = "file:assets/PlankSprite.png";
        this.setImagePath(imagePath);
        this.loadImageView();
//        setImage(new Image(imagePath));
    }

    @Override
    public void loadImageView()
    {
        super.loadImageView();

        getImageView().setX(this.getPosition().getX());
        getImageView().setY(this.getPosition().getY());
        getImageView().setFitWidth(length);
        getImageView().setPreserveRatio(true);
    }

    public void fall () {
        TranslateTransition goDown = new TranslateTransition();
        goDown.setByY(LIMIT);
        goDown.setDuration(Duration.millis(1000));
        goDown.setCycleCount(1);
        goDown.setAutoReverse(false);
        goDown.setNode(getImageView());
        goDown.play();
    }
    public boolean if_collides_hero (Hero hero) {
        boolean hasLanded = false;
        double xdist = hero.getPosition().getX() - this.getPosition().getX();
        if(xdist >= -hero.getSize() && xdist <= this.length) {
            double ydist = hero.getPosition().getY() - this.getPosition().getY();
            if(ydist >= -hero.getSize() && ydist < 0) {
                hasLanded = true;
            }
        }
        return hasLanded;
    }
    public double getLength () {
        return this.length;
    }
    @Override
    public void if_collides(Hero hero) {}
    @Override
    public void updateFrame(double cameraPosition) {
        getImageView().setX(getPosition().getX() - cameraPosition);
    }
}