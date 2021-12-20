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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class GameOrganiser
{
    private double sceneWidth;
    private double sceneHeight;

    private WillHero willHeroApplication;
    private Game game;
    private InputTracker inputTracker;
    private Group root; // Stores all the static GUI + GUI corresponding to all the GameObjects

    // Top Bar Objects
    private ImageView settingsImage;
    private Button settingsButton;
    private Label heroLocationLabel;
    private Label noOfCoinsLabel;

    // Weapons Buttons objects
    private ImageView weapon2Image;
    private ImageView weapon1Image;
    private Button weapon1Button;
    private Button weapon2Button;
    private Label weapon1LevelLabel;
    private Label weapon2LevelLabel;

    // Camera Properties
    private double cameraPosition;
    private double cameraVelocity;

    // Scenes Inside Game
    private AnchorPane pauseMenuRoot;
    private AnchorPane resurrectHeroRoot;

    // Game Framerate
    private final double frameRate;

    public GameOrganiser(WillHero willHeroApplication)
    {
        this.willHeroApplication = willHeroApplication;
        frameRate = 60;
        cameraPosition = 0;
        cameraVelocity = 1;

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
            System.out.println("Main Menu Button clicked");
            willHeroApplication.goToMainMenu();
        });

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
        });

        this.inputTracker = willHeroApplication.getInputTracker();
        this.sceneWidth = willHeroApplication.getSceneWidth();
        this.sceneHeight = willHeroApplication.getSceneHeight();
        root = new Group();
        game = new Game();

        setBackgroundClouds();

        // Adding all Level GUI objects created to root

        Level level = game.get_current_level();
        for(int i = 0; i < level.getIslands().size(); i++) {
            Island island = level.getIslands().get(i);
            for(int j = 0; j < island.getBackgroundObjects().size(); j++) {
                root.getChildren().add(island.getBackgroundObjects().get(j));
            }
            root.getChildren().add(island.getImageView());
        }
        for(int i = 0; i < level.getChests().size(); i++) {
            root.getChildren().add(level.getChests().get(i).getImageView());
        }
        for(int i = 0; i < level.getOrcs().size(); i++) {
            root.getChildren().add(level.getOrcs().get(i).getImageView());
        }
        for(int i = 0; i < level.getCoins().size(); i++) {
            root.getChildren().add(level.getCoins().get(i).getImageView());
        }

        // Displaying hero
        root.getChildren().add(game.getPlayer().getHero().getImageView());

        // displaying weapons
        root.getChildren().add(game.getPlayer().getHero().getHelmet().getWeapon(0).getImageView());
        root.getChildren().add(game.getPlayer().getHero().getHelmet().getWeapon(1).getImageView());

        // DISPLAYING HERO LOCATION
        heroLocationLabel = new Label();
        heroLocationLabel.setTranslateX(sceneWidth/2);
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
        noOfCoinsLabel.setTranslateX(sceneWidth - 100);
        noOfCoinsLabel.setTranslateY(20);
        noOfCoinsLabel.setStyle("-fx-effect: dropshadow( one-pass-box , gray , 0 , 0.0 , -4 , 0 )");

        root.getChildren().add(heroLocationLabel);
        root.getChildren().add(noOfCoinsLabel);

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

        root.getChildren().add(settingsImage);
        root.getChildren().add(settingsButton);

        // SETTINGS BUTTON ON CLICK
        settingsButton.setOnAction(e -> {
            pauseGame();
        });

        // DISPLAY WEAPON 1 BUTTON (SWORD)
        weapon1Image = new ImageView(new Image("file:assets/SwordButton.png"));
        weapon1Image.setFitWidth(90);
        weapon1Image.setPreserveRatio(true);
        weapon1Image.setTranslateX(25);
        weapon1Image.setTranslateY(sceneHeight - weapon1Image.getFitWidth() - 25);
        weapon1Image.setOpacity(0.9);
        weapon1Button = new Button();
        weapon1Button.setPrefWidth(90);
        weapon1Button.setPrefHeight(90);
        weapon1Button.setTranslateX(weapon1Image.getTranslateX());
        weapon1Button.setTranslateY(weapon1Image.getTranslateY());
        weapon1Button.setOpacity(0);

        root.getChildren().add(weapon1Image);
        root.getChildren().add(weapon1Button);

        // WEAPON 1 LEVEL TEXT
        weapon1LevelLabel = new Label("1");
        weapon1LevelLabel.setTextFill(Color.YELLOW);
        weapon1LevelLabel.setFont(new Font("Arial", 25));
        weapon1LevelLabel.setTranslateX(weapon1Button.getTranslateX() + 60);
        weapon1LevelLabel.setTranslateY(weapon1Button.getTranslateY() + 60);
        weapon1LevelLabel.setStyle("-fx-effect: dropshadow( one-pass-box , gray , 0 , 0.0 , -2 , 0 )");

        root.getChildren().add(weapon1LevelLabel);

        // DISPLAY WEAPON 2 BUTTON (THROWING KNIVES)
        weapon2Image = new ImageView(new Image("file:assets/ThrowingKnivesButton.png"));
        weapon2Image.setFitWidth(90);
        weapon2Image.setPreserveRatio(true);
        weapon2Image.setTranslateX(25 + weapon1Image.getFitWidth() + 10);
        weapon2Image.setTranslateY(sceneHeight - weapon2Image.getFitWidth() - 25);
        weapon2Image.setOpacity(0.4);
        weapon2Button = new Button();
        weapon2Button.setPrefWidth(90);
        weapon2Button.setPrefHeight(90);
        weapon2Button.setTranslateX(weapon2Image.getTranslateX());
        weapon2Button.setTranslateY(weapon2Image.getTranslateY());
        weapon2Button.setOpacity(0);

        root.getChildren().add(weapon2Image);
        root.getChildren().add(weapon2Button);

        // WEAPON 2 LEVEL TEXT
        weapon2LevelLabel = new Label("1");
        weapon2LevelLabel.setTextFill(Color.YELLOW);
        weapon2LevelLabel.setFont(new Font("Arial", 25));
        weapon2LevelLabel.setTranslateX(weapon2Button.getTranslateX() + 60);
        weapon2LevelLabel.setTranslateY(weapon2Button.getTranslateY() + 60);
        weapon2LevelLabel.setStyle("-fx-effect: dropshadow( one-pass-box , gray , 0 , 0.0 , -2 , 0 )");

        root.getChildren().add(weapon2LevelLabel);

        // WEAPON 1 BUTTON ON CLICK
        weapon1Button.setOnAction(e -> {game.getPlayer().getHero().getHelmet().setSelectedWeapon(0);});
        // WEAPON 2 BUTTON ON CLICK
        weapon2Button.setOnAction(e -> {game.getPlayer().getHero().getHelmet().setSelectedWeapon(1);});

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
            if(game.isPaused())
                return;

            if(game.isGameLost())
            {
                game.pause();
                if(!game.isResurrected())
                {
                    showResurrectHeroMenu();
                }
                else{
                    willHeroApplication.goToLostGameScene(game.getPlayer().getHero().getLocation());
                }
            }

            updateCamera();
            game.getPlayer().getHero().move_down();

            Level level = game.get_current_level();
            for(int i = 0; i < level.getIslands().size(); i++)
            {
                game.getPlayer().getHero().if_lands(level.getIslands().get(i));
            }

            game.getPlayer().getHero().updatePosition(cameraPosition);
            game.getPlayer().getHero().move_forward(inputTracker.isLeftMousePressed() || inputTracker.isSpacePressed());
            game.getPlayer().getHero().if_falls();

            // Updating position/game state of all Orcs in the game
            ArrayList<Orc> orcs = level.getOrcs();
            for(int i = 0; i < orcs.size(); i++)
            {
                Orc orc = orcs.get(i);
                if(orc.isActive())
                {
                    orc.move_down();
                    for (int j = 0; j < level.getIslands().size(); j++)
                    {
                        orc.if_lands(level.getIslands().get(j));
                    }
                    orc.updatePosition(cameraPosition);
                }
                else
                {
                    orcs.remove(i--);
                }
            }

            // Checking all projectiles and if they are being displayed. Deleting inactive projectiles
            for(int i = 0; i < game.getPlayer().getHero().getHelmet().getLaunchedProjectiles().size(); i++)
            {
                Projectile projectile = game.getPlayer().getHero().getHelmet().getLaunchedProjectiles().get(i);
                if(projectile.isActive())
                {
                    if(!root.getChildren().contains(projectile.getImageView()))
                        root.getChildren().add(projectile.getImageView());
                }
                else
                {
                    if(root.getChildren().contains(projectile.getImageView()))
                        root.getChildren().remove(projectile.getImageView());
                    game.getPlayer().getHero().getHelmet().getLaunchedProjectiles().remove(i);
                }
            }

            // Checking collisions of all projectiles and all orcs
            for(int i = 0; i < game.getPlayer().getHero().getHelmet().getLaunchedProjectiles().size(); i++)
            {
                Projectile projectile = game.getPlayer().getHero().getHelmet().getLaunchedProjectiles().get(i);
                if(!projectile.isActive())
                    continue;
                for(int j = 0; j < game.get_current_level().getOrcs().size(); j++)
                {
                    Orc orc = game.get_current_level().getOrcs().get(j);
                    projectile.ifAttacks(orc);
                }
            }

            // Checking collisions of sword and all orcs
            if(game.getPlayer().getHero().getHelmet().getWeapon(0).isActive())
            {
                Weapon sword = game.getPlayer().getHero().getHelmet().getWeapon(0);

                for(int j = 0; j < game.get_current_level().getOrcs().size(); j++)
                {
                    Orc orc = game.get_current_level().getOrcs().get(j);
                    sword.ifAttacks(orc);
                }
            }

            // Checking collision of hero with coins
            for (Coin x : level.getCoins()) {
                x.if_collides(game.getPlayer().getHero());
            }

            // Checking collision of hero with coin/weapon chests
            for (Chest x : level.getChests()) {
                x.if_collides(game.getPlayer().getHero());
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
                game.get_current_level().getIslands().get(i).updatePosition(cameraPosition);
            for(int i = 0; i < game.get_current_level().getCoins().size(); i++)
                game.get_current_level().getCoins().get(i).updatePosition(cameraPosition);
            for(int i = 0; i < game.get_current_level().getChests().size(); i++)
                game.get_current_level().getChests().get(i).updatePosition(cameraPosition);

            Integer heroLocation = game.getPlayer().getHero().getLocation();
            heroLocationLabel.setText(heroLocation.toString());
            Integer noOfCoins = game.getPlayer().getNoOfCoins();
            noOfCoinsLabel.setText(noOfCoins.toString());
        }
    }

    private void pauseGame()
    {
        game.pause();
        root.getChildren().addAll(pauseMenuRoot);
    }
    private void resumeGame()
    {
        game.resume();
        root.getChildren().removeAll(pauseMenuRoot);
    }
    private void showResurrectHeroMenu()
    {
        System.out.println("Game LOST");
        root.getChildren().addAll(resurrectHeroRoot);
    }
    private void resurrectHero()
    {
        game.resurrect_hero();
        root.getChildren().removeAll(resurrectHeroRoot);
    }

    private void updateCamera()
    {
        if(game.getPlayer().getHero().getPosition().getX() - cameraPosition <= 100)
            cameraVelocity = 0;
        else if(game.getPlayer().getHero().getPosition().getX() - cameraPosition <= 250)
            cameraVelocity = 2;
        else if(game.getPlayer().getHero().getPosition().getX() - cameraPosition >= sceneWidth - 100)
            cameraPosition = game.getPlayer().getHero().getPosition().getX() - sceneWidth + 100;
        else
            cameraVelocity = 7;
        cameraPosition += cameraVelocity;
    }

    public Game getGame() {
        return game;
    }

    private void setBackgroundClouds()
    {
        ImageView cloud1 = new ImageView(new Image("file:assets/Cloud1.png"));
        cloud1.setFitWidth(200);
        cloud1.setPreserveRatio(true);
        cloud1.setTranslateX(70);
        cloud1.setTranslateY(180);

        ImageView cloud2 = new ImageView(new Image("file:assets/Cloud2.png"));
        cloud2.setFitWidth(175);
        cloud2.setPreserveRatio(true);
        cloud2.setTranslateX(240);
        cloud2.setTranslateY(100);

        ImageView cloud3 = new ImageView(new Image("file:assets/Cloud3.png"));
        cloud3.setFitWidth(130);
        cloud3.setPreserveRatio(true);
        cloud3.setTranslateX(40);
        cloud3.setTranslateY(80);

        ImageView cloud4 = new ImageView(new Image("file:assets/Cloud4.png"));
        cloud4.setFitWidth(250);
        cloud4.setPreserveRatio(true);
        cloud4.setTranslateX(540);
        cloud4.setTranslateY(60);

        ImageView cloud5 = new ImageView(new Image("file:assets/Cloud1.png"));
        cloud5.setFitWidth(250);
        cloud5.setPreserveRatio(true);
        cloud5.setTranslateX(750);
        cloud5.setTranslateY(-40);

        ImageView cloud6 = new ImageView(new Image("file:assets/Cloud6.png"));
        cloud6.setFitWidth(300);
        cloud6.setPreserveRatio(true);
        cloud6.setTranslateX(150);
        cloud6.setTranslateY(-85);

        ImageView cloud7 = new ImageView(new Image("file:assets/Cloud6.png"));
        cloud7.setFitWidth(150);
        cloud7.setPreserveRatio(true);
        cloud7.setTranslateX(850);
        cloud7.setTranslateY(140);

        root.getChildren().add(cloud1);
        root.getChildren().add(cloud2);
        root.getChildren().add(cloud3);
        root.getChildren().add(cloud4);
        root.getChildren().add(cloud5);
        root.getChildren().add(cloud6);
        root.getChildren().add(cloud7);
    }
}