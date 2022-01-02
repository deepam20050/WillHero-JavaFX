import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PauseMenuController
{
    @FXML
    private Button resumeButton;

    @FXML
    private Button saveGameButton;

    @FXML
    private Button mainMenuButton;

    public Button getResumeButton()
    {
        return resumeButton;
    }

    public Button getSaveGameButton()
    {
        return saveGameButton;
    }

    public Button getMainMenuButton()
    {
        return mainMenuButton;
    }
}
