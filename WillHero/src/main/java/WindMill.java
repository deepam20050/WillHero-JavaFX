import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class WindMill extends GameObject{
    private double rotation_speed;
    private double current_angle;
    private ImageView fan;
    private String fan_path;
    private ImageView body;
    private String body_path;
    private final int height = 720;
    private final int width = 720;
    private final double distanceWidth = 360.0 / 720.0;
    private final double distanceHeight = 270.0 / 720.0;

    public WindMill (double x, double y) {
        super(new Vector2D(x, y), new Vector2D(x, y));
        fan_path = "file:assets/WindmillFan.png";
        fan = new ImageView(new Image(fan_path));
        body_path = null;
        body = new ImageView(new Image(body_path));
        fan.setX(width * distanceWidth - fan.getImage().getWidth() / 2);
        fan.setY(height * distanceHeight - fan.getImage().getHeight() / 2);
    }

    @Override
    public void if_collides (Hero hero) {

    }
    @Override
    public void updatePosition (double cameraPosition) {

    }
}
