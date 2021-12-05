/* WillHero (Main) Class
 * This is the Application type class. The 'start' method creates the scenes and shows the stage.
 */

import javafx.application.Application;
import javafx.scene.Scene;
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
        Scene mainMenuScene;
        Scene gameScene;
        // *** More scenes will be added as required ***

        // *** INPUT HANDLER TO BE ADDED ***

        // *** ADD mainMenuScene IMPLEMENTATION ***

        GameOrganiser organiser = new GameOrganiser();
        gameScene = new Scene(organiser.getRoot(), 800, 600, Color.BLACK);

        stage.setScene(gameScene); // *** Change this to mainMenuScene after mainMenuScene is implemented +++
        stage.setTitle("Will Hero");
        stage.show();
    }
}