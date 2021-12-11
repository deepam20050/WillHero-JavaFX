import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Chest extends GameObject {
    public Chest (double x, double y) {
        super(new Vector2D (x, y), new Vector2D (0, 0));
    }

    public abstract ImageView getImageView();

    public void open_chest (Hero hero) {

    }

    @Override
    public void if_collides (Hero hero) {

    }
}