/* WillHero (Main) Class
 * This is the Application type class. The 'start' method creates the scenes and shows the stage.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    public void start(Stage stage) throws Exception
    {
        double sceneWidth = 1000;
        double sceneHeight = 600;
        InputTracker inputTracker = new InputTracker();

        Scene mainMenuScene;
        Scene gameScene;

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

        // Setting Up Main Menu Code
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenuController.class.getResource("mainmenu.fxml"));
        mainMenuScene = new Scene(fxmlLoader.load());
        MainMenuController controller = fxmlLoader.getController();
        Button playbtn = controller.getPlayButton();
        Button exitbtn = controller.getExitButton();
        Button loadfilebtn = controller.getLoadFileButton();

        playbtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                stage.setScene(gameScene);
            }
        });
        exitbtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                Platform.exit();
                System.exit(0);
            }
        });

        stage.setScene(mainMenuScene); // *** Change this to mainMenuScene after mainMenuScene is implemented +++
        stage.setTitle("Will Hero");
        stage.show();
    }
}