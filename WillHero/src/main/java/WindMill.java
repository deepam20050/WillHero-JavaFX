import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.awt.geom.Rectangle2D;

public class WindMill extends GameObject implements Obstacle {
    private double rotation_speed;
    private double current_angle;
    private ImageView fan;
    private String fan_path;
    private ImageView body;
    private String body_path;
    private final int height = 80;
    private final int width = 200;

    public WindMill (double x, double y) {
        super(new Vector2D(x, y), new Vector2D(x, y));
        fan_path = "file:assets/WindmillFan.png";
        fan = new ImageView(new Image(fan_path));
        body_path = "file:assets/WeaponSword.png";
        body = new ImageView(new Image(body_path));
        fan.setX(x);
        fan.setY(y);
        fan.setFitWidth(width);
        body.setX(x);
        body.setY(y);
        body.setFitWidth(height);
        body.setPreserveRatio(true);
        fan.setPreserveRatio(true);
    }

    public ImageView getFan () {
        return fan;
    }
    public ImageView getBody () {
        return body;
    }
    public void rotateFan () {
        fan.setRotate(fan.getRotate() + 5);
    }
    @Override
    public void if_collides (Hero hero) {

    }
    @Override
    public void update_obs_state () {

    }
    @Override
    public void updatePosition (double cameraPosition) {
        fan.setX(this.getPosition().getX() - cameraPosition);
        body.setX(this.getPosition().getX() - cameraPosition);
    }
}
