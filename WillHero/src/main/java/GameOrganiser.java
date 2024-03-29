/* GameOrganiser Class
 * This class stores all the GUI objects for gameScene and also the method for controlling each frame of the game.
 */

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.scene.control.Label;

import java.io.*;
import java.util.ArrayList;

public class GameOrganiser
{
    private WillHero willHeroApplication;
    private Game game;
    private InputTracker inputTracker;
    private Group root; // Stores all the static GUI + GUI corresponding to all the GameObjects

    // Top Bar Objects
    private ImageView settingsImage;
    private Button settingsButton;
    private Label heroLocationLabel;
    private Label noOfCoinsLabel;

    // Weapon Buttons objects
    private ImageView weapon2Image;
    private ImageView weapon1Image;
    private Button weapon1Button;
    private Button weapon2Button;
    private Label weapon1LevelLabel;
    private Label weapon2LevelLabel;

    // Theme of the level
    private String levelTheme;

    // Scenes Inside Game
    private AnchorPane gameBackground;
    private AnchorPane pauseMenuRoot;
    private AnchorPane resurrectHeroRoot;

    protected Timeline timeline;

    public GameOrganiser(WillHero willHeroApplication, String gameMode)
    {
        this.willHeroApplication = willHeroApplication;

        loadPauseMenu();

        // Resurrect Hero Root Setup
        FXMLLoader resurrectHeroLoader = new FXMLLoader(ResurrectHeroController.class.getResource("resurrecthero.fxml"));
        try {
            resurrectHeroRoot = resurrectHeroLoader.load();
        }
        catch(Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        ResurrectHeroController resurrectHeroController = resurrectHeroLoader.getController();
        resurrectHeroController.getResurrectYesButton().setOnAction(e -> {
            resurrectHero();
        });
        resurrectHeroController.getResurrectNoButton().setOnAction(e -> {
            willHeroApplication.goToLostGameScene(game.getPlayer().getHero().getLocation());
            timeline.stop();
        });

        this.inputTracker = willHeroApplication.getInputTracker();
        game = new Game(gameMode);

        setUpTopBar();

        // DISPLAY WEAPON 1 BUTTON (SWORD)
        weapon1Image = new ImageView(new Image("file:assets/SwordButton.png"));
        weapon1Image.setFitWidth(90);
        weapon1Image.setPreserveRatio(true);
        weapon1Image.setTranslateX(25);
        weapon1Image.setTranslateY(WillHero.sceneHeight - weapon1Image.getFitWidth() - 25);
        weapon1Image.setOpacity(0.9);
        weapon1Button = new Button();
        weapon1Button.setPrefWidth(90);
        weapon1Button.setPrefHeight(90);
        weapon1Button.setTranslateX(weapon1Image.getTranslateX());
        weapon1Button.setTranslateY(weapon1Image.getTranslateY());
        weapon1Button.setOpacity(0);

        // WEAPON 1 LEVEL TEXT
        weapon1LevelLabel = new Label("1");
        weapon1LevelLabel.setTextFill(Color.YELLOW);
        weapon1LevelLabel.setFont(new Font("Arial", 25));
        weapon1LevelLabel.setTranslateX(weapon1Button.getTranslateX() + 60);
        weapon1LevelLabel.setTranslateY(weapon1Button.getTranslateY() + 60);
        weapon1LevelLabel.setStyle("-fx-effect: dropshadow( one-pass-box , gray , 0 , 0.0 , -2 , 0 )");

        // DISPLAY WEAPON 2 BUTTON (THROWING KNIVES)
        weapon2Image = new ImageView(new Image("file:assets/ThrowingKnivesButton.png"));
        weapon2Image.setFitWidth(90);
        weapon2Image.setPreserveRatio(true);
        weapon2Image.setTranslateX(25 + weapon1Image.getFitWidth() + 10);
        weapon2Image.setTranslateY(WillHero.sceneHeight - weapon2Image.getFitWidth() - 25);
        weapon2Image.setOpacity(0.4);
        weapon2Button = new Button();
        weapon2Button.setPrefWidth(90);
        weapon2Button.setPrefHeight(90);
        weapon2Button.setTranslateX(weapon2Image.getTranslateX());
        weapon2Button.setTranslateY(weapon2Image.getTranslateY());
        weapon2Button.setOpacity(0);

        // WEAPON 2 LEVEL TEXT
        weapon2LevelLabel = new Label("1");
        weapon2LevelLabel.setTextFill(Color.YELLOW);
        weapon2LevelLabel.setFont(new Font("Arial", 25));
        weapon2LevelLabel.setTranslateX(weapon2Button.getTranslateX() + 60);
        weapon2LevelLabel.setTranslateY(weapon2Button.getTranslateY() + 60);
        weapon2LevelLabel.setStyle("-fx-effect: dropshadow( one-pass-box , gray , 0 , 0.0 , -2 , 0 )");

        // WEAPON 1 BUTTON ON CLICK
        weapon1Button.setOnAction(e -> {
            for(Player player: game.getListOfPlayers())
                player.getHero().getHelmet().setSelectedWeapon(0);
//            game.getPlayer().getHero().getHelmet().setSelectedWeapon(0);
        });
        // WEAPON 2 BUTTON ON CLICK
        weapon2Button.setOnAction(e -> {
            for(Player player: game.getListOfPlayers())
            {
                System.out.println("Equipped throwing knives");
                player.getHero().getHelmet().setSelectedWeapon(1);
            }
//            game.getPlayer().getHero().getHelmet().setSelectedWeapon(1);
        });

        setLevelTheme();

        this.root = new Group();
        this.loadRoot();
        this.setUpTimeLine();
    }

    private void setLevelTheme()
    {
        if(game.getGameMode().equals("TimeChallenge"))
            levelTheme = "Night";
        else
            levelTheme = "Day";
    }

    protected void loadPauseMenu()
    {
        // Pause Menu Root setup
        FXMLLoader pauseMenuLoader = new FXMLLoader(PauseMenuController.class.getResource("pausemenu.fxml"));
        try {
            pauseMenuRoot = pauseMenuLoader.load();
        }
        catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        PauseMenuController pauseMenuController = pauseMenuLoader.getController();
        pauseMenuController.getResumeButton().setOnAction(e -> {
            resumeGame();
        });
        pauseMenuController.getMainMenuButton().setOnAction(e -> {
            willHeroApplication.goToMainMenu();
            timeline.stop();
        });
        pauseMenuController.getSaveGameButton().setOnAction(e -> {
            willHeroApplication.goToSaveGameScene();
        });
    }

    protected void setUpTopBar()
    {
        // DISPLAYING HERO LOCATION
        heroLocationLabel = new Label();
        heroLocationLabel.setTranslateX(WillHero.sceneWidth/2);
        heroLocationLabel.setTranslateY(50);
        heroLocationLabel.setTextFill(Color.WHITE);
        heroLocationLabel.setFont(new Font("Arial", 12));
        heroLocationLabel.setScaleX(5);
        heroLocationLabel.setScaleY(5);
        heroLocationLabel.setStyle("-fx-effect: dropshadow( one-pass-box , gray , 0 , 0.0 , -1 , 0 )");

        // DISPLAY NUMBER OF COINS
        noOfCoinsLabel = new Label("0");
        ImageView coinImage = new ImageView(new Image("file:assets/CoinSprite.png"));
        coinImage.setFitWidth(30);
        coinImage.setPreserveRatio(true);
        noOfCoinsLabel.setGraphic(coinImage);
        noOfCoinsLabel.setTextFill(Color.YELLOW);
        noOfCoinsLabel.setFont(new Font("Arial", 40));
        noOfCoinsLabel.setTranslateX(WillHero.sceneWidth - 100);
        noOfCoinsLabel.setTranslateY(20);
        noOfCoinsLabel.setStyle("-fx-effect: dropshadow( one-pass-box , gray , 0 , 0.0 , -4 , 0 )");

        // DISPLAY SETTINGS BUTTON
        settingsImage = new ImageView(new Image("file:assets/SettingsSprite.png"));
        settingsImage.setFitWidth(50);
        settingsImage.setPreserveRatio(true);
        settingsImage.setTranslateX(20);
        settingsImage.setTranslateY(20);
        settingsImage.setStyle("-fx-effect: dropshadow( one-pass-box , gray , 0 , 0.0 , -4 , 0 )");
        settingsImage.setOpacity(0.9);
        settingsButton = new Button();
        settingsButton.setPrefWidth(50);
        settingsButton.setPrefHeight(50);
        settingsButton.setTranslateX(settingsImage.getTranslateX());
        settingsButton.setTranslateY(settingsImage.getTranslateY());
        settingsButton.setOpacity(0);

        // SETTINGS BUTTON ON CLICK
        settingsButton.setOnAction(e -> {
            pauseGame();
        });
    }

    public void loadBackground()
    {
        if(game == null)
            return;

        AnchorPane newBackground = null;

        String backgroundFile;
        if(levelTheme.equals("Night")) {
            backgroundFile = "gamebackground_dark.fxml";
        }
        else if(levelTheme.equals("Dawn")) {
            backgroundFile = "gamebackground_dawn.fxml";
        }
        else {
            backgroundFile = "gamebackground.fxml";
        }

        try {
           newBackground = FXMLLoader.load(getClass().getResource(backgroundFile));
        }
        catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }

        if(gameBackground == null)
            gameBackground = new AnchorPane();
        if(newBackground != null)
        {
            gameBackground.getChildren().removeAll(gameBackground.getChildren());
            gameBackground.getChildren().addAll(newBackground);
        }
    }

