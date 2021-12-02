import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.util.ArrayList;

public class PaneOrganiser
{
    // Group stores the GUI objects (Just rectangles in this case). Scene reads this to know what to display
    private Group group;

    // InputTracker is the same as that in the main class, allows the class to read the input
    private InputTracker tracker;
    // Instance of the Mario and Platforms (Platforms is an arraylist since there can be many of them)
    private Mario mario;
    private ArrayList<Platform> platforms;

    // Rectangles corresponding to all the GameObject type objects
    // Each GameObject would have a corresponding GUI element (Rectangle in this case)so they can be represented on the screen
    private Rectangle marioRect;
    private ArrayList<Rectangle> platformsRect;

    // Framerate of the game
    private final double frameRate;

    public PaneOrganiser(InputTracker tracker)
    {
        this.tracker = tracker;
        frameRate = 60;

        group = new Group();
        // Initial positions of Mario and the platforms, along with platform lengths
        mario = new Mario(new Vector2D(20,20), 25);
        platforms = new ArrayList<Platform>();
        platforms.add(new Platform(new Vector2D(25, 500), 200));
        platforms.add(new Platform(new Vector2D(300, 400), 100));
        platforms.add(new Platform(new Vector2D(500, 300), 100));

        // Rectangles corresponsing to each GameObject are given the same position and shape/size specified by the GameObject
        marioRect = new Rectangle();
        marioRect.setX(mario.getPosition().getX() - mario.getSize());
        marioRect.setY(mario.getVelocity().getY() - mario.getSize());
        marioRect.setWidth(mario.getSize() * 2);
        marioRect.setHeight(mario.getSize() * 2);
        marioRect.setFill(Color.RED);

        platformsRect = new ArrayList<Rectangle>();
        for(int i = 0; i < platforms.size(); i++)
        {
            Platform platform = platforms.get(i);
            Rectangle platformRect = new Rectangle();
            platformRect.setX(platform.getPosition().getX());
            platformRect.setY(platform.getPosition().getY());
            platformRect.setWidth(platform.getLength());
            platformRect.setHeight(10);
            platformRect.setFill(Color.GREEN);

            platformsRect.add(platformRect);
        }

        // All the Rectangle objects are added to the group object
        group.getChildren().add(marioRect);
        for(int i = 0; i < platformsRect.size(); i++) {
            group.getChildren().add(platformsRect.get(i));
        }

        this.setupTimeline();
    }

    // Specifying a timeline, containing a TimeHandler (defined below) and specifying framerate
    public void setupTimeline()
    {
        KeyFrame kf = new KeyFrame(Duration.millis(1000/frameRate), new TimeHandler());

        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    // Getter for the 'group' object (Called when constructing the scene)
    public Group getRoot()
    {
        return group;
    }

    private class TimeHandler implements EventHandler<ActionEvent>
    {
        // Defining what happens every frame
        @Override
        public void handle(ActionEvent event)
        {
            // MoveDirection is just an integer (0: Don't move. 1: Move right. -1: Move left
            int moveDirection = 0;
            if(tracker.getLeftPressed())
            {
                moveDirection--;
            }
            if(tracker.getRightPressed())
            {
                moveDirection++;
            }
            // MoveDirection is multiplied by Mario's walkSpeed variable to set its velocity
            mario.setHorizontalVelocity(moveDirection);
            // Update Mario's vertical velocity by the gravity factor
            mario.moveDown();

            // For all platforms, check if Mario has landed, & perform necessary position/velocity changes
            for(int i = 0; i < platforms.size(); i++)
            {
                mario.ifLands(platforms.get(i));
            }
            // If 'Up' is pressed, update Mario's velocity to jump up.
            if(tracker.getUpPressed())
            {
                mario.jump();
            }

            mario.moveFrame();

            // Updating position of Mario's rectangle corresponding to Mario GameObject's position
            marioRect.setX(mario.getPosition().getX());
            marioRect.setY(mario.getPosition().getY());
        }
    }
}
