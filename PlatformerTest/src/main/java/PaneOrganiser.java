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
    private Group group;
    private InputTracker tracker;
    private Mario mario;
    private ArrayList<Platform> platforms;

    private Rectangle marioRect;
    private ArrayList<Rectangle> platformsRect;

    private final double frameRate;

    public PaneOrganiser(InputTracker tracker)
    {
        this.tracker = tracker;
        frameRate = 60;

        group = new Group();
        mario = new Mario(new Vector2D(20,20), 25);
        platforms = new ArrayList<Platform>();
        platforms.add(new Platform(new Vector2D(25, 500), 200));
        platforms.add(new Platform(new Vector2D(300, 400), 100));
        platforms.add(new Platform(new Vector2D(500, 300), 100));

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

        group.getChildren().add(marioRect);
        for(int i = 0; i < platformsRect.size(); i++) {
            group.getChildren().add(platformsRect.get(i));
        }

        this.setupTimeline();
    }

    public void setupTimeline()
    {
        KeyFrame kf = new KeyFrame(Duration.millis(1000/frameRate), new TimeHandler());

        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public Group getRoot()
    {
        return group;
    }

    private class TimeHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            int moveDirection = 0;
            if(tracker.getLeftPressed())
            {
                moveDirection--;
            }
            if(tracker.getRightPressed())
            {
                moveDirection++;
            }
            mario.setHorizontalVelocity(moveDirection);
            mario.moveDown();
            for(int i = 0; i < platforms.size(); i++)
            {
                mario.ifLands(platforms.get(i));
            }
            if(tracker.getUpPressed())
            {
                mario.jump();
            }

            mario.moveFrame();

            marioRect.setX(mario.getPosition().getX());
            marioRect.setY(mario.getPosition().getY());
        }
    }
}
