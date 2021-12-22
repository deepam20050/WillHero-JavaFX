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
//    @Override
//    public void if_collides (Hero hero)
//    {
//        Vector2D hero_pos = hero.getPosition();
//        Vector2D orc_pos = this.getPosition();
//        double hero_size = hero.getSize();
//        double orc_size = this.size;
//        double dx = orc_pos.getX() - hero_pos.getX();
//        double dy = orc_pos.getY() - hero_pos.getY();
//        if (dx < 0) return;
//        if (dx > hero_size + orc_size) return;
//        if (dy > 0 && dy <= orc_size && dx <= hero_size) {
//            hero.lose_game();
//        }
//        if (dx <= hero_size) {
//            this.setVelocity(30, 0);
//        }
//    }
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