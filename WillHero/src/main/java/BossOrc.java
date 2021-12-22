import javafx.scene.image.Image;

public class BossOrc extends Orc {
    public BossOrc (double x, double y, double _size) {
        super(x, y, _size, 5);
        size = _size;
        prize = 100;
        pushVelocity = 0.5;
        imagePath = "file:assets/BossOrcSprite.png";
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