import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WeaponChest extends Chest {
    private Weapon weapon;
    private String imagePath;

    public WeaponChest (double x, double y) {
        super(x, y);
        imagePath = "file:assets/WeaponChestSprite.png";
        this.setImage(new Image(imagePath));
        getImageView().setX(x);
        getImageView().setY(y);
        getImageView().setFitHeight(60);
        getImageView().setPreserveRatio(true);
        weapon = new Sword(x, y, null);
    }
    @Override
    public void updatePosition(double cameraPosition) {
        getImageView().setX(this.getPosition().getX() - cameraPosition);
    }
    @Override
    public void open_chest (Hero hero) {
        if (this.weapon == null) return;
        hero.getPlayer().getHero().getHelmet().addWeapon(this.weapon);
        this.weapon = null;
        imagePath = "file:assets/ChestOpenSprite.png";
        this.setImage(new Image(imagePath));
    }
}
