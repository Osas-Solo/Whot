package game;

import cards.Card;
import cards.Market;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class SinglePlayerMode {

    //  declare window
    static Stage window;
    Scene scene;
    BorderPane windowContent;

    //  declare north content
    VBox northContent;
    static Label computerLabel;
    static HBox computerArea;
    static ArrayList<ImageView> computerCardPictures;
    Label roundNumberLabel;
    HBox scoreArea;
    static Label userScoreLabel;
    static Label computerScoreLabel;

    //  declare centre content
    HBox centreContent;
    static ImageView marketPicture;
    static ImageView playedCardPicture;
    static Label nextMove;
    static Button nextRoundButton;

    //  declare south content
    VBox southContent;
    static Label userLabel;
    static TilePane userArea;
    ScrollPane scroller;

    //  declare cards at hand for user and computer
    static ArrayList<Card> computerCards;
    static ArrayList<Card> userCards;

    //  declare other game components
    static Market market;
    static Card playedCard;
    static Market playedCardsPile;

    //  declare other variables
    Alert waitMessageAlert;
    int starterNumber;

    public SinglePlayerMode() {

        //  initialise window
        window = new Stage();
        windowContent = new BorderPane();

        //  initialise top content
        northContent = new VBox(5);
        computerArea = new HBox(10);
        computerCardPictures = new ArrayList();
        computerLabel = new Label();
        scoreArea = new HBox();
        scoreArea.setAlignment(Pos.CENTER);
        roundNumberLabel = new Label();
        userScoreLabel = new Label();
        computerScoreLabel = new Label();
        scoreArea.getChildren().addAll(roundNumberLabel, userScoreLabel, computerScoreLabel);
        northContent.getChildren().addAll(Home.northContent, computerArea, computerLabel, scoreArea);
        windowContent.setTop(northContent);

        //  initialise centre content
        centreContent = new HBox(100);
        centreContent.setAlignment(Pos.CENTER);
        marketPicture = new ImageView("cards/whot_card.png");
        marketPicture.setFitWidth(100);
        marketPicture.setFitHeight(150);
        centreContent.getChildren().add(marketPicture);
        windowContent.setCenter(centreContent);

        //  initialise south content
        southContent = new VBox();
        userArea = new TilePane();
        userArea.setHgap(10);
        userArea.setVgap(10);
        userLabel = new Label();
        southContent.getChildren().addAll(userLabel, userArea);
        scroller = new ScrollPane();
        scroller.setContent(southContent);
        scroller.setFitToHeight(true);
        scroller.setFitToWidth(true);
        scroller.setPrefHeight(200);
        windowContent.setBottom(scroller);

        //  initialise cards at hand for user and computer
        computerCards = new ArrayList();
        userCards = new ArrayList();

        //  initialise other game components
        market = new Market();
        playedCardsPile = new Market();
        playedCardsPile.removeAllElements();
        market.shuffle();
        for (int i = 0; i < 5; i++) {
            computerCards.add((Card)market.pop());
            computerCardPictures.add(new ImageView("cards/whot_card.png"));
            computerCardPictures.get(i).setFitWidth(100);
            computerCardPictures.get(i).setFitHeight(150);
            computerArea.getChildren().add(computerCardPictures.get(i));
            userCards.add((Card)market.pop());
            userArea.getChildren().add(userCards.get(i).getPicture());
        }
        playedCard = new Card((Card)market.pop());
        playedCardsPile.add(playedCard);
        System.out.println(playedCard.isSpecial());
        while (playedCard.isSpecial()) {
            System.out.println(playedCard.isSpecial());
            playedCardsPile.add(playedCard);
            playedCard = new Card((Card)market.pop());
        }
        playedCardPicture = playedCard.getPicture();
        nextMove = new Label();
        nextRoundButton = new Button("Next Round");
        nextRoundButton.setDisable(true);
        centreContent.getChildren().addAll(playedCardPicture, nextMove, nextRoundButton);

        //  choose starter
        Random random = new Random();
        starterNumber = 1 + random.nextInt(2);
        System.out.println("Starter: " + starterNumber);
        if (starterNumber == 1) {  // user starts
            userLabel.setText("Your Turn");
        }  else if (starterNumber == 2) {
            computerLabel.setText("Computer's Turn");
            Timeline timer = new Timeline(new KeyFrame(Duration.seconds(3), event -> playedCard = new Card(GamePlay.playComputerCard(playedCard, playedCardPicture, computerArea, market,
                    computerCards, userCards, computerCardPictures, playedCardsPile,
                    marketPicture, userLabel, computerLabel, nextMove))));
            timer.play();
        }

        //  add widgets to window
        scene = new Scene(windowContent, 640, 480);
        window.setScene(scene);
        window.setTitle("Whot - Single Player Mode");
        window.setMaximized(true);
        window.getIcons().add(new Image(getClass().getResource("/cards/whot_icon.png").toString()));
        window.show();

        //  initialise waitMessage
        waitMessageAlert = new Alert(Alert.AlertType.INFORMATION);
        waitMessageAlert.setTitle("PLEASE WAIT");
        waitMessageAlert.setHeaderText("");
        waitMessageAlert.setContentText("Please wait while a new game is being prepared");

        //  set stylesheet
        scene.getStylesheets().add("game/style.css");

        //  set actions
        marketPicture.setOnMouseClicked(e -> {
            if (userLabel.getText().equals("Your Turn")) {

                switch (nextMove.getText()) {
                    case "Pick Two":
                        GamePlay.goToMarket(market, playedCardsPile, userArea, 2,
                                userCards, userLabel, computerLabel, nextMove);
                        break;
                    case "Pick Three":
                        GamePlay.goToMarket(market, playedCardsPile, userArea, 3,
                                userCards, userLabel, computerLabel, nextMove);
                        break;
                    case "General Market":
                        GamePlay.goToMarket(market, playedCardsPile, userArea, 1,
                                userCards, userLabel, computerLabel, nextMove);
                        break;
                    default:
                        GamePlay.goToMarket(market, playedCardsPile, userArea, 1,
                                userCards, userLabel, computerLabel, nextMove);
                        break;
                }

                if (!nextMove.getText().contains("Play")) {
                    nextMove.setText("");
                }

                setUserCardsAction();

                if (computerLabel.getText().equals("Computer's Turn")) {
                    Timeline timer = new Timeline(new KeyFrame(Duration.seconds(3), event -> playedCard = new Card(GamePlay.playComputerCard(playedCard, playedCardPicture, computerArea, market,
                            computerCards, userCards, computerCardPictures, playedCardsPile,
                            marketPicture, userLabel, computerLabel, nextMove))));
                    timer.play();
                }
            }
        });

        setUserCardsAction();

        Home.homeButton.setOnAction(e -> {
            Home.goHome(SinglePlayerMode.window);
        });

        Home.helpButton.setOnAction(e -> {
            Home.goToHelp(SinglePlayerMode.window);
        });

        Home.aboutButton.setOnAction(e -> {
            Home.goToAbout(SinglePlayerMode.window);
        });

        Home.newGameButton.setOnAction(e -> {
                Home.gameNumber = 1;
                Home.roundNumber = 0;
                Home.userWinNumber = 0;
                Home.computerWinNumber = 0;
                waitMessageAlert.show();
                window.hide();
                new SinglePlayerMode();
                waitMessageAlert.hide();
        });

        Home.helpButton.setOnAction(e -> {
            Home.goToHelp(window);
        });

        nextRoundButton.setOnAction(e -> {
            waitMessageAlert.show();
            window.hide();
            new SinglePlayerMode();
            waitMessageAlert.hide();
        });

        //  set round and score numbers
        Home.roundNumber++;
        roundNumberLabel.setText("Round " + Home.roundNumber + "          ");
        userScoreLabel.setText("User " + Home.userWinNumber);
        computerScoreLabel.setText(" - " + Home.computerWinNumber + " Computer");

    }  //  end of start

    private static void setUserCardsAction() {
        for (Card card: userCards) {
            card.getPicture().setOnMouseClicked(e -> {
                if (userLabel.getText().equals("Your Turn")) {
                    playedCard = new Card(GamePlay.playUserCard(card, playedCard, playedCardPicture,
                            userArea, userCards, playedCardsPile,
                            marketPicture, userLabel, computerLabel, nextMove));

                    if (computerLabel.getText().equals("Computer's Turn")) {
                        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(3), event -> playedCard =
                                                                    new Card(GamePlay.playComputerCard(playedCard, playedCardPicture, computerArea, market,
                                                                    computerCards, userCards, computerCardPictures, playedCardsPile,
                                                                    marketPicture, userLabel, computerLabel, nextMove))));
                        timer.play();
                    }
                }
            });
        }
    }  //  end of setUserCardsAction

}  //  end of class
