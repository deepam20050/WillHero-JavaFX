/* WillHero (Main) Class
 * This is the Application type class. The 'start' method creates the scenes and shows the stage.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WillHero extends Application
{
    private double sceneWidth = 1000;
    private double sceneHeight = 600;
    private InputTracker inputTracker = new InputTracker();

    // UPDATE: gameScene will be used to show all scenes.
    // Multiple roots will be made instead of multiple scenes.
    private Scene gameScene;

    private GameOrganiser organiser;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        // Setting up Game Scene
        organiser = new GameOrganiser(this);
        gameScene = new Scene(organiser.getRoot(), sceneWidth, sceneHeight, Color.rgb(86,227,255));

        // Setting Up Main Menu Code
        goToMainMenu();

        stage.setScene(gameScene);
        stage.setTitle("Will Hero");
        stage.show();
    }

    // Function to go to main menu
    // Can be called from start() or from the game's pause menu
    public void goToMainMenu()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenuController.class.getResource("mainmenu.fxml"));
        try
        {
            gameScene.setRoot(fxmlLoader.load());
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.exit(0);
        }
        MainMenuController controller = fxmlLoader.getController();
        Button playbtn = controller.getPlayButton();
        Button exitbtn = controller.getExitButton();
        Button loadfilebtn = controller.getLoadFileButton();

        playbtn.setOnAction(e -> {
            createNewGame();
        });
        exitbtn.setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public void goToLostGameScene(int location)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(LostGameController.class.getResource("lostgame.fxml"));
        try
        {
            gameScene.setRoot(fxmlLoader.load());
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.exit(0);
        }

        LostGameController controller = fxmlLoader.getController();
        Button playAgainButton = controller.getPlayAgainButton();
        Button mainMenuButton = controller.getMainMenuButton();
        controller.setLocationReachedText(location);

        playAgainButton.setOnAction(e -> {
            createNewGame();
        });
        mainMenuButton.setOnAction(e -> {
            goToMainMenu();
        });
    }

    private void createNewGame()
    {
        organiser = new GameOrganiser(this);
        gameScene.setOnMousePressed(e -> {inputTracker.setLeftMousePressed(true);});
        gameScene.setOnMouseReleased(e -> {inputTracker.setLeftMousePressed(false);});

        gameScene.setRoot(organiser.getRoot());
        gameScene.setFill(Color.rgb(86,227,255));
    }

    public double getSceneWidth() {
        return sceneWidth;
    }
    public double getSceneHeight() {
        return sceneHeight;
    }
    public InputTracker getInputTracker() {
        return inputTracker;
    }
}