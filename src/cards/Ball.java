package cards;

import javafx.scene.image.ImageView;

public class Ball {

    //  declare cards in this suit
    private Card one;
    private Card two;
    private Card three;
    private Card four;
    private Card five;
    private Card seven;
    private Card eight;
    private Card ten;
    private Card eleven;
    private Card twelve;
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

    public Card getFour() {
        return four;
    }

    public void setFour(Card four) {
        this.four = four;
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

    public Card getEight() {
        return eight;
    }

    public void setEight(Card eight) {
        this.eight = eight;
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

    public Card getTwelve() {
        return twelve;
    }

    public void setTwelve(Card twelve) {
        this.twelve = twelve;
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

    public Ball() {

        // define each card in this suit
        one = new Card("ball", 1, new ImageView("cards/ball1.png"));
        two = new Card("ball", 2, new ImageView("cards/ball2.png"));
        three = new Card("ball", 3, new ImageView("cards/ball3.png"));
        four = new Card("ball", 4, new ImageView("cards/ball4.png"));
        five = new Card("ball", 5, new ImageView("cards/ball5.png"));
        seven = new Card("ball", 7, new ImageView("cards/ball7.png"));
        eight = new Card("ball", 8, new ImageView("cards/ball8.png"));
        ten = new Card("ball", 10, new ImageView("cards/ball10.png"));
        eleven = new Card("ball", 11, new ImageView("cards/ball11.png"));
        twelve = new Card("ball", 12, new ImageView("cards/ball12.png"));
        thirteen = new Card("ball", 13, new ImageView("cards/ball13.png"));
        fourteen = new Card("ball", 14, new ImageView("cards/ball14.png"));

    }  //  end of constructor

}  //  end of class