    public void loadRoot()
    {
        loadRoot(false);
    }
    public void loadRoot(boolean firstTime)
    {
        root.getChildren().removeAll(root.getChildren());
        setLevelTheme();
        loadBackground();
        root.getChildren().add(gameBackground);

        ColorAdjust darker = new ColorAdjust();

        if(game.getGameMode() == "Regular") {
            darker.setBrightness(0);
        }
        else if(game.getGameMode() == "TimeChallenge") {
            darker.setBrightness(-0.3);
            heroLocationLabel.setStyle("-fx-effect: dropshadow( one-pass-box , #505050 , 0 , 0.0 , -1 , 0 )");
            noOfCoinsLabel.setStyle("-fx-effect: dropshadow( one-pass-box , #505050 , 0 , 0.0 , -4 , 0 )");
            settingsImage.setStyle("-fx-effect: dropshadow( one-pass-box , #505050 , 0 , 0.0 , -4 , 0 )");

            ImageView coinImage = new ImageView(new Image("file:assets/TimerSprite.png"));
            coinImage.setFitWidth(30);
            coinImage.setPreserveRatio(true);
            noOfCoinsLabel.setGraphic(coinImage);
            noOfCoinsLabel.setTextFill(Color.rgb(195,195,255));
        }

        // Adding all Level GUI objects created to root
        Level level = game.get_current_level();
        // Adding all Island Background Objects
        for(Island island: level.getIslands())
        {
            island.loadImageView();
            island.getImageView().setEffect(darker);
            if(firstTime)
                island.loadBackgroundImageViews();
            for(ImageView backgroundImg: island.getBackgroundObjects())
            {
                backgroundImg.setEffect(darker);
                root.getChildren().add(backgroundImg);
            }
        }
        // Adding All GameObjects
        for(ArrayList<? extends GameObject> listOfObjects: level.getAllObjectsInLevel())
        {
            for(GameObject obj: listOfObjects)
            {
                if(firstTime)
                    obj.loadImageView();
                root.getChildren().add(obj.getImageView());
            }
        }

        // Adding All Falling Bridges to the level
        for (FallingBridge x : level.getObstacles()) {
            for (Plank y : x.getPlanks()) {
                y.loadImageView();
                root.getChildren().add(y.getImageView());
            }
        }

        // Adding Ghost Hero
        if(game.get_current_level().getGhostHero() != null)
        {
            game.get_current_level().getGhostHero().loadImageView();
            root.getChildren().add(game.get_current_level().getGhostHero().getImageView());
        }

        // Displaying hero
        for(Player player: game.getListOfPlayers())
        {
            player.getHero().loadImageView();
            root.getChildren().add(player.getHero().getImageView());

            player.getHero().getHelmet().getWeapon(0).loadImageView();
            player.getHero().getHelmet().getWeapon(1).loadImageView();

            root.getChildren().add(player.getHero().getHelmet().getWeapon(0).getImageView());
            root.getChildren().add(player.getHero().getHelmet().getWeapon(1).getImageView());
        }

        // Displaying Projectiles
        for(Projectile projectile: game.getPlayer().getHero().getHelmet().getLaunchedProjectiles())
        {
            if(firstTime)
                projectile.loadImageView();
            root.getChildren().add(projectile.getImageView());
        }

        root.getChildren().add(heroLocationLabel);
        root.getChildren().add(noOfCoinsLabel);
        root.getChildren().add(settingsImage);
        root.getChildren().add(settingsButton);
        root.getChildren().add(weapon1Image);
        root.getChildren().add(weapon1Button);
        root.getChildren().add(weapon1LevelLabel);
        root.getChildren().add(weapon2Image);
        root.getChildren().add(weapon2Button);
        root.getChildren().add(weapon2LevelLabel);
    }

