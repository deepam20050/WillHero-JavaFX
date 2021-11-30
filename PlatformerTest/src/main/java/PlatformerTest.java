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
        InputTracker tracker = new InputTracker();
        PaneOrganiser organiser = new PaneOrganiser(tracker);
        Scene scene = new Scene(organiser.getRoot(), 600, 600, Color.BLACK);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent k) {
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

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent k) {
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

        stage.setScene(scene);
        stage.setTitle("Mario!");
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
