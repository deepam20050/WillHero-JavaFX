import javafx.scene.image.Image;

public class Plank extends GameObject {
    private String imagePath;
    private final double length;
    private final double fallVelocity;
    Plank (double x, double y, double length, double fallVelocity) {
        super(new Vector2D(x, y), new Vector2D(0, 0));
        this.length = length;
        this.fallVelocity = fallVelocity;
        imagePath = "file:assets/PlankSprite.png";
        setImage(new Image(imagePath));
        getImageView().setX(x);
        getImageView().setY(y);
        getImageView().setFitWidth(length);
        getImageView().setPreserveRatio(true);
    }
    public void fall () {
        double x = getPosition().getX();
        this.setVelocity(new Vector2D(x, this.fallVelocity));
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
    @Override
    public void if_collides(Hero hero) {}
    @Override
    public void updateFrame(double cameraPosition) {
        getImageView().setX(getPosition().getX() - cameraPosition);
    }
}
