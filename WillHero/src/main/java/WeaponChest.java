import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WeaponChest extends Chest {
    private Weapon weapon;
//    private String imagePath;

    public WeaponChest (double x, double y, Weapon weapon) {
        super(x, y);
        String imagePath = "file:assets/ChestSprite.png";
        this.weapon = weapon;
//        this.setImage(new Image(imagePath));
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
    public void updateFrame(double cameraPosition) {
        getImageView().setX(this.getPosition().getX() - cameraPosition);
    }
    @Override
    public void open_chest (Hero hero) {
        if (this.weapon == null) return;
        hero.getPlayer().getHero().getHelmet().addWeapon(this.weapon);
        this.weapon = null;
        setImagePath("file:assets/ChestOpenSprite.png");
//        this.setImage(new Image(imagePath));
        loadImageView();
    }
}