import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ResurrectHeroController
{
    @FXML
    private Button resurrectYesButton;

    @FXML
    private Button resurrectNoButton;

    public Button getResurrectYesButton() {
        return resurrectYesButton;
    }

    public Button getResurrectNoButton() {
        return resurrectNoButton;
    }
}
