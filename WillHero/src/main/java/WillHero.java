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
import java.util.ArrayList;

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
        organiser = new GameOrganiser(this, "Regular");
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
        gameScene.setOnKeyPressed(e -> {
            inputTracker.setZPressed(true);
        });
        gameScene.setOnKeyReleased(e -> {
            inputTracker.setZPressed(false);
        });

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
        Button flappyHerobtn = controller.getFlappyHeroButton();
        Button twoPlayerbtn = controller.getTwoPlayerButton();

        playbtn.setOnAction(e -> {
            goToStartGameScene("Regular");
        });
        exitbtn.setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });
        loadfilebtn.setOnAction(e -> {
            goToLoadGameScene();
        });
        timebtn.setOnAction(e -> {
            goToStartGameScene("TimeChallenge");
        });
        flappyHerobtn.setOnAction(e -> {
            goToStartGameScene("Flappy Hero");
        });
        twoPlayerbtn.setOnAction(e -> {
            goToStartGameScene("Multiplayer");
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

    public void goToWonGameScene()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(WonGameController.class.getResource("wongame.fxml"));
        try {
            gameScene.setRoot(fxmlLoader.load());
        }
        catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        WonGameController controller = fxmlLoader.getController();

        controller.getPlayAgainButton().setOnAction(e -> {
            createNewGame(organiser.getGame().getGameMode());
        });
        controller.getMainMenuButton().setOnAction(e -> {
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
        ArrayList<Button> saveButtons = controller.getAllSaveButtons();

        ArrayList<Text> locationTexts = controller.getAllLocationTexts();
        ArrayList<Text> gameModeTexts = controller.getAllGameModeTexts();

        Game currentGame = organiser.getGame();
        for(int i = 0; i < locationTexts.size(); i++)
        {
            organiser.deserializeGame(i+1);
            if(organiser.getGame() == null)
            {
                locationTexts.get(i).setText("---");
                gameModeTexts.get(i).setText("---");
            }
            else
            {
                Integer playerLocation = organiser.getGame().getPlayer().getHero().getLocation();
                locationTexts.get(i).setText(playerLocation.toString());
                gameModeTexts.get(i).setText(organiser.getGame().getGameMode());
            }
        }
        organiser.setGame(currentGame);

        for(int i = 0; i < saveButtons.size(); i++)
        {
            final int j = i;
            saveButtons.get(i).setOnAction(e ->
            {
                organiser.serializeGame(j+1);
                Integer playerLocation = organiser.getGame().getPlayer().getHero().getLocation();
                locationTexts.get(j).setText(playerLocation.toString());
                gameModeTexts.get(j).setText(organiser.getGame().getGameMode());
            });
        }
        controller.getGoBackButton().setOnAction(e -> {
            organiser.loadRoot();
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
        ArrayList<Button> loadButtons = controller.getAllLoadButtons();

        ArrayList<Text> locationTexts = controller.getAllLocationTexts();
        ArrayList<Text> gameModeTexts = controller.getAllGameModeTexts();
        for(int i = 0; i < locationTexts.size(); i++)
        {
            organiser.deserializeGame(i+1);
            if(organiser.getGame() == null)
            {
                locationTexts.get(i).setText("---");
                gameModeTexts.get(i).setText("---");
            }
            else
            {
                Integer playerLocation = organiser.getGame().getPlayer().getHero().getLocation();
                locationTexts.get(i).setText(playerLocation.toString());
                gameModeTexts.get(i).setText(organiser.getGame().getGameMode());
            }
        }

        for(int i = 0; i < loadButtons.size(); i++)
        {
            final int j = i;
            loadButtons.get(i).setOnAction((e ->
            {
                organiser = new GameOrganiser(this, "Regular");
                organiser.deserializeGame(j+1);
                if (organiser.getGame() != null)
                {
                    organiser.loadRoot();
                    System.out.println("LOCATION: " + organiser.getGame().getPlayer().getHero().getLocation());
                    System.out.println("GAME MODE: " + organiser.getGame().getGameMode());

                    gameScene.setRoot(organiser.getRoot());
//                organiser.setUpTimeLine();
                }
                else
                {
                    System.out.println("No game exists in Save File " + (j+1));
                }
            }));
        }
        controller.getGoBackButton().setOnAction(e -> {
            goToMainMenu();
        });
    }

    private void goToStartGameScene(String gameMode)
    {
        FXMLLoader fxmlLoader;

        if(gameMode.equals("Multiplayer"))
            fxmlLoader = new FXMLLoader(StartGameController.class.getResource("startgame_coop.fxml"));
        else if(gameMode.equals("TimeChallenge"))
            fxmlLoader = new FXMLLoader(StartGameController.class.getResource("startgame_timechallenge.fxml"));
        else if(gameMode.equals("Flappy Hero"))
            fxmlLoader = new FXMLLoader(StartGameController.class.getResource("startgame_flappyhero.fxml"));
        else
            fxmlLoader = new FXMLLoader(StartGameController.class.getResource("startgame.fxml"));

        try {
            gameScene.setRoot(fxmlLoader.load());
        }
        catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        StartGameController controller = fxmlLoader.getController();

        controller.getPlayButton().setOnAction(e -> {
            createNewGame(gameMode);
        });
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
    public Scene getGameScene() {
        return gameScene;
    }
}