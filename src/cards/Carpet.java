package cards;

import javafx.scene.image.ImageView;

public class Carpet {

    //  declare cards in this suit
    private Card one;
    private Card two;
    private Card three;
    private Card five;
    private Card seven;
    private Card ten;
    private Card eleven;
    private Card thirteen;
    private Card fourteen;

    public Card getOne() {
        return one;
    }

    public void setOne(Card one) {
        this.one = one;
    }

    public Card getTwo() {
        return two;
    }

    public void setTwo(Card two) {
        this.two = two;
    }

    public Card getThree() {
        return three;
    }

    public void setThree(Card three) {
        this.three = three;
    }

    public Card getFive() {
        return five;
    }

    public void setFive(Card five) {
        this.five = five;
    }

    public Card getSeven() {
        return seven;
    }

    public void setSeven(Card seven) {
        this.seven = seven;
    }

    public Card getTen() {
        return ten;
    }

    public void setTen(Card ten) {
        this.ten = ten;
    }

    public Card getEleven() {
        return eleven;
    }

    public void setEleven(Card eleven) {
        this.eleven = eleven;
    }

    public Card getThirteen() {
        return thirteen;
    }

    public void setThirteen(Card thirteen) {
        this.thirteen = thirteen;
    }

    public Card getFourteen() {
        return fourteen;
    }

    public void setFourteen(Card fourteen) {
        this.fourteen = fourteen;
    }

    public Carpet() {

        // define each card in this suit
        one = new Card("carpet", 1, new ImageView("cards/carpet1.png"));
        two = new Card("carpet", 2, new ImageView("cards/carpet2.png"));
        three = new Card("carpet", 3, new ImageView("cards/carpet3.png"));
        five = new Card("carpet", 5, new ImageView("cards/carpet5.png"));
        seven = new Card("carpet", 7, new ImageView("cards/carpet7.png"));
        ten = new Card("carpet", 10, new ImageView("cards/carpet10.png"));
        eleven = new Card("carpet", 11, new ImageView("cards/carpet11.png"));
        thirteen = new Card("carpet", 13, new ImageView("cards/carpet13.png"));
        fourteen = new Card("carpet", 14, new ImageView("cards/carpet14.png"));

    }  //  end of constructor

}  //  end of class
