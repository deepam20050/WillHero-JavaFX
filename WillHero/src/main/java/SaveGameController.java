import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SaveGameController
{
    @FXML
    private Button saveFile1Button;
    @FXML
    private Button saveFile2Button;
    @FXML
    private Button saveFile3Button;
    @FXML
    private Button saveFile4Button;
    @FXML
    private Button saveFile5Button;
    @FXML
    private Button saveFile6Button;
    @FXML
    private Button goBackButton;

    public Button getSaveFile1Button() {
        return saveFile1Button;
    }
    public Button getSaveFile2Button() {
        return saveFile2Button;
    }
    public Button getSaveFile3Button() {
        return saveFile3Button;
    }
    public Button getSaveFile4Button() {
        return saveFile4Button;
    }
    public Button getSaveFile5Button() {
        return saveFile5Button;
    }
    public Button getSaveFile6Button() {
        return saveFile6Button;
    }
    public Button getGoBackButton() {
        return goBackButton;
    }
}
