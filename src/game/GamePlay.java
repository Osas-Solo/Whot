package game;

import cards.Card;
import cards.Market;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.util.ArrayList;

public class GamePlay {

    static Card playUserCard(Card card, Card playedCard, ImageView playedCardPicture,
                             TilePane userArea, ArrayList<Card> userCards, Market playedCardsPile,
                             ImageView marketPicture, Label userLabel, Label computerLabel, Label nextMove) {

        System.out.println("Played card info:");
        System.out.println(playedCard.getSuit() + " " + playedCard.getNumber());

        Card chosenCard = null;

        if (!playedCard.isSpecial()) {
            nextMove.setText("");
        }

        //  check if card can be played
        if (card.getSuit().equals(playedCard.getSuit()) || card.getNumber() == playedCard.getNumber()
                ||  card.getSuit().equals("whot")) {
            chosenCard = card;
            playedCardPicture.setImage(chosenCard.getPicture().getImage());
            userArea.getChildren().remove(userCards.get(userCards.indexOf(card)).getPicture());
            userCards.remove(card);

            if (!nextMove.getText().equals("")) {
                nextMove.setText("");
            }

            if (!playedCardsPile.containsCard(chosenCard)) {
                playedCardsPile.add(chosenCard);
            }

            if (checkWinner(userCards) == 1) {
                Home.userWinNumber++;
                displayWinner("You", marketPicture, userCards);
            }  //  end of if to check winner

            else {

                if (!card.isSpecial()) {
                    userLabel.setText("");
                    computerLabel.setText("Computer's Turn");
                } else if (card.getNumber() == 1) {
                    holdOn(userLabel, computerLabel, nextMove);
                } else if (card.getNumber() == 2) {
                    userLabel.setText("");
                    computerLabel.setText("Computer's Turn");
                    nextMove.setText("Pick Two");
                } else if (card.getNumber() == 5) {
                    userLabel.setText("");
                    computerLabel.setText("Computer's Turn");
                    nextMove.setText("Pick Three");
                } else if (card.getNumber() == 8) {
                    suspend(userLabel, computerLabel, nextMove);
                } else if (card.getNumber() == 14) {
                    userLabel.setText("");
                    computerLabel.setText("Computer's Turn");
                    nextMove.setText("General Market");
                } else if (card.getSuit().equals("whot")) {
                    Card chosenCardCopy = new Card(chosenCard);
                    chosenCardCopy.setSuit(nameSuit(nextMove));
                    computerLabel.setText("Computer's Turn");
                    return chosenCardCopy;
                }

                System.out.println("Your info:");
                System.out.println(chosenCard.getSuit() + " " + chosenCard.getNumber());

            }  //  end of else

        }  //  end of if card can be played

        if (chosenCard != null) {
            return chosenCard;
        } else {
            return playedCard;
        }

    }  //  end of playUserCard()

