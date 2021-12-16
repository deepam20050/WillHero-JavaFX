import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GreenOrc extends Orc {
    private String imagePath;
    private ImageView imageView;
    private double size;
    private double jumpSpeed1;
    private double jumpSpeed2;
    private double gravity;

    private int jump_counter;

    public GreenOrc (double x, double y, double _size) {
        /* For now jump_speed, size, hits_required initialized to 1
         * Can change if required
         */
        super(x, y, 1, 1, 1);
        size = _size;
        jumpSpeed1 = 8;
        jumpSpeed2 = 3;
        gravity = 0.25;

        imagePath = "file:assets/GreenOrcSprite.png";
        imageView = new ImageView(new Image(imagePath));
        double w = imageView.getImage().getWidth();
        double h = imageView.getImage().getHeight();
        imageView.setX(x);
        imageView.setY(y - (h - w) * (size / w));
        imageView.setFitWidth(size);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        jump_counter = 0;
    }

    @Override
    public ImageView getImageView() {
        return imageView;
    }

    @Override
    public void move_down()
    {
        this.setVelocity(getVelocity().getX(), getVelocity().getY() + gravity);
    }

    @Override
    public void updatePosition(double cameraPosition)
    {
        this.setPosition(getPosition().getX() + getVelocity().getX(), getPosition().getY() + getVelocity().getY());

        double w = imageView.getImage().getWidth();
        double h = imageView.getImage().getHeight();
        imageView.setX(getPosition().getX() - cameraPosition);
        imageView.setY(getPosition().getY() - (h-w)*(size/w));
    }

    public void jump_up ()
    {
//        this.setVelocity(getVelocity().getX(), -jumpSpeed2);
//        this.setVelocity(getVelocity().getX(), -jumpSpeed2);
//        this.setVelocity(getVelocity().getX(), -jumpSpeed1);
        if(jump_counter == 0) {
            this.setVelocity(getVelocity().getX(), -jumpSpeed1);
        }
        else {
            this.setVelocity(getVelocity().getX(), -jumpSpeed2);
        }
        jump_counter = (jump_counter + 1) % 3;
    }


    public void if_lands (Island island) {
        boolean hasLanded = false;
        double xdist = this.getPosition().getX() - island.getPosition().getX();
        if (xdist >= -size && xdist <= island.getLength()) {
            double ydist = this.getPosition().getY() - island.getPosition().getY();
            if (ydist >= -size && ydist < 0) {
                hasLanded = true;
            }
        }
        if (hasLanded) {
            jump_up();
        }
    }

    @Override
    public void if_falls () {

    }
    @Override
    public void is_attacked () {

    }
}