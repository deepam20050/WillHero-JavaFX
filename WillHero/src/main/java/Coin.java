import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Coin extends GameObject {
  private String imagePath;
  private ImageView imageView;
  public Coin (double x, double y) {
    super(new Vector2D (x, y), new Vector2D (0, 0));
    imagePath = "file:assets/CoinSprite.png";
    imageView = new ImageView(new Image(imagePath));
    imageView.setX(x);
    imageView.setY(y);
    imageView.setFitWidth(2);
    imageView.setPreserveRatio(true);
  }
  public ImageView getImageView () {
    return imageView;
  }
  @Override
  public void if_collides (Hero hero) {

  }
}
