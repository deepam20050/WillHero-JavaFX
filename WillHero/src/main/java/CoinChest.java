import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CoinChest extends Chest{
    private int no_of_coins;

    public CoinChest (double x, double y, int num) {
        super(x, y);
        no_of_coins = num;
        String imagePath = "file:assets/ChestSprite.png";
        this.setImagePath(imagePath);
        this.loadImageView();
    }

    @Override
    public void loadImageView()
    {
        super.loadImageView();

        getImageView().setX(this.getPosition().getX());
        getImageView().setY(this.getPosition().getY());
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
        setImagePath("file:assets/ChestOpenSprite.png");
//        this.setImage(new Image(imagePath));
        loadImageView();
    }
}