import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartGameController
{
    @FXML
    private Button goBackButton;

    @FXML
    private Button playButton;

    public Button getGoBackButton()
    {
        return goBackButton;
    }

    public Button getPlayButton()
    {
        return playButton;
    }
}
