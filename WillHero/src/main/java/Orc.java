import javafx.scene.image.ImageView;

public abstract class Orc extends GameObject {
    private double jump_speed;
    private double size;
    private int hits_required;

    public Orc (double x, double y, double _jump_speed, double _size, int _hits_required) {
        super(new Vector2D (x, y), new Vector2D (0, 0));
        jump_speed = _jump_speed;
        size = _size;
        hits_required = _hits_required;
    }

    public abstract ImageView getImageView();
    public abstract void move_down();
    public abstract void updatePosition(double cameraPosition);

    public void is_attacked () {

    }
    public void if_falls () {

    }
    public void jump_up () {

    }
    public void if_lands (Island island) {

    }

    public double get_size () {
        return size;
    }

    @Override
    public void if_collides (Hero hero) {

    }
}