    public void setUpTimeLine()
    {
        if(timeline != null)
            timeline.stop();
        if(game.getGameMode().equals("TimeChallenge"))
        {
            game.getPlayer().add_coins(30);
        }

        KeyFrame kf = new KeyFrame(Duration.millis(1000/WillHero.frameRate), new TimeHandler());

        timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public Group getRoot()
    {
        return root;
    }

    public class TimeHandler implements EventHandler<ActionEvent>
    {
        private int frameCount;
        TimeHandler()
        {
            frameCount = 0;
        }

        // CONTROL WHAT HAPPENS EVERY FRAME
        @Override
        public void handle(ActionEvent event)
        {
            if(game == null || willHeroApplication.getGameScene().getRoot() != root)
                return;
            if(game.isPaused())
            {
                if(!root.getChildren().contains(pauseMenuRoot) && !root.getChildren().contains(resurrectHeroRoot))
                {
                    pauseGame();
                }
                return;
            }

            frameCount++;
            if(game.getGameMode().equals("TimeChallenge"))
            {
                if (frameCount >= WillHero.frameRate)
                {
                    frameCount = 0;
                    game.getPlayer().add_coins(-1);

                    if (game.getPlayer().getNoOfCoins() <= 0)
                    {
                        willHeroApplication.goToLostGameScene(game.getPlayer().getHero().getLocation());
                        timeline.stop();
                    }
                }
                game.get_current_level().getGhostHero().updateFrame(game.getCameraPosition());
            }

            if(game.isGameLost())
            {
                game.pause();
                if(!game.isResurrected() && game.getPlayer().getNoOfCoins() >= game.getCoinsForResurrection())
                {
                    showResurrectHeroMenu();
                }
                else{
                    willHeroApplication.goToLostGameScene(game.getPlayer().getHero().getLocation());
                    timeline.stop();
                }
            }
            else if(game.isGameWon())
            {
                timeline.stop();
                willHeroApplication.goToWonGameScene();
            }

            game.updateCamera();

            for(Player player: game.getListOfPlayers())
            {
                player.getHero().updateFrame(game.getCameraPosition());
            }

            // Updating game state of ALL GAME OBJECTS in the level
            Level level = game.get_current_level();
            for(ArrayList<? extends GameObject> listOfObjects: level.getAllObjectsInLevel())
            {
                for(GameObject obj: listOfObjects)
                {
                    if(obj.isActive())
                    {
                        if(!root.getChildren().contains(obj.getImageView()))
                            root.getChildren().add(obj.getImageView());
                        obj.updateFrame(game.getCameraPosition());
                        for(Player player: game.getListOfPlayers())
                        {
                            obj.if_collides(player.getHero());
                        }
                    }
                    else
                    {
                        if(root.getChildren().contains(obj.getImageView()))
                            root.getChildren().remove(obj.getImageView());
                    }
                }
            }
            for (Orc orc : level.getOrcs()) {
                orc.give_coin_if_killed(game.getPlayer().getHero());
                orc.if_falls(game.getPlayer().getHero());
                if (orc instanceof BossOrc) {
                    if (!orc.isActive()) {
                        game.win_game();
                    }
                }
            }
            for(Orc orc: level.getOrcs())
            {
                orc.give_coin(game.getPlayer().getHero());
                for(Island island: level.getIslands())
                {
                    orc.if_lands(island);
                }
            }
            for (FallingBridge platform : level.getObstacles()) {
                platform.updateFrame(game.getCameraPosition());
            }

            // Falling platforms
            for (FallingBridge platform : level.getObstacles()) {
                for(Player player: game.getListOfPlayers())
                {
                    platform.if_collides(player.getHero());
                }
            }
            // Falling platforms + Orc
            for (FallingBridge platform : level.getObstacles()) {
                for (Plank plank : platform.getPlanks()) {
                    for (Orc x : level.getOrcs()) {
                        x.if_lands_plank(plank);
                    }
                }
            }

            // Checking collision of shooting star with orcs
            for(ShootingStar star: level.getShootingStars())
            {
                if(!star.isActive())
                    continue;
                for(Orc orc: level.getOrcs())
                {
                    if(!orc.isActive())
                        continue;
                    star.if_collides_with_orc(orc);
                }
            }

            // Checking if Hero has Feather activated
            if(game.getPlayer().getHero().getCurrentPowerUp() instanceof Feather)
            {
                if(!levelTheme.equals("Dawn"))
                {
                    levelTheme = "Dawn";
                    loadBackground();
                }
            }
            else
            {
                if(levelTheme.equals("Dawn"))
                {
                    setLevelTheme();
                    loadBackground();
                }
            }

            // Checking all projectiles and if they are being displayed. Deleting inactive projectiles
            for(Player player: game.getListOfPlayers())
            {
                for (int i = 0; i < player.getHero().getHelmet().getLaunchedProjectiles().size(); i++)
                {
                    Projectile projectile = player.getHero().getHelmet().getLaunchedProjectiles().get(i);
                    if (projectile.isActive())
                    {
                        if (!root.getChildren().contains(projectile.getImageView()))
                            root.getChildren().add(projectile.getImageView());
                    } else
                    {
                        if (root.getChildren().contains(projectile.getImageView()))
                            root.getChildren().remove(projectile.getImageView());
                        player.getHero().getHelmet().getLaunchedProjectiles().remove(i);
                    }
                }
            }

            // Checking collisions of all projectiles and all orcs
            for(Player player: game.getListOfPlayers())
            {
                for (int i = 0; i < player.getHero().getHelmet().getLaunchedProjectiles().size(); i++)
                {
                    Projectile projectile = player.getHero().getHelmet().getLaunchedProjectiles().get(i);
                    if (!projectile.isActive())
                        continue;
                    for (int j = 0; j < game.get_current_level().getOrcs().size(); j++)
                    {
                        Orc orc = game.get_current_level().getOrcs().get(j);
                        projectile.ifAttacks(orc);
                    }
                }
            }
            // Checking collisions of sword and all orcs
            for(Player player: game.getListOfPlayers())
            {
                if (player.getHero().getHelmet().getWeapon(0).isActive())
                {
                    Weapon sword = player.getHero().getHelmet().getWeapon(0);

                    for (int j = 0; j < game.get_current_level().getOrcs().size(); j++)
                    {
                        Orc orc = game.get_current_level().getOrcs().get(j);
                        sword.ifAttacks(orc);
                    }
                }
            }

            // Checking collisions of all orcs with each other
            for(int i = 0; i < level.getOrcs().size(); i++)
            {
                for(int j = 0; j < level.getOrcs().size(); j++)
                {
                    if(i == j)
                        continue;
                    level.getOrcs().get(i).if_collides_with_orc(level.getOrcs().get(j));
                }
            }

            // Syncing coins among players
            // In the current multiplayer co-op scenario, both players share the same coin pile
            for(int i = 1; i < game.getListOfPlayers().size(); i++)
            {
                game.getPlayer().add_coins(game.getListOfPlayers().get(i).getNoOfCoins());
                game.getListOfPlayers().get(i).add_coins(-game.getListOfPlayers().get(i).getNoOfCoins());
            }

            // Syncing weapons among players
            for(int i = 0; i < 2; i++)
            {
                int maxLevel = 0;
                for(Player player: game.getListOfPlayers())
                {
                    if(player.getHero().getHelmet().getWeapon(i).getLevel() > maxLevel)
                        maxLevel = player.getHero().getHelmet().getWeapon(i).getLevel();
                }
                for(Player player: game.getListOfPlayers())
                {
                    player.getHero().getHelmet().getWeapon(i).setLevel(maxLevel);
                }
            }

            // UPDATING WEAPONS BUTTONS DISPLAY
            // Sword
            if(game.getPlayer().getHero().getHelmet().getWeapon(0).getLevel() > 0)
            {
                Integer weaponLevel = game.getPlayer().getHero().getHelmet().getWeapon(0).getLevel();
                weapon1LevelLabel.setText(weaponLevel.toString());
            }
            else
            {
                weapon1LevelLabel.setText("");
            }

            // Throwing Knives
            if(game.getPlayer().getHero().getHelmet().getWeapon(1).getLevel() > 0)
            {
                Integer weaponLevel = game.getPlayer().getHero().getHelmet().getWeapon(1).getLevel();
                weapon2LevelLabel.setText(weaponLevel.toString());
            }
            else
            {
                weapon2LevelLabel.setText("");
            }

            weapon1Image.setOpacity(0.4);
            weapon2Image.setOpacity(0.4);
            int selected_index = game.getPlayer().getHero().getHelmet().getSelectedWeaponIndex();
            if(selected_index == 0)
                weapon1Image.setOpacity(0.9);
            else if(selected_index == 1)
                weapon2Image.setOpacity(0.9);

            // UPDATING POSITIONS OF ALL GAMEOBJECTS
            for(int i = 0; i < game.get_current_level().getIslands().size(); i++)
                game.get_current_level().getIslands().get(i).updateFrame(game.getCameraPosition());
            for(int i = 0; i < game.get_current_level().getCoins().size(); i++)
                game.get_current_level().getCoins().get(i).updateFrame(game.getCameraPosition());
            for(int i = 0; i < game.get_current_level().getChests().size(); i++)
                game.get_current_level().getChests().get(i).updateFrame(game.getCameraPosition());

            Integer heroLocation = game.getPlayer().getHero().getLocation();
            heroLocationLabel.setText(heroLocation.toString());
            Integer noOfCoins = game.getPlayer().getNoOfCoins();
            noOfCoinsLabel.setText(noOfCoins.toString());
        }
    }

    public void serializeGame(int fileNo)
    {
        ObjectOutputStream out = null;
        try
        {
            out = new ObjectOutputStream(new FileOutputStream("savefile" + fileNo + ".txt"));
            out.writeObject(game);
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return;
        }
        finally
        {
            try
            {
                out.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
                return;
            }
        }
        System.out.println("Saved: " + "savefile" + fileNo + ".txt");
    }
    public void deserializeGame(int fileNo)
    {
        ObjectInputStream in = null;
        try
        {
            in = new ObjectInputStream(new FileInputStream("savefile" + fileNo + ".txt"));
            Game loadedGame = (Game) in.readObject();
            setGame(loadedGame);
            loadRoot(true);
        }
        catch(IOException e)
        {
//            e.printStackTrace();
            setGame(null);
            return;
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
            return;
        }
        finally
        {
            try
            {
                if(in != null)
                    in.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
                return;
            }
        }
    }

    protected void pauseGame()
    {
        game.pause();
        root.getChildren().add(pauseMenuRoot);
    }
    protected void resumeGame()
    {
        game.resume();
        root.getChildren().remove(pauseMenuRoot);
    }
    protected void showResurrectHeroMenu()
    {
        System.out.println("Game LOST");
        root.getChildren().add(resurrectHeroRoot);
    }
    protected void resurrectHero()
    {
        game.resurrect_hero();
        root.getChildren().remove(resurrectHeroRoot);
    }

    public Game getGame() {
        return game;
    }
    public void setGame(Game game) {
        this.game = game;
    }
}