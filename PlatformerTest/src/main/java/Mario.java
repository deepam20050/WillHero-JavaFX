// Mario is a Game Object that is essentially the main character the player controls

public class Mario extends GameObject
{
    private double size;
    private double gravity;
    private double walkSpeed;
    private double jumpSpeed;

    Mario(Vector2D pos, double size)
    {
        // set initial position
        super(pos, new Vector2D(0,0));

        this.size = size;
        this.gravity = 0.3;
        this.walkSpeed = 5;
        this.jumpSpeed = 10;
    }

    // Set velocity in x direction depending on direction of movement
    public void setHorizontalVelocity(int x)
    {
        this.setVelocity(x * walkSpeed, getVelocity().getY());
    }

    // Update velocity to slightly downwards.
    // Called every frame by the TimeHandler. Essentially implements gravity.
    public void moveDown()
    {
        this.setVelocity(getVelocity().getX(), getVelocity().getY() + gravity);
    }

    // If landed on a platform, don't move down and stay on the platform
    public void ifLands(Platform platform)
    {
        // Calculating if the player is on/below a platform.
        // BUG: Note that the code does not yet consider if the player is under the platform without being on it, so going under one transports you above it.
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

        // If the character has landed, place it on top of the platform and set vertical velocity as 0.
        if(hasLanded)
        {
            this.setVelocity(this.getVelocity().getX(), 0);
            this.setPosition(this.getPosition().getX(), platform.getPosition().getY() - size * 2);
        }
    }

    // Jump
    // BUG: Player can jump whenever moving downwards, regardless of if they're on a platform or not,
    public void jump()
    {
        if(getVelocity().getY() >= 0)
        {
            setVelocity(getVelocity().getX(), -jumpSpeed);
        }
    }

    // Update player's position based on velocity
    // (Although it's probably better to do this in TimeHandler directly instead of having a separate method)
    public void moveFrame()
    {
        setPosition(getPosition().getX() + getVelocity().getX(), getPosition().getY() + getVelocity().getY());
    }

    public double getSize()
    {
        return size;
    }
}
