import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class LoadGameController
{
    @FXML
    private Button goBackButton;

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
    private Text file1Location;
    @FXML
    private Text file2Location;
    @FXML
    private Text file3Location;
    @FXML
    private Text file4Location;
    @FXML
    private Text file5Location;
    @FXML
    private Text file6Location;

    @FXML
    private Text file1GameMode;
    @FXML
    private Text file2GameMode;
    @FXML
    private Text file3GameMode;
    @FXML
    private Text file4GameMode;
    @FXML
    private Text file5GameMode;
    @FXML
    private Text file6GameMode;

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

    public ArrayList<Text> getAllLocationTexts()
    {
        ArrayList<Text> locationTexts = new ArrayList<Text>();
        locationTexts.add(file1Location);
        locationTexts.add(file2Location);
        locationTexts.add(file3Location);
        locationTexts.add(file4Location);
        locationTexts.add(file5Location);
        locationTexts.add(file6Location);

        return locationTexts;
    }

    public ArrayList<Text> getAllGameModeTexts()
    {
        ArrayList<Text> gameModeTexts = new ArrayList<Text>();
        gameModeTexts.add(file1GameMode);
        gameModeTexts.add(file2GameMode);
        gameModeTexts.add(file3GameMode);
        gameModeTexts.add(file4GameMode);
        gameModeTexts.add(file5GameMode);
        gameModeTexts.add(file6GameMode);

        return gameModeTexts;
    }

    public Button getGoBackButton() {
        return goBackButton;
    }

//    public Button getLoadFile1Button() {
//        return loadFile1Button;
//    }
//    public Button getLoadFile2Button() {
//        return loadFile2Button;
//    }
//    public Button getLoadFile3Button() {
//        return loadFile3Button;
//    }
//    public Button getLoadFile4Button() {
//        return loadFile4Button;
//    }
//    public Button getLoadFile5Button() {
//        return loadFile5Button;
//    }
//    public Button getLoadFile6Button() {
//        return loadFile6Button;
//    }
}
