public class Mario extends GameObject
{
    private double size;
    private double gravity;
    private double walkSpeed;
    private double jumpSpeed;

    Mario(Vector2D pos, double size)
    {
        super(pos, new Vector2D(0,0));

        this.size = size;
        this.gravity = 0.3;
        this.walkSpeed = 5;
        this.jumpSpeed = 10;
    }

    public void setHorizontalVelocity(int x)
    {
        this.setVelocity(x * walkSpeed, getVelocity().getY());
    }

    public void moveDown()
    {
        this.setVelocity(getVelocity().getX(), getVelocity().getY() + gravity);
    }

    public void ifLands(Platform platform)
    {
        boolean hasLanded = false;
        double xdist = this.getPosition().getX() - platform.getPosition().getX();
        if(xdist >= -size*2 && xdist <= platform.getLength())
        {
            double verticalDistance = this.getPosition().getY() - platform.getPosition().getY();
            if(verticalDistance >= -size*2)
            {
                hasLanded = true;
            }
        }

        if(hasLanded)
        {
            this.setVelocity(this.getVelocity().getX(), 0);
            this.setPosition(this.getPosition().getX(), platform.getPosition().getY() - size * 2);
        }
    }

    public void jump()
    {
        if(getVelocity().getY() >= 0)
        {
            setVelocity(getVelocity().getX(), -jumpSpeed);
        }
    }

    public void moveFrame()
    {
        setPosition(getPosition().getX() + getVelocity().getX(), getPosition().getY() + getVelocity().getY());
    }

    public double getSize()
    {
        return size;
    }
}
