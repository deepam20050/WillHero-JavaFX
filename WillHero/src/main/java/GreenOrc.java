import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GreenOrc extends Orc {
    private String imagePath;
    private ImageView imageView;
    private double size;
    private double jumpSpeed;
    private double gravity;

    public GreenOrc (double x, double y, double _size) {
        /* For now jump_speed, size, hits_required initialized to 1
         * Can change if required
         */
        super(x, y, 1, 1, 1);
        size = _size;
        jumpSpeed = 8;
        gravity = 0.25;

        imagePath = "file:assets/GreenOrcSprite.png";
        imageView = new ImageView(new Image(imagePath));
        double w = imageView.getImage().getWidth();
        double h = imageView.getImage().getHeight();
        imageView.setX(x);
        imageView.setY(y - (h - w) * (size / w));
        imageView.setFitWidth(size);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
    }
    public ImageView getImageView() {
        return imageView;
    }
    @Override
    public void if_falls () {

    }
    @Override
    public void is_attacked () {

    }
}