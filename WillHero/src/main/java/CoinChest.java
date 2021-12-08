import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CoinChest extends Chest{
  private int no_of_coins;
  private String imagePath;
  private ImageView imageView;
  public CoinChest (double x, double y) {
    super(x, y);
    no_of_coins = 5;
    imagePath = "file:assets/ChestSprite.png";
    imageView = new ImageView(new Image(imagePath));
    imageView.setX(x);
    imageView.setY(y);
    imageView.setPreserveRatio(true);
  }
  @Override
  public void open_chest (Hero hero) {
    
  }
}
