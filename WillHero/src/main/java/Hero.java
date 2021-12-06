import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    // ImageView Attributes
    private String imagePath;
    private ImageView imageView;

    public Hero(Player player, double x, double y, double size)
    {
        super(new Vector2D(x,y), new Vector2D(0,0));
        this.player = player;
        location = 0;

        this.size = size;
        this.moveForwardSpeed = 20;
        this.moveForwardDistance = 50;
        this.jumpSpeed = 7.5;
        this.gravity = 0.25;
        this.fallBoundary = 500;

        isMovingForward = true;
        forwardDistanceMoved = 0;

        // Setting up ImageView
        imagePath = "file:assets/HeroSprite1.png";
        imageView = new ImageView(new Image(imagePath));
        double w = imageView.getImage().getWidth();
        double h = imageView.getImage().getHeight();
        imageView.setX(x);
        imageView.setY(y - (h-w)*(size/w));
        imageView.setFitWidth(size);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        System.out.println(imageView.getImage().getWidth());
        System.out.println(imageView.getImage().getHeight());
    }

    public ImageView getImageView()
    {
        return imageView;
    }

    // Updating the position of the player depending on its velocity
    // Also updates the imageview
    public void updatePosition()
    {
        this.setPosition(getPosition().getX() + getVelocity().getX(), getPosition().getY() + getVelocity().getY());

        double w = imageView.getImage().getWidth();
        double h = imageView.getImage().getHeight();
        imageView.setX(getPosition().getX());
        // Image is not a perfect square but the Hero object is treated like a square
        // Image is moved up slightly so the bottom portion represents the object area
        imageView.setY(getPosition().getY() - (h-w)*(size/w));
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
                this.getPosition().setX(getPosition().getX() + moveForwardDistance - forwardDistanceMoved);
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

    // If the hero falls, player loses the game.
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

    // Colliding with a coin or a coin chest should allow that object to add coins to the player.
    public void collect_coins(int added_coins)
    {
        player.add_coins(added_coins);
    }

    @Override
    public void if_collides(Hero other) {}
}
