package game;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class Home extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    //  declare window
    static Stage window;
    Scene homeScene;
    static BorderPane windowContent;

    //  declare north content
    static HBox northContent;
    static Button homeButton;
    static Button newGameButton;
    static Button helpButton;
    static Button aboutButton;

    //  declare centre content
    VBox centreContent;
    Label gameNumberLabel;
    static TextField gameNumberInput;
    Label gameModeLabel;
    static ChoiceBox<String> gameModeSelector;
    static String[] options = {"Single player mode", "Tournament"};
    Button proceedButton;

    // declare other variables
    static int gameNumber;
    static int roundNumber;
    static int userWinNumber;
    static int computerWinNumber;
    Alert errorMessageAlert;
    Alert messageAlert;
    static Stage previousWindow;

    @Override
    public void start(Stage primaryStage) {

        //  initialise window
        window = primaryStage;
        windowContent = new BorderPane();

        //  initialise north content
        northContent = new HBox(5);
        northContent.setId("bar");
        homeButton = new Button("Home");
        newGameButton = new Button("New Game");
        helpButton = new Button("Help");
        aboutButton = new Button("About");
        northContent.getChildren().addAll(homeButton, newGameButton, helpButton, aboutButton);
        windowContent.setTop(northContent);

        //  initialise centre content
        centreContent = new VBox(10);
        centreContent.setAlignment(Pos.CENTER);
        gameNumberLabel = new Label("Enter number of games:");
        gameNumberInput = new TextField();
        gameModeLabel = new Label("Select game mode:");
        gameModeSelector = new ChoiceBox();
        gameModeSelector.getItems().addAll(options);
        gameModeSelector.setValue(options[0]);
        proceedButton = new Button("Proceed");
        centreContent.getChildren().addAll(gameNumberLabel, gameNumberInput,
                                            gameModeLabel, gameModeSelector, proceedButton);
        windowContent.setCenter(centreContent);

        //  add widgets to window
        homeScene = new Scene(windowContent, 640, 480);
        window.setScene(homeScene);
        window.setTitle("Whot");
        window.setMaximized(true);
        window.getIcons().add(new Image(getClass().getResource("/cards/whot_icon.png").toString()));
        window.show();

        // initialise error message
        errorMessageAlert = new Alert(Alert.AlertType.ERROR);
        errorMessageAlert.setTitle("INVALID INPUT");
        errorMessageAlert.setHeaderText("");
        errorMessageAlert.setContentText("Please enter a valid integer");

        // initialise message alert
        messageAlert = new Alert(Alert.AlertType.INFORMATION);
        messageAlert.setTitle("PLEASE READ");
        messageAlert.setHeaderText("");
        messageAlert.setContentText("This mode is not yet available");

        //  set actions
        proceedButton.setOnAction(e -> {
            if (gameModeSelector.getValue().equals(options[0])) {
                try {
                    gameNumber = Integer.parseInt(gameNumberInput.getText());
                    roundNumber = 0;
                    userWinNumber = 0;
                    computerWinNumber = 0;
                    new SinglePlayerMode();
                    Home.window.hide();
                } catch (NumberFormatException e1) {
                    errorMessageAlert.showAndWait();
                }
            }

            else if (gameModeSelector.getValue().equals(options[1])) {
                messageAlert.showAndWait();
            }
        });

        helpButton.setOnAction(e -> {
            goToHelp(Home.window);
        });

        aboutButton.setOnAction(e -> {
            goToAbout(Home.window);
        });

        //  set stylesheet
        homeScene.getStylesheets().add("game/style.css");

    }  //  end of start



    public static void goHome(Stage window) {
        Home.gameNumberInput.setText("");
        Home.gameModeSelector.setValue(options[0]);
        Home.windowContent.setTop(Home.northContent);
        Home.window.show();
        window.hide();
    }  //  end of goHome()

    public static void goToHelp(Stage window) {
        new Help();
        previousWindow = window;
        window.hide();
    }  //  end of goToHelp()

    public static void goToAbout(Stage window) {
        new About();
        previousWindow = window;
        window.hide();
    }  //  end of goToHelp()

}  //  end of class
