/* GameOrganiser Class
 * This class stores all the GUI objects for gameScene and also the method for controlling each frame of the game.
 */

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class GameOrganiser
{
    private double sceneWidth;
    private double sceneHeight;

    private Game game;
    private InputTracker inputTracker;
    private Group root; // Stores all the static GUI + GUI corresponding to all the GameObjects

    private Label heroLocationLabel;
    private Label noOfCoinsLabel;

    // Declaring GUI objects for GameObjects
    // *** TO BE DONE LATER ***

    private final double frameRate;

    public GameOrganiser(InputTracker inputTracker, double sceneWidth, double sceneHeight)
    {
        frameRate = 60;

        this.inputTracker = inputTracker;
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
        root = new Group();
        game = new Game();
        heroLocationLabel = new Label();
        heroLocationLabel.setTranslateX(sceneWidth/2);
        heroLocationLabel.setTranslateY(50);
        heroLocationLabel.setTextFill(Color.WHITE);
        heroLocationLabel.setFont(new Font("Arial", 12));
        heroLocationLabel.setScaleX(5);
        heroLocationLabel.setScaleY(5);
        heroLocationLabel.setStyle("-fx-effect: dropshadow( one-pass-box , gray , 0 , 0.0 , -1 , 0 )");

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

        ImageView settingsImage = new ImageView(new Image("file:assets/SettingsSprite.png"));
        settingsImage.setFitWidth(50);
        settingsImage.setPreserveRatio(true);
        settingsImage.setTranslateX(20);
        settingsImage.setTranslateY(20);
        settingsImage.setStyle("-fx-effect: dropshadow( one-pass-box , gray , 0 , 0.0 , -4 , 0 )");

        ImageView weapon1Button = new ImageView(new Image("file:assets/SwordButton.png"));
        weapon1Button.setFitWidth(90);
        weapon1Button.setPreserveRatio(true);
        weapon1Button.setTranslateX(25);
        weapon1Button.setTranslateY(sceneHeight - weapon1Button.getFitWidth() - 25);
        weapon1Button.setOpacity(0.4);

        ImageView weapon2Button = new ImageView(new Image("file:assets/SwordButton.png"));
        weapon2Button.setFitWidth(90);
        weapon2Button.setPreserveRatio(true);
        weapon2Button.setTranslateX(25 + weapon1Button.getFitWidth() + 10);
        weapon2Button.setTranslateY(sceneHeight - weapon2Button.getFitWidth() - 25);
        weapon2Button.setOpacity(0.4);

        setBackgroundClouds();

        // Adding all Level GUI objects created to root
        Level level = game.get_current_level();
        for(int i = 0; i < level.getIslands().size(); i++)
        {
            Island island = level.getIslands().get(i);
            for(int j = 0; j < island.getBackgroundObjects().size(); j++)
            {
                root.getChildren().add(island.getBackgroundObjects().get(j));
            }
            root.getChildren().add(island.getImageView());
        }

        for(int i = 0; i < level.getChests().size(); i++)
        {
            root.getChildren().add(level.getChests().get(i).getImageView());
        }

        for(int i = 0; i < level.getOrcs().size(); i++)
        {
            root.getChildren().add(level.getOrcs().get(i).getImageView());
        }
        for(int i = 0; i < level.getCoins().size(); i++)
        {
            root.getChildren().add(level.getCoins().get(i).getImageView());
        }

        root.getChildren().add(game.getPlayer().getHero().getImageView());

        // Adding Buttons and Labels to root
        root.getChildren().add(heroLocationLabel);
        root.getChildren().add(noOfCoinsLabel);
        root.getChildren().add(settingsImage);
        root.getChildren().add(weapon1Button);
        root.getChildren().add(weapon2Button);

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
            game.getPlayer().getHero().move_down();

            Level level = game.get_current_level();
            for(int i = 0; i < level.getIslands().size(); i++)
            {
                game.getPlayer().getHero().if_lands(level.getIslands().get(i));
            }

            game.getPlayer().getHero().updatePosition();
            game.getPlayer().getHero().move_forward(inputTracker.isLeftMousePressed() || inputTracker.isSpacePressed());
//            System.out.println(inputTracker.isLeftMousePressed());
            game.getPlayer().getHero().if_falls();

            // Updating position/game state of all Orcs in the game
            ArrayList<Orc> orcs = level.getOrcs();
            for(int i = 0; i < orcs.size(); i++)
            {
                Orc orc = orcs.get(i);
                orc.move_down();
                for(int j = 0; j < level.getIslands().size(); j++)
                {
                    orc.if_lands(level.getIslands().get(j));
                }
                orc.updatePosition();
            }

            Integer heroLocation = game.getPlayer().getHero().getLocation();
            heroLocationLabel.setText(heroLocation.toString());
        }
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