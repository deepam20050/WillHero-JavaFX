import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PlatformerTest extends Application
{
    @Override
    public void start(Stage stage)
    {
        // InputTracker simply stores boolean values of the keys that are pressed. Handled below
        InputTracker tracker = new InputTracker();
        // PaneOrganiser objects that contains all the game and GUI objects and controls what happens each frame
        PaneOrganiser organiser = new PaneOrganiser(tracker);

        // Creating a 600x600 scene with BLACK background colour and the group/root from the PaneOrganiser (containing GUI objects)
        Scene scene = new Scene(organiser.getRoot(), 600, 600, Color.BLACK);

        // When a key is pressed, set its corresponding tracker value to true
        scene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent k)
            {
                if (k.getCode().equals(KeyCode.LEFT)) {
                    tracker.setLeftPressed(true);
                }
                if (k.getCode().equals(KeyCode.RIGHT)) {
                    tracker.setRightPressed(true);
                }
                if (k.getCode().equals(KeyCode.UP)) {
                    tracker.setUpPressed(true);
                }
                if (k.getCode().equals(KeyCode.DOWN)) {
                    tracker.setDownPressed(true);
                }
            }
        });

        // When a key is released, set its corresponding tracker value to false
        scene.setOnKeyReleased(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent k)
            {
                if (k.getCode().equals(KeyCode.LEFT)) {
                    tracker.setLeftPressed(false);
                }
                if (k.getCode().equals(KeyCode.RIGHT)) {
                    tracker.setRightPressed(false);
                }
                if (k.getCode().equals(KeyCode.UP)) {
                    tracker.setUpPressed(false);
                }
                if (k.getCode().equals(KeyCode.DOWN)) {
                    tracker.setDownPressed(false);
                }
            }
        });

        // Add the created scene above to the stage and show it
        stage.setScene(scene);
        stage.setTitle("Mario!");
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
