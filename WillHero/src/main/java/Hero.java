import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

public class Hero extends GameObject
{
    private Helmet helmet;
    private Player player;
    private int location;
    private double startingLocation;
    private double moveForwardSpeed;
    private double moveForwardDistance;
    private double jumpSpeed;
    private double size; // Side length of the square hero
    private double gravity;
    private double fallBoundary;

    private boolean startedMoving;
    private boolean isMovingForward;
    private double forwardDistanceMoved;
    private boolean hasDashed;

    private ArrayList<Vector2D> positionLogs;
    private PowerUp currentPowerUp;

    private String imagePath;

    public Hero(Player player, double x, double y, double size)
    {
        super(new Vector2D(x,y), new Vector2D(0,0));
        this.player = player;
        location = 0;

        this.startingLocation = x;
        this.size = size;
        this.moveForwardSpeed = 30;
        this.moveForwardDistance = 120;
        this.jumpSpeed = 8;
        this.gravity = 0.25;
        this.fallBoundary = 600;
        this.helmet = new Helmet(getPosition().getX() + size/2, getPosition().getY() + size/2, this);

        startedMoving = false;
        isMovingForward = false;
        forwardDistanceMoved = 0;
        hasDashed = false;

        // Setting up ImageView
        imagePath = "file:assets/HeroSprite1.png";
        this.setImage(new Image(imagePath));
        double w = getImageView().getImage().getWidth();
        double h = getImageView().getImage().getHeight();
        getImageView().setX(x);
        getImageView().setY(y - (h-w)*(size/w));
        getImageView().setFitWidth(size);
        getImageView().setPreserveRatio(true);
        getImageView().setSmooth(true);

        positionLogs = new ArrayList<Vector2D>();
    }

    // Updating the position of the player depending on its velocity
    // Also updates the imageview
    @Override
    public void updateFrame(double cameraPosition)
    {
        move_down();
        this.setPosition(getPosition().getX() + getVelocity().getX(), getPosition().getY() + getVelocity().getY());

        double w = getImageView().getImage().getWidth();
        double h = getImageView().getImage().getHeight();
        getImageView().setX(getPosition().getX() - cameraPosition);
        // Image is not a perfect square but the Hero object is treated like a square
        // Image is moved up slightly so the bottom portion represents the object area
        getImageView().setY(getPosition().getY() - (h-w)*(size/w));

        // Updating Helmet Position
        helmet.setPosition(getPosition().getX() + size/4, getPosition().getY() + size/2);
        helmet.updateFrame(cameraPosition);

        logPosition();

        if(currentPowerUp != null)
        {
            if(currentPowerUp instanceof Feather)
            {
                this.getVelocity().setX(Feather.flySpeed);
                if(WillHero.inputTracker.isLeftMousePressed())
                    jump_up();
            }
            currentPowerUp.usePowerUp();
            if(currentPowerUp.getDuration() < 0)
                unEquipPowerUp();
        }
        else
            move_forward(WillHero.inputTracker.isLeftMousePressed() || WillHero.inputTracker.isSpacePressed());
        if_falls();

        this.location = (int)((this.getPosition().getX() - this.startingLocation - 5)/moveForwardDistance + 1);
    }

    private void logPosition()
    {
        if(startedMoving)
        {
            positionLogs.add(new Vector2D(this.getPosition()));
//        System.out.println("Logged: " + this.getPosition().getX() + " " + this.getPosition().getY());
        }
    }

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
        if((forwardButtonPressed && !hasDashed) || isMovingForward)
        {
            startedMoving = true;
            if(!isMovingForward)
            {
                if(helmet.getCurrentWeapon() != null)
                    helmet.getCurrentWeapon().useWeapon();
            }

            isMovingForward = true;
            if(forwardDistanceMoved + moveForwardSpeed >= moveForwardDistance)
            {
                this.getPosition().setX(getPosition().getX() + moveForwardDistance - forwardDistanceMoved);
                this.getVelocity().setX(0);
                forwardDistanceMoved = 0;
                isMovingForward = false;
                location++;
            }
            else
            {
                this.setVelocity(moveForwardSpeed, 0);
                forwardDistanceMoved += moveForwardSpeed;
            }
        }

        hasDashed = forwardButtonPressed;
    }

    // If the hero lands on an island, it has to jump.
    public void if_lands(Island island)
    {
        boolean hasLanded = false;

        // Checking if the hero has landed on the island
        double xdist = this.getPosition().getX() - island.getPosition().getX();
        if(xdist >= -size && xdist <= island.getLength())
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

    public int getLocation()
    {
        return location;
    }

    // Colliding with a coin or a coin chest should allow that object to add coins to the player.
    public void collect_coins(int added_coins)
    {
        player.add_coins(added_coins);
    }

    public Helmet getHelmet()
    {
        return helmet;
    }
    public Player getPlayer()
    {
        return player;
    }
    public double getSize() {
        return size;
    }
    public ArrayList<Vector2D> getPositionLogs() {
        return positionLogs;
    }
    public PowerUp getCurrentPowerUp() {
        return currentPowerUp;
    }

    public void equipPowerUp(PowerUp powerUp)
    {
        unEquipPowerUp();
        this.currentPowerUp = powerUp;

        if(powerUp instanceof Feather)
        {
            getImageView().setImage(new Image("file:assets/FlyingHeroSprite.png"));
            this.getVelocity().setX(Feather.flySpeed);
            isMovingForward = false;
        }
    }
    public void unEquipPowerUp()
    {
        if(currentPowerUp instanceof Feather)
        {
            getImageView().setImage(new Image(imagePath));
            this.getVelocity().setX(0);
        }
        currentPowerUp = null;
    }

    @Override
    public void if_collides(Hero other) {}
}