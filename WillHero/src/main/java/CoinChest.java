import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CoinChest extends Chest{
    private int no_of_coins;
    private String imagePath;

    public CoinChest (double x, double y, int num) {
        super(x, y);
        no_of_coins = num;
        imagePath = "file:assets/ChestSprite.png";
        this.setImage(new Image(imagePath));
        getImageView().setX(x);
        getImageView().setY(y);
        getImageView().setFitHeight(60);
        getImageView().setPreserveRatio(true);
    }

    @Override
    public void updateFrame(double cameraPosition)
    {
        getImageView().setX(this.getPosition().getX() - cameraPosition);
    }

    @Override
    public void open_chest (Hero hero) {
        if (this.no_of_coins == 0) return;
        hero.getPlayer().add_coins(this.no_of_coins);
        this.no_of_coins = 0;
        imagePath = "file:assets/ChestOpenSprite.png";
        this.setImage(new Image(imagePath));
    }
}