    public static Card playComputerCard(Card playedCard, ImageView playedCardPicture, HBox computerArea,
                                        Market market, ArrayList<Card> computerCards, ArrayList<Card> userCards,
                                        ArrayList<ImageView> computerCardPictures, Market playedCardsPile,
                                        ImageView marketPicture, Label userLabel, Label computerLabel, Label nextMove) {

        System.out.println("Played card info:");
        System.out.println(playedCard.getSuit() + " " + playedCard.getNumber());

        final Card[] chosenCard = {null};

        if (!playedCard.isSpecial()) {
            nextMove.setText("");
        }

        //  check if played card is special
        switch (nextMove.getText()) {
            case "Pick Two":
                goToMarket(market, playedCardsPile, computerArea, 2,
                        computerCards, computerCardPictures, userLabel, computerLabel);
                nextMove.setText("");
                chosenCard[0] = playedCard;
                break;
            case "Pick Three":
                goToMarket(market, playedCardsPile, computerArea, 3,
                        computerCards, computerCardPictures, userLabel, computerLabel);
                nextMove.setText("");
                chosenCard[0] = playedCard;
                break;
            case "General Market":
                goToMarket(market, playedCardsPile, computerArea, 1,
                        computerCards, computerCardPictures, userLabel, computerLabel);
                nextMove.setText("");
                chosenCard[0] = playedCard;
                break;
            default:     //  choose card to play

                for (Card card : computerCards) {
                    if (card.getSuit().equals(playedCard.getSuit()) || card.getNumber() == playedCard.getNumber()
                            || card.getSuit().equals("whot")) {
                        chosenCard[0] = card;
                        break;
                    }
                }  //  end of for to choose card to play

                if (chosenCard[0] != null) {

                    System.out.println("Computer info:");
                    System.out.println(chosenCard[0].getSuit() + " " + chosenCard[0].getNumber());

                    //  check for better card to play
                    for (Card card : computerCards) {
                        if (card.getSuit().equals(playedCard.getSuit()) && card.getNumber() > playedCard.getNumber()) {
                            chosenCard[0] = card;
                        }
                    }  //  end of for to choose better card to play

                    System.out.println("Computer info:");
                    System.out.println(chosenCard[0].getSuit() + " " + chosenCard[0].getNumber());

                    //  check to see if user is about to check up
                    if (userCards.size() <= 3) {
                        //  find special cards
                        for (Card card : computerCards) {
                            if (card.getSuit().equals(chosenCard[0].getSuit()) || card.getNumber() == chosenCard[0].getNumber()) {
                                if (!nextMove.getText().contains("Play")) {
                                    if (card.isSpecial()) {
                                        chosenCard[0] = card;
                                    }
                                }
                            }
                        }  //  end of for to choose better card to play
                    }  //  end of if user's card are <= 3

                    System.out.println("Computer info:");
                    System.out.println(chosenCard[0].getSuit() + " " + chosenCard[0].getNumber());

                    playedCardPicture.setImage(chosenCard[0].getPicture().getImage());
                    computerArea.getChildren().remove(computerCardPictures.get(computerCardPictures.size() - 1));
                    computerCardPictures.remove(computerCardPictures.get(computerCardPictures.size() - 1));
                    computerCards.remove(chosenCard[0]);

                    if (!playedCardsPile.containsCard(chosenCard[0])) {
                        playedCardsPile.add(chosenCard[0]);
                    }

                    if (checkWinner(computerCards) == 1) {
                        Home.computerWinNumber++;
                        displayWinner("Computer", marketPicture, userCards);
                    }  //  end of if to check winner

                    else {  //  continue when winner is not found

                        if (!chosenCard[0].isSpecial()) {
                            userLabel.setText("Your Turn");
                            computerLabel.setText("");
                            nextMove.setText("");
                        } else if (chosenCard[0].getNumber() == 1) {
                            holdOn(userLabel, computerLabel, nextMove);
                            Timeline timer = new Timeline(new KeyFrame(Duration.seconds(3), event -> chosenCard[0] =
                                    new Card(GamePlay.playComputerCard(chosenCard[0], playedCardPicture, computerArea, market,
                                            computerCards, userCards, computerCardPictures, playedCardsPile,
                                            marketPicture, userLabel, computerLabel, nextMove))));
                            timer.play();
                            return chosenCard[0];
                        } else if (chosenCard[0].getNumber() == 2) {
                            pickTwo(userCards, userLabel, computerLabel, nextMove);
                        } else if (chosenCard[0].getNumber() == 5) {
                            pickThree(userCards, userLabel, computerLabel, nextMove);
                        } else if (chosenCard[0].getNumber() == 8) {
                            suspend(userLabel, computerLabel, nextMove);
                            Timeline timer = new Timeline(new KeyFrame(Duration.seconds(3), event -> chosenCard[0] =
                                    new Card(GamePlay.playComputerCard(chosenCard[0], playedCardPicture, computerArea, market,
                                            computerCards, userCards, computerCardPictures, playedCardsPile,
                                            marketPicture, userLabel, computerLabel, nextMove))));
                            timer.play();
                            return chosenCard[0];
                        } else if (chosenCard[0].getNumber() == 14) {
                            userLabel.setText("Your Turn");
                            computerLabel.setText("");
                            nextMove.setText("General Market");
                            removeUserCardsEvent(userCards);
                        } else if (chosenCard[0].getSuit().equals("whot")) {
                            Card chosenCardCopy = new Card(chosenCard[0]);
                            chosenCardCopy.setSuit(nameComputerSuit(computerCards, nextMove));
                            userLabel.setText("Your Turn");
                            computerLabel.setText("");
                            return chosenCardCopy;
                        }

                    }  //  end of else if winner is not found

                } else {
                    goToMarket(market, playedCardsPile, computerArea, 1,
                            computerCards, computerCardPictures, userLabel, computerLabel);
                    if (!nextMove.getText().contains("Play")) {
                        nextMove.setText("");
                    }
                    chosenCard[0] = new Card(playedCard);
                    return chosenCard[0];
                }

        }  //  end of switch()

        return chosenCard[0];

    }  //  end of playComputerCard()


    private static void holdOn(Label userLabel, Label computerLabel, Label nextMove) {

        nextMove.setText("Hold On");

        if (!userLabel.getText().isEmpty()) {
            userLabel.setText("Your Turn");
            computerLabel.setText("");
        } else if (!computerLabel.getText().isEmpty()) {
            userLabel.setText("");
            computerLabel.setText("Computer's Turn");
        }

    }  //  end of holdOn()

    private static void pickTwo(ArrayList<Card> userCards, Label userLabel, Label computerLabel, Label nextMove) {
        userLabel.setText("Your Turn");
        computerLabel.setText("");
        nextMove.setText("Pick Two");
        removeUserCardsEvent(userCards);
    }  //  end of pickTwo()

    private static void pickThree(ArrayList<Card> userCards, Label userLabel, Label computerLabel, Label nextMove) {
        userLabel.setText("Your Turn");
        computerLabel.setText("");
        nextMove.setText("Pick Three");
        removeUserCardsEvent(userCards);
    }  //  end of pickThree()

