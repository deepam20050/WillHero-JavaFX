import javafx.scene.image.Image;

public class TNT extends GameObject{
    private String imagePath;
    private final double size = 60;
    private double gravity = 0.25;
    TNT (double x, double y) {
        super(new Vector2D(x, y), new Vector2D(0, 0));
        imagePath = "file:assets/TNTSprite.png";
//        imagePath = "file:assets/ChestSprite.png";
        this.setImage(new Image(imagePath));
        getImageView().setX(x);
        getImageView().setY(y);
        getImageView().setFitWidth(size);
        getImageView().setPreserveRatio(true);
        getImageView().setSmooth(true);
    }
    @Override
    public void if_collides(Hero hero) {
        double dx = this.getPosition().getX() - hero.getPosition().getX();
        double dy = this.getPosition().getY() - hero.getPosition().getY();
        if (0 <= dx && dx <= hero.getSize() && 0 <= dy && dy <= hero.getSize()) {
            System.out.println("hit TNT");
            hero.lose_game();
        }
    }

    @Override
    public void updateFrame(double cameraPosition)
    {
        getImageView().setX(this.getPosition().getX() - cameraPosition);
    }
}
