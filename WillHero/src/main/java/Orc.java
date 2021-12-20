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

    public abstract void move_down();
    public abstract void updatePosition(double cameraPosition);
    public abstract void jump_up ();
    public abstract void if_lands (Island island);

    public void is_attacked () {
        if (this.hits_required == 0) return;
        --this.hits_required;
        if (this.hits_required == 0) {
            this.setActive(false);
        }
        System.out.println("Orc with ID " + getId() + " attacked");
    }
    public void if_falls () {
        this.setActive(false);
    }
    public double get_size () {
        return size;
    }
    @Override
    public void if_collides (Hero hero) {
        Vector2D hero_pos = hero.getPosition();
        Vector2D orc_pos = this.getPosition();
        double hero_size = hero.getSize();
        double orc_size = this.size;
        double dx = orc_pos.getX() - hero_pos.getX();
        double dy = orc_pos.getY() - hero_pos.getY();
        if (dx < 0) return;
        if (dx > hero_size + orc_size) return;
        if (dy > 0 && dy <= orc_size && dx <= hero_size) {
            hero.lose_game();
        }
        if (dx <= hero_size) {
            this.setVelocity(5, 0);
        }
    }
}