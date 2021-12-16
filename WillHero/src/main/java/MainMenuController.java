import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;


public class MainMenuController
{
    private Stage stage;

    @FXML
    private Button playButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button loadFileButton;

    public Button getPlayButton()
    {
        return playButton;
    }
    public Button getExitButton()
    {
        return exitButton;
    }
    public Button getLoadFileButton()
    {
        return loadFileButton;
    }
}
