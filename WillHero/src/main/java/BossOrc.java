import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BossOrc extends Orc {
    private String imagePath;
    private double size;
    private double jumpSpeed1;
    private double jumpSpeed2;
    private double gravity;

    private int jump_counter;

    public BossOrc (double x, double y, double _size) {
        /* For now jump_speed, size, hits_required initialized to 1
         * Can change if required
         */
        super(x, y, 1, _size, 5);
        size = _size;
        jumpSpeed1 = 8;
        jumpSpeed2 = 3;
        gravity = 0.25;
        prize = 100;

        imagePath = "file:assets/BossOrcSprite.png";
        this.setImage(new Image(imagePath));
        double w = getImageView().getImage().getWidth();
        double h = getImageView().getImage().getHeight();
        getImageView().setX(x);
        getImageView().setY(y - (h - w) * (size / w));
        getImageView().setFitWidth(size);
        getImageView().setPreserveRatio(true);
        getImageView().setSmooth(true);

        jump_counter = 0;
    }

    @Override
    public void move_down()
    {
        this.setVelocity(getVelocity().getX(), getVelocity().getY() + gravity);
    }

    public void jump_up ()
    {
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
            this.setVelocity(0, 0);
            jump_up();
        }
    }

    @Override
    public void if_collides(Hero hero)
    {
        boolean isPushed = false;
        boolean isLandedOnHero = false;
        boolean heroLandedOnOrc = false;

        double dx = this.getPosition().getX() - hero.getPosition().getX();
        double dy = this.getPosition().getY() - hero.getPosition().getY();

        if(0 <= dx && dx <= hero.getSize())
        {
            if(-this.size <= dy && dy <= hero.getSize())
                isPushed = true;
        }
        if(-this.size <= dx && dx <= hero.getSize())
        {
            if(- this.size <= dy && dy <= - this.size * 4 / 5)
                isLandedOnHero = true;
            else if(hero.getSize() * 4 / 5 <= dy && dy <= hero.getSize())
                heroLandedOnOrc = true;
        }

        if(heroLandedOnOrc)
        {
            hero.jump_up();
        }
        else if(isLandedOnHero)
            hero.lose_game();
        else if(isPushed)
        {
            hero.getVelocity().setX(0);
            this.setVelocity(5, 0);
        }
    }
}