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

import java.io.IOException;

public class WillHero extends Application
{
    public static final double frameRate = 60;
    public static final double sceneWidth = 1000;
    public static final double sceneHeight = 600;
    public static final InputTracker inputTracker = new InputTracker();

    // UPDATE: gameScene will be used to show all scenes.
    // Multiple roots will be made instead of multiple scenes.
    private Scene gameScene;

    private GameOrganiser organiser;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        // Setting up Game Scene
        try
        {
            gameScene = new Scene(new FXMLLoader(MainMenuController.class.getResource("mainmenu.fxml")).load(),
                    sceneWidth, sceneHeight, Color.rgb(86, 227, 255));
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
        gameScene.setOnMousePressed(e -> {inputTracker.setLeftMousePressed(true);});
        gameScene.setOnMouseReleased(e -> {inputTracker.setLeftMousePressed(false);});

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
        Button timebtn = controller.getTimeChallengeButton();

        playbtn.setOnAction(e -> {
            createNewGame("Regular");
        });
        exitbtn.setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });
        loadfilebtn.setOnAction(e -> {
            goToLoadGameScene();
        });
        timebtn.setOnAction(e -> {
            createNewGame("TimeChallenge");
        });
    }

    public void goToLostGameScene(int location)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(LostGameController.class.getResource("lostgame.fxml"));
        try {
            gameScene.setRoot(fxmlLoader.load());
        }
        catch(Exception e) {
            System.out.println(e);
            System.exit(0);
        }

        LostGameController controller = fxmlLoader.getController();
        Button playAgainButton = controller.getPlayAgainButton();
        Button mainMenuButton = controller.getMainMenuButton();
        controller.setLocationReachedText(location);

        playAgainButton.setOnAction(e -> {
            createNewGame(organiser.getGame().getGameMode());
        });
        mainMenuButton.setOnAction(e -> {
            goToMainMenu();
        });
    }

    public void goToSaveGameScene()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(SaveGameController.class.getResource("savegame.fxml"));
        try {
            gameScene.setRoot(fxmlLoader.load());
        }
        catch(Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        SaveGameController controller = fxmlLoader.getController();
        controller.getSaveFile1Button().setOnAction(e -> {
            organiser.getGame().save_file(1);
        });
        controller.getSaveFile2Button().setOnAction(e -> {
            organiser.getGame().save_file(2);
        });
        controller.getSaveFile3Button().setOnAction(e -> {
            organiser.getGame().save_file(3);
        });
        controller.getSaveFile4Button().setOnAction(e -> {
            organiser.getGame().save_file(4);
        });
        controller.getSaveFile5Button().setOnAction(e -> {
            organiser.getGame().save_file(5);
        });
        controller.getSaveFile6Button().setOnAction(e -> {
            organiser.getGame().save_file(6);
        });
        controller.getGoBackButton().setOnAction(e -> {
            gameScene.setRoot(organiser.getRoot());
        });
    }

    public void goToLoadGameScene()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(LoadGameController.class.getResource("loadgame.fxml"));
        try {
            gameScene.setRoot(fxmlLoader.load());
        }
        catch(Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        LoadGameController controller = fxmlLoader.getController();
        controller.getLoadFile1Button().setOnAction((e -> {
            organiser.getGame().load_file(1);
            if(organiser.getGame() != null)
                gameScene.setRoot(organiser.getRoot());
        }));
        controller.getLoadFile2Button().setOnAction((e -> {
            organiser.getGame().load_file(2);
            if(organiser.getGame() != null)
                gameScene.setRoot(organiser.getRoot());
        }));
        controller.getLoadFile3Button().setOnAction((e -> {
            organiser.getGame().load_file(3);
            if(organiser.getGame() != null)
                gameScene.setRoot(organiser.getRoot());
        }));
        controller.getLoadFile4Button().setOnAction((e -> {
            organiser.getGame().load_file(4);
            if(organiser.getGame() != null)
                gameScene.setRoot(organiser.getRoot());
        }));
        controller.getLoadFile5Button().setOnAction((e -> {
            organiser.getGame().load_file(5);
            if(organiser.getGame() != null)
                gameScene.setRoot(organiser.getRoot());
        }));
        controller.getLoadFile6Button().setOnAction((e -> {
            organiser.getGame().load_file(6);
            if(organiser.getGame() != null)
                gameScene.setRoot(organiser.getRoot());
        }));
        controller.getGoBackButton().setOnAction(e -> {
            goToMainMenu();
        });
    }

    private void createNewGame(String gameMode)
    {
        organiser = new GameOrganiser(this, gameMode);
        gameScene.setRoot(organiser.getRoot());
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