    private static void suspend(Label userLabel, Label computerLabel, Label nextMove) {

        nextMove.setText("Suspension");

        if (!userLabel.getText().isEmpty()) {
            userLabel.setText("Your Turn");
            computerLabel.setText("");
        } else if (!computerLabel.getText().isEmpty()) {
            userLabel.setText("");
            computerLabel.setText("Computer's Turn");
        }

    }  //  end of holdOn()

    static void goToMarket(Market market, Market playedCardsPile, Pane userArea, int numberOfTrips,
                           ArrayList<Card> userCards, Label userLabel, Label computerLabel, Label nextMove) {

        for (int i = 1; i <= numberOfTrips; i++) {
            userCards.add((Card) market.pop());
            userArea.getChildren().add(userCards.get(userCards.size() - 1).getPicture());

            if (market.size() < 4) {
                market.shuffle(market, playedCardsPile);
            }
        }

        userLabel.setText("");
        computerLabel.setText("Computer's Turn");

    }  //  end of goToMarket()

    //  goToMarket() for computer
    private static void goToMarket(Market market, Market playedCardsPile, Pane computerArea, int numberOfTrips,
                                   ArrayList<Card> computerCards, ArrayList<ImageView> computerCardPictures, Label userLabel, Label computerLabel) {

        for (int i = 1; i <= numberOfTrips; i++) {
            computerCards.add((Card) market.pop());
            computerCardPictures.add(new ImageView("cards/whot_card.png"));
            computerCardPictures.get(computerCardPictures.size() - 1).setFitWidth(100);
            computerCardPictures.get(computerCardPictures.size() - 1).setFitHeight(150);
            computerArea.getChildren().add(computerCardPictures.get(computerCardPictures.size() - 1));

            if (market.size() < 4) {
                market.shuffle(market, playedCardsPile);
            }
        }

        computerLabel.setText("");
        userLabel.setText("Your Turn");

    }  //  end of goToMarket() for computer

    private static String nameSuit(Label nextMove) {

        ChoiceDialog<String> choiceOfSuit = new ChoiceDialog("", "Ball", "Angle", "Cross", "Carpet", "Star");
        choiceOfSuit.setTitle("Choose suit");
        choiceOfSuit.setHeaderText("");

        choiceOfSuit.showAndWait();

        if (choiceOfSuit.getSelectedItem().equals("")) {
            nameSuit(nextMove);
        }

        nextMove.setText("Play " + choiceOfSuit.getSelectedItem().toLowerCase());

        return choiceOfSuit.getSelectedItem().toLowerCase();

    }  //  end of nameSuit()

    private static String nameComputerSuit(ArrayList<Card> computerCards, Label nextMove) {  //  for computer

        String chosenSuit;
        String[] suits = {"Ball", "Angle", "Cross", "Carpet", "Star"};
        int[] suitNumbers = {0, 0, 0, 0, 0};

        //  find best suit to choose
        for (Card card: computerCards) {
            switch (card.getSuit()) {
                case "ball":
                    suitNumbers[0]++;
                    break;
                case "angle":
                    suitNumbers[1]++;
                    break;
                case "cross":
                    suitNumbers[2]++;
                    break;
                case "carpet":
                    suitNumbers[3]++;
                    break;
                case "star":
                    suitNumbers[4]++;
                    break;
            }  //  end of switch
        }  //  end of for to find best suit

        //  finalise best suit to choose
        chosenSuit = suits[0].toLowerCase();
        for (int i = 1; i < suitNumbers.length; i++) {
            if (suitNumbers[i] > suitNumbers[i - 1]) {
                chosenSuit = suits[i].toLowerCase();
            }
        }  //  end of for to finalise best suit

        nextMove.setText("Play " + chosenSuit.toLowerCase());

        return chosenSuit;

    } //  end of nameComputerSuit()

    private static int checkWinner(ArrayList<Card> cards) {

        if (cards.size() == 0) {
            return 1;
        }

        return 0;

    }  //  end of checkWinner()

    private static void displayWinner(String winner, ImageView marketPicture, ArrayList<Card> userCards) {

        Alert winnerAlert = new Alert(Alert.AlertType.INFORMATION);
        winnerAlert.setTitle("Winner");
        winnerAlert.setHeaderText("");
        winnerAlert.setContentText(winner + " Won");
        winnerAlert.show();

        marketPicture.setOnMouseClicked(e -> {
                //  do nothing
        });

        removeUserCardsEvent(userCards);

        SinglePlayerMode.userScoreLabel.setText("User " + Home.userWinNumber);
        SinglePlayerMode.computerScoreLabel.setText(" - " + Home.computerWinNumber + " Computer");

        if ( Home.roundNumber >= Home.gameNumber &&
                ((Home.userWinNumber > Home.computerWinNumber) || (Home.computerWinNumber > Home.userWinNumber)) ) {
            SinglePlayerMode.nextRoundButton.setDisable(true);
        } else {
            SinglePlayerMode.nextRoundButton.setDisable(false);
        }

    }  //  end of displayWinner()

    private static void removeUserCardsEvent(ArrayList<Card> userCards) {

        for (Card card: userCards) {
            card.getPicture().setOnMouseClicked(e -> {

            });
        }

    }  //  end of removeUserCardsEvent()

}

