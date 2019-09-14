package cards;

import javafx.scene.image.ImageView;

public class Whot {

    //  declare cards in this suit
    private Card[] whot;

    public Card[] getWhot() {
        return whot;
    }

    public void setWhot(Card[] whot) {
        this.whot = whot;
    }

    public Whot() {

        // define each card in this suit
        whot = new Card[5];

        for (int i = 0; i < whot.length; i++) {
            whot[i] = new Card("whot", 20, new ImageView("cards/whot.png"));
        }

    }  //  end of constructor
}
