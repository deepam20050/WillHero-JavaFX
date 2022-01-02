import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

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

//    public Button getSaveFile1Button() {
//        return saveFile1Button;
//    }
//    public Button getSaveFile2Button() {
//        return saveFile2Button;
//    }
//    public Button getSaveFile3Button() {
//        return saveFile3Button;
//    }
//    public Button getSaveFile4Button() {
//        return saveFile4Button;
//    }
//    public Button getSaveFile5Button() {
//        return saveFile5Button;
//    }
//    public Button getSaveFile6Button() {
//        return saveFile6Button;
//    }
}
