package cards;

import javafx.scene.image.ImageView;

public class Card {


    //  declare fields
    private String suit;
    private int number;
    private ImageView picture;
    private boolean isSpecial;

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ImageView getPicture() {
        return picture;
    }

    public void setPicture(ImageView picture) {
        this.picture = picture;
    }

    public boolean isSpecial() {
        return isSpecial;
    }

    public void setSpecial(boolean special) {
        isSpecial = special;
    }

    //  define fields
    public Card(String suit, int number, ImageView picture) {

        this.suit = suit;
        this.number = number;
        this.picture = picture;

        if (this.number == 1 || this.number == 2 || this.number == 5 || this.number == 8 || this.number == 14
            || this.number == 20) {
            this.isSpecial = true;
        } else {
            isSpecial = false;
        }

    }  //  end of constructor

    //  copy constructor
    public Card(Card card) {

        this.suit = card.suit;
        this.number = card.number;
        this.picture = card.picture;
        this.isSpecial = card.isSpecial;

    }  //  end of copy constructor

}  //  end of class
