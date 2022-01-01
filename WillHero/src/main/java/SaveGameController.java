import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.util.ArrayList;

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

    public ArrayList<Button> getAllSaveButtons()
    {
        ArrayList<Button> saveButtons = new ArrayList<Button>();
        saveButtons.add(saveFile1Button);
        saveButtons.add(saveFile2Button);
        saveButtons.add(saveFile3Button);
        saveButtons.add(saveFile4Button);
        saveButtons.add(saveFile5Button);
        saveButtons.add(saveFile6Button);

        return saveButtons;
    }

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
