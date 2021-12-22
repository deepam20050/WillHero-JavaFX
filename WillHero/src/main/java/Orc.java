import javafx.scene.image.ImageView;

public abstract class Orc extends GameObject {
    protected double size;
    protected int prize;
    protected int dead;
    protected int hits_required;
    protected double pushVelocity;
    protected String imagePath;
    protected double jumpSpeed1;
    protected double jumpSpeed2;
    protected int jump_counter;
    private final double gravity;
    private final int fallBoundary = 600;

    public Orc (double x, double y, double _size, int _hits_required) {
        super(new Vector2D (x, y), new Vector2D (0, 0));
        size = _size;
        hits_required = _hits_required;
        jumpSpeed1 = 8;
        jumpSpeed2 = 3;
        gravity = 0.25;
        dead = 1;
    }

    public void move_down() {
        this.setVelocity(getVelocity().getX(), getVelocity().getY() + gravity);
    }
    public void updatePosition(double cameraPosition) {
        this.setPosition(getPosition().getX() + getVelocity().getX(), getPosition().getY() + getVelocity().getY());
        ImageView imageView = getImageView();
        double w = imageView.getImage().getWidth();
        double h = imageView.getImage().getHeight();
        imageView.setX(getPosition().getX() - cameraPosition);
        imageView.setY(getPosition().getY() - (h-w)*(size/w));
    }
    public void jump_up ()  {
        if(jump_counter == 0) {
            this.setVelocity(getVelocity().getX(), -jumpSpeed1);
        } else {
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
    public void give_coin (Hero hero) {
        if (dead == 1) return;
        if (dead == 0) {
            hero.collect_coins(prize);
            --dead;
        }
    }
    public void is_attacked () {
        if (this.hits_required == 0) return;
        --this.hits_required;
        if (this.hits_required == 0) {
            this.setActive(false);
            dead = 0;
        }
        System.out.println("Orc with ID " + getId() + " attacked");
    }
    public void if_falls () {
        if (getPosition().getY() >= fallBoundary) {
            this.setActive(false);
            --dead;
        }
    }
    public double get_size () {
        return size;
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
            if(- hero.getSize() <= dy && dy <= - hero.getSize() * 4 / 5)
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
            this.setVelocity(pushVelocity, 0);
        }
    }
    public void if_collides_with_orc(Orc orc)
    {
        boolean colliding = false;
        boolean otherOnTop = false;

        double dx = orc.getPosition().getX() - this.getPosition().getX();
        double dy = orc.getPosition().getY() - this.getPosition().getY();

        if(-orc.get_size() <= dy && dy <= this.size)
        {
            if(0 <= dx && dx <= this.size)
            {
                colliding = true;
            }
        }

        else if(-orc.get_size() <= dx && dx <= this.get_size())
        {
            if(-orc.get_size() <= dy && dy <= orc.get_size() * 4 / 5)
            {
                otherOnTop = true;
            }
        }

        if(otherOnTop)
        {
            orc.jump_up();
        }
        else if(colliding)
        {
            this.getVelocity().setX(this.getVelocity().getX()/2);
            orc.getPosition().setX(this.getPosition().getX() + this.size);
        }
    }
}