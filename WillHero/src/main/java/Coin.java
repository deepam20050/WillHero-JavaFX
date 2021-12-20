import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Coin extends GameObject {
    private String imagePath;
    private int add;

    public Coin (double x, double y) {
        super(new Vector2D (x, y), new Vector2D (0, 0));
        imagePath = "file:assets/CoinSprite.png";
        this.setImage(new Image(imagePath));
        getImageView().setX(x);
        getImageView().setY(y);
        getImageView().setFitWidth(35);
        getImageView().setPreserveRatio(true);
        add = 1;
    }
    @Override
    public void updatePosition(double cameraPosition)
    {
        getImageView().setX(this.getPosition().getX() - cameraPosition);
    }
    @Override
    public void if_collides (Hero hero) {
        if (this.add == 0) return;
        Vector2D coin_pos = this.getPosition();
        Vector2D hero_pos = hero.getPosition();
        double dx = Math.abs(coin_pos.getX() - hero_pos.getX());
        double dy = Math.abs(coin_pos.getY() - hero_pos.getY());
        if (dx <= 30 && dy <= 30) {
            hero.getPlayer().add_coins(this.add);
            this.add = 0;
            this.setActive(false);
        }
    }
}