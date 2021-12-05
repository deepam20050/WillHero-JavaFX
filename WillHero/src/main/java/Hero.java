public class Hero extends GameObject
{
//    private Helmet helmet;
//    private Weapon currentWeapon;
    private Player player;
    private int location;
    private double moveForwardSpeed;
    private double moveForwardDistance;
    private double jumpSpeed;
    private double size; // Side length of the square hero
    private double gravity;
    private double fallBoundary;

    private boolean isMovingForward;
    private double forwardDistanceMoved;

    public Hero(Player player, double x, double y, double size)
    {
        super(new Vector2D(x,y), new Vector2D(0,0));
        this.player = player;
        location = 0;

        this.size = size;
        this.moveForwardSpeed = 20;
        this.moveForwardDistance = 50;
        this.jumpSpeed = 10;
        this.gravity = 0.4;
        this.fallBoundary = 500;

        isMovingForward = true;
        forwardDistanceMoved = 0;
    }

    // *** BELOW 2 TO BE IMPLEMENTED AFTER Helmet & Weapons CLASSES ***
    public void use_weapon() {}
    public void select_weapon() {}

    // Changes the hero's upward velocity, causing it to jump
    public void jump_up()
    {
        this.setVelocity(getVelocity().getX(), -jumpSpeed);
    }

    // Adds player's vertical velocity by the value of gravity.
    // Calling it every frame causes the player to fall with a constant acceleration.
    public void move_down()
    {
        this.setVelocity(getVelocity().getX(), getVelocity().getY() + gravity);
    }

    // When input is given to move forward, the player dashes forward.
    // The forward movement takes a few frames to complete, which is why it is checked if the hero is currently moving forward.
    public void move_forward(boolean forwardButtonPressed) // Parameter to check if the player pressed the button to move forward.
    {
        if(forwardButtonPressed || isMovingForward)
        {
            isMovingForward = true;
            if(forwardDistanceMoved + moveForwardSpeed >= moveForwardDistance)
            {
                this.setPosition().setX(getPosition().getX() + moveForwardDistance - forwardDistanceMoved);
                this.getVelocity().setX(0);
                forwardDistanceMoved = 0;
                isMovingForward = false;
            }
            else
            {
                this.getVelocity().setX(moveForwardSpeed);
                moveForwardDistance += moveForwardSpeed;
            }
        }
    }

    // If the hero lands on an island, it has to jump.
    public void if_lands(Island island)
    {
        boolean hasLanded = false;

        // Checking if the hero has landed on the island
        double xdist = this.getPosition().getX() - island.getPosition().getX();
        if(xdist >= size && xdist <= island.getLength())
        {
            double ydist = this.getPosition().getY() - island.getPosition().getY();
            if(ydist >= -size && ydist < 0)
            {
                hasLanded = true;
            }
        }
        if(hasLanded)
        {
            jump_up();
        }
    }

    // If the hero falls, it loses the game.
    public void if_falls()
    {
        if(getPosition().getY() >= fallBoundary)
        {
            lose_game();
        }
    }

    public void lose_game()
    {
        player.lose_game();
    }

    public void add_coins(int added_coins)
    {
        player.add_coins(added_coins);
    }

    @Override
    public void if_collides(Hero other) {}
}
