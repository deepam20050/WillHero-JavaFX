import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Coin extends GameObject {
    private String imagePath;

    public Coin (double x, double y) {
        super(new Vector2D (x, y), new Vector2D (0, 0));
        imagePath = "file:assets/CoinSprite.png";
        this.setImage(new Image(imagePath));
        getImageView().setX(x);
        getImageView().setY(y);
        getImageView().setFitWidth(35);
        getImageView().setPreserveRatio(true);
    }
    @Override
    public void updatePosition(double cameraPosition)
    {
        getImageView().setX(this.getPosition().getX() - cameraPosition);
    }
    @Override
    public void if_collides (Hero hero) {
        hero.getPlayer().add_coins(1);
    }
}