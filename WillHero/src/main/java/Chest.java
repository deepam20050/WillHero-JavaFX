import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Chest extends GameObject {
    public Chest (double x, double y) {
        super(new Vector2D (x, y), new Vector2D (0, 0));
    }

    public abstract void open_chest (Hero hero);

    @Override
    public void if_collides (Hero hero) {
        Vector2D chest_pos = this.getPosition();
        Vector2D hero_pos = hero.getPosition();
//        double dx = Math.abs(chest_pos.getX() - hero_pos.getX());
//        double dy = Math.abs(chest_pos.getY() - hero_pos.getY());
//        if (dx <= 20 && dy <= 20) {
//            this.open_chest(hero);
//        }
        double dx = chest_pos.getX() - hero_pos.getX();
        double dy = chest_pos.getY() - hero_pos.getY();

        if(-getImageView().getFitWidth() <= dx && dx <= hero.getSize() &&
            -getImageView().getFitHeight() <= dy && dy <= hero.getSize())
        {
            this.open_chest(hero);
        }
    }
}