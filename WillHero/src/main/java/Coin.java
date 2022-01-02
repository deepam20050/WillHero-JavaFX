import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Coin extends GameObject {
//    private String imagePath;
    private double size;
    private int add;

    public Coin (double x, double y) {
        super(new Vector2D (x, y), new Vector2D (0, 0));
        String imagePath = "file:assets/CoinSprite.png";
        size = 40;

        this.setImagePath(imagePath);
        this.loadImageView();

        add = 1;
    }

    @Override
    public void loadImageView()
    {
        super.loadImageView();
        getImageView().setX(this.getPosition().getX());
        getImageView().setY(this.getPosition().getY());
        getImageView().setFitWidth(size);
        getImageView().setPreserveRatio(true);
    }

    @Override
    public void updateFrame(double cameraPosition)
    {
        getImageView().setX(this.getPosition().getX() - cameraPosition);
    }
    @Override
    public void if_collides (Hero hero) {
        if (this.add == 0) return;
        Vector2D coin_pos = this.getPosition();
        Vector2D hero_pos = hero.getPosition();
//        double dx = Math.abs(coin_pos.getX() - hero_pos.getX());
//        double dy = Math.abs(coin_pos.getY() - hero_pos.getY());
//        if (dx <= 30 && dy <= 30) {
//            hero.getPlayer().add_coins(this.add);
//            this.add = 0;
//            this.setActive(false);
//        }
        double dx = coin_pos.getX() - hero_pos.getX();
        double dy = coin_pos.getY() - hero_pos.getY();
        if(-this.size <= dx && dx <= hero.getSize() &&
            -this.size <= dy && dy <= hero.getSize()) {
            hero.getPlayer().add_coins(this.add);
            this.add = 0;
            this.setActive(false);
        }
    }
}