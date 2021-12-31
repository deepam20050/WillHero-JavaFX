import javafx.scene.image.ImageView;

public abstract class Orc extends GameObject {
    private double jump_speed;
    private double size;
    protected int prize;
    protected int dead;
    protected int hits_required;
    private final int fallBoundary = 600;

    public Orc (double x, double y, double _jump_speed, double _size, int _hits_required) {
        super(new Vector2D (x, y), new Vector2D (0, 0));
        jump_speed = _jump_speed;
        size = _size;
        hits_required = _hits_required;
        dead = 1;
    }

    public abstract void move_down();
    public abstract void jump_up ();
    public abstract void if_lands (Island island);

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
            dead = 0;
            System.out.println("Orc with ID " + getId() + " fell");
//            --dead;
        }
    }
    public double get_size () {
        return size;
    }

    @Override
    public void updateFrame(double cameraPosition)
    {
        move_down();
        this.setPosition(getPosition().getX() + getVelocity().getX(), getPosition().getY() + getVelocity().getY());

        ImageView imageView = getImageView();

        double w = imageView.getImage().getWidth();
        double h = imageView.getImage().getHeight();
        imageView.setX(getPosition().getX() - cameraPosition);
        imageView.setY(getPosition().getY() - (h-w)*(size/w));
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
            this.setVelocity(20, 0);
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