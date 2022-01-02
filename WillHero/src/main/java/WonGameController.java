import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WonGameController
{
    @FXML
    private Button playAgainButton;

    @FXML
    private Button mainMenuButton;

    public Button getPlayAgainButton() {
        return playAgainButton;
    }

    public Button getMainMenuButton() {
        return mainMenuButton;
    }
}