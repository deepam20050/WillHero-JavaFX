import javafx.scene.image.Image;

public class GreenOrc extends Orc {
    public GreenOrc (double x, double y, double _size) {
        super(x, y, _size, 1);
        size = _size;
        prize = 1;
        pushVelocity = 20;
        imagePath = "file:assets/GreenOrcSprite.png";
        this.setImage(new Image(imagePath));
        double w = getImageView().getImage().getWidth();
        double h = getImageView().getImage().getHeight();
        getImageView().setX(x);
        getImageView().setY(y - (h - w) * (size / w));
        getImageView().setFitWidth(size);
        getImageView().setPreserveRatio(true);
        getImageView().setSmooth(true);
        jump_counter = 0;
    }
}