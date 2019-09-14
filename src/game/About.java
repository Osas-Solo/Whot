package game;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.layout.*;

import java.net.URL;

public class About {

    //  declare window
    static Stage window;
    Scene helpScene;
    static BorderPane windowContent;

    //  declare centre content
    VBox centreContent;
    WebView helpPage;

    //  declare bottom content
    Button backButton;

    public About() {

        //  initialise window
        window = new Stage();
        windowContent = new BorderPane();

        //  initialise centre content
        centreContent = new VBox(10);
        centreContent.setAlignment(Pos.CENTER);
        helpPage = new WebView();
        URL url = getClass().getResource("about.html");
        helpPage.getEngine().load(url.toExternalForm());
        centreContent.getChildren().add(helpPage);
        windowContent.setCenter(centreContent);

        //  initialise south content
        backButton = new Button("Back");
        backButton.setAlignment(Pos.CENTER);
        windowContent.setBottom(backButton);

        //  add widgets to window
        helpScene = new Scene(windowContent, 640, 480);
        window.setScene(helpScene);
        window.setTitle("Whot - About");
        window.setMaximized(true);
        window.getIcons().add(new Image(getClass().getResource("/cards/whot_icon.png").toString()));
        window.show();

        //  set stylesheet
        helpScene.getStylesheets().add("game/style.css");

        //  set actions
        backButton.setOnAction(e -> {
            goBack(Home.previousWindow);
        });

    }  //  end of constructor


    public static void goBack(Stage previousWindow) {

        previousWindow.show();
        window.hide();

    }  //  end of goBack()

}  //  end of class
