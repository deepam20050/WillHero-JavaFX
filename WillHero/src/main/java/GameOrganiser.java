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

public class GameOrganiser
{
    private double sceneWidth;
    private double sceneHeight;

    private Game game;
    private InputTracker inputTracker;
    // Stores all the static GUI + GUI corresponding to all the GameObjects
    private Group root;

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

        root.getChildren().add(game.getPlayer().getHero().getImageView());

        // Adding all Level GUI objects created to root
        // *** TO BE DONE LATER ***
        Level level = game.get_current_level();
        for(int i = 0; i < level.getIslands().size(); i++)
            root.getChildren().add(level.getIslands().get(i).getImageView());
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
            game.getPlayer().getHero().move_forward(inputTracker.isSpacePressed());
            game.getPlayer().getHero().if_falls();

            Integer heroLocation = game.getPlayer().getHero().getLocation();
            heroLocationLabel.setText(heroLocation.toString());
        }
    }
}
