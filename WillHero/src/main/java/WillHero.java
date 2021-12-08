/* WillHero (Main) Class
 * This is the Application type class. The 'start' method creates the scenes and shows the stage.
 */

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class WillHero extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        double sceneWidth = 1000;
        double sceneHeight = 600;
        InputTracker inputTracker = new InputTracker();

        Scene mainMenuScene;
        Scene gameScene;
        // *** More scenes will be added as required ***

        // *** ADD mainMenuScene Set up ***

        // Setting up Game Scene
        GameOrganiser organiser = new GameOrganiser(inputTracker, sceneWidth, sceneHeight);
        gameScene = new Scene(organiser.getRoot(), sceneWidth, sceneHeight, Color.rgb(86,227,255));
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent keyEvent)
            {
                if(keyEvent.getCode().equals(KeyCode.SPACE)) {
                    inputTracker.setSpacePressed(true);
                }
            }
        });
        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent keyEvent)
            {
                if(keyEvent.getCode().equals(KeyCode.SPACE)) {
                    inputTracker.setSpacePressed(false);
                }
            }
        });

        stage.setScene(gameScene); // *** Change this to mainMenuScene after mainMenuScene is implemented +++
        stage.setTitle("Will Hero");
        stage.show();
    }
}