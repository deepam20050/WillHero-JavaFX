import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class LoadGameController
{
    @FXML
    private Button loadFile1Button;
    @FXML
    private Button loadFile2Button;
    @FXML
    private Button loadFile3Button;
    @FXML
    private Button loadFile4Button;
    @FXML
    private Button loadFile5Button;
    @FXML
    private Button loadFile6Button;
    @FXML
    private Button goBackButton;

    public ArrayList<Button> getAllLoadButtons()
    {
        ArrayList<Button> loadButtons = new ArrayList<Button>();
        loadButtons.add(loadFile1Button);
        loadButtons.add(loadFile2Button);
        loadButtons.add(loadFile3Button);
        loadButtons.add(loadFile4Button);
        loadButtons.add(loadFile5Button);
        loadButtons.add(loadFile6Button);

        return loadButtons;
    }

    public Button getLoadFile1Button() {
        return loadFile1Button;
    }
    public Button getLoadFile2Button() {
        return loadFile2Button;
    }
    public Button getLoadFile3Button() {
        return loadFile3Button;
    }
    public Button getLoadFile4Button() {
        return loadFile4Button;
    }
    public Button getLoadFile5Button() {
        return loadFile5Button;
    }
    public Button getLoadFile6Button() {
        return loadFile6Button;
    }
    public Button getGoBackButton() {
        return goBackButton;
    }
}
