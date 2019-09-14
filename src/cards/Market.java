package cards;

import java.util.*;

public class Market<Card> extends Stack {

    //  create instances of card suits
    public Ball ball;
    public Angle angle;
    public Cross cross;
    public Carpet carpet;
    public Star star;
    public Whot whot;

    public cards.Card[] cards;

    public Market() {

        // instantiate card suits
        ball = new Ball();
        angle = new Angle();
        cross = new Cross();
        carpet = new Carpet();
        star = new Star();
        whot = new Whot();

        cards = new cards.Card[54];

        //  add cards in ball suit to market
        cards[0] = ball.getOne();
        cards[1] = ball.getTwo();
        cards[2] = ball.getThree();
        cards[3] = ball.getFour();
        cards[4] = ball.getFive();
        cards[5] = ball.getSeven();
        cards[6] = ball.getEight();
        cards[7] = ball.getTen();
        cards[8] = ball.getEleven();
        cards[9] = ball.getTwelve();
        cards[10] = ball.getThirteen();
        cards[11] = ball.getFourteen();

        //  add cards in angle suit to market
        cards[12] = angle.getOne();
        cards[13] = angle.getTwo();
        cards[14] = angle.getThree();
        cards[15] = angle.getFour();
        cards[16] = angle.getFive();
        cards[17] = angle.getSeven();
        cards[18] = angle.getEight();
        cards[19] = angle.getTen();
        cards[20] = angle.getEleven();
        cards[21] = angle.getTwelve();
        cards[22] = angle.getThirteen();
        cards[23] = angle.getFourteen();

        //  add cards in cross suit to market
        cards[24] = cross.getOne();
        cards[25] = cross.getTwo();
        cards[26] = cross.getThree();
        cards[27] = cross.getFive();
        cards[28] = cross.getSeven();
        cards[29] = cross.getTen();
        cards[30] = cross.getEleven();
        cards[31] = cross.getThirteen();
        cards[32] = cross.getFourteen();

        //  add cards in carpet suit to market
        cards[33] = carpet.getOne();
        cards[34] = carpet.getTwo();
        cards[35] = carpet.getThree();
        cards[36] = carpet.getFive();
        cards[37] = carpet.getSeven();
        cards[38] = carpet.getTen();
        cards[39] = carpet.getEleven();
        cards[40] = carpet.getThirteen();
        cards[41] = carpet.getFourteen();

        //  add cards in star suit to market
        cards[42] = star.getOne();
        cards[43] = star.getTwo();
        cards[44] = star.getThree();
        cards[45] = star.getFour();
        cards[46] = star.getFive();
        cards[47] = star.getSeven();
        cards[48] = star.getEight();

        //  add cards in whot suit to market
        cards[49] = whot.getWhot()[0];
        cards[50] = whot.getWhot()[1];
        cards[51] = whot.getWhot()[2];
        cards[52] = whot.getWhot()[3];
        cards[53] = whot.getWhot()[4];

        //  add card to market
        for (int i = 0; i < cards.length; i++) {
            cards[i].getPicture().setFitWidth(100);
            cards[i].getPicture().setFitHeight(150);
            add(cards[i]);
        }

    }  //  end of constructor

    public void shuffle() {

        removeAllElements();
        Random random = new Random();

        for (int i = 0; i < cards.length; i++) {
            int randomNumber;
            boolean isInMarket = true;
            do {
                randomNumber = random.nextInt(54);
                if (search(cards[randomNumber]) == -1) {
                    isInMarket = false;
                }
            } while (isInMarket);
            System.out.println(randomNumber);
            add(cards[randomNumber]);
        }  //  end of for

    }  //  end of shuffle()

    public void shuffle(Market market, Market playedCardsPile) {

        // create temporary arraylist to store removed cards from market and playedCardsPile
        ArrayList<cards.Card> cards = new ArrayList();

        //  move every card from market to cards
        for (int i = 0; i < market.size(); i++) {
            cards.add((cards.Card)market.pop());
        }

        //  move every card from playedCardsPile to cards
        for (int i = 0; i < playedCardsPile.size(); i++) {
            cards.add((cards.Card)playedCardsPile.pop());
        }

        Random random = new Random();

        for (int i = 0; i < cards.size(); i++) {
            int randomNumber;
            boolean isInMarket = true;
            do {
                randomNumber = random.nextInt(cards.size());
                if (market.search(cards.get(randomNumber)) == -1) {
                    isInMarket = false;
                }
            } while (isInMarket);
            System.out.println(randomNumber);
            market.add(cards.get(randomNumber));
        }  //  end of for

    }  //  end of shuffle()

    public boolean containsCard(cards.Card card) {

        boolean isInCardPile = false;

        if (card.getSuit().equals("whot")) {  //  if card is whot

            //  create temporary ArrayList to store cards for counting
            ArrayList<cards.Card> cards = new ArrayList();

            //  add every card from market to cards
            for (int i = 0; i < size(); i++) {
                cards.add((cards.Card) get(i));
            }

            int whotCardCount = 0;
            for (int i = 0; i < cards.size(); i++) {
                if (cards.get(i).getSuit().equals("whot")) {
                    whotCardCount++;
                }
            }

            if (whotCardCount == 5) {
                isInCardPile = true;
            } else {
                isInCardPile = false;
            }

        } else if (!card.getSuit().equals("whot")) {  //  if card is not whot

            //  create temporary ArrayList to store cards for counting
            ArrayList<cards.Card> cards = new ArrayList();

            //  add every card from market to cards
            for (int i = 0; i < size(); i++) {
                cards.add((cards.Card) get(i));
            }

            for (int i = 0; i < cards.size(); i++) {
                if (cards.get(i).getSuit().equals(card.getSuit()) && cards.get(i).getNumber() == card.getNumber()) {
                    isInCardPile = true;
                    break;
                } else {
                    isInCardPile = false;
                }
            }

        }  //  end of if card is not whot

        return isInCardPile;

    }  //  end of containsCard()


}  //  end of class