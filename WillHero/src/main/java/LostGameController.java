import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class LostGameController
{
    @FXML
    private Text locationReachedText;

    @FXML
    private Button playAgainButton;

    @FXML
    private Button mainMenuButton;

    public void setLocationReachedText(int locationReached)
    {
        Integer location = locationReached;
        locationReachedText.setText(location.toString());
    }

    public Button getPlayAgainButton() {
        return playAgainButton;
    }

    public Button getMainMenuButton() {
        return mainMenuButton;
    }
}
