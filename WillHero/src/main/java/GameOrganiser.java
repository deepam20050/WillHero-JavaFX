/* GameOrganiser Class
 * This class stores all the GUI objects for gameScene and also the method for controlling each frame of the game.
 */

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.util.Duration;

public class GameOrganiser
{
    private Game game;

    // Stores all the static GUI + GUI corresponding to all the GameObjects
    private Group root;

    // Declaring GUI objects for GameObjects
    // *** TO BE DONE LATER ***

    private final double frameRate;

    public GameOrganiser()
    {
        frameRate = 60;

        root = new Group();
        // Initialising GUI for GameObjects
        // *** TO BE DONE LATER ***

        // Adding all GUI objects created to root
        // *** TO BE DONE LATER ***

        this.setUpTimeLine();
    }

    public void setUpTimeLine()
    {
        KeyFrame kf = new KeyFrame(Duration.millis(1000/frameRate), new TimeHandler());

        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public Group getRoot()
    {
        return root;
    }

    public class TimeHandler implements EventHandler<ActionEvent>
    {
        // CONTROL WHAT HAPPENS EVERY FRAME
        @Override
        public void handle(ActionEvent event)
        {
            // *** TO BE IMPLEMENTED LATER ***
        }
    }
}
