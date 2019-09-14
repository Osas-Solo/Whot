package cards;

import javafx.scene.image.ImageView;

public class Angle {

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

    public Angle() {

        // define each card in this suit
        one = new Card("angle", 1, new ImageView("cards/angle1.png"));
        two = new Card("angle", 2, new ImageView("cards/angle2.png"));
        three = new Card("angle", 3, new ImageView("cards/angle3.png"));
        four = new Card("angle", 4, new ImageView("cards/angle4.png"));
        five = new Card("angle", 5, new ImageView("cards/angle5.png"));
        seven = new Card("angle", 7, new ImageView("cards/angle7.png"));
        eight = new Card("angle", 8, new ImageView("cards/angle8.png"));
        ten = new Card("angle", 10, new ImageView("cards/angle10.png"));
        eleven = new Card("angle", 11, new ImageView("cards/angle11.png"));
        twelve = new Card("angle", 12, new ImageView("cards/angle12.png"));
        thirteen = new Card("angle", 13, new ImageView("cards/angle13.png"));
        fourteen = new Card("angle", 14, new ImageView("cards/angle14.png"));

    }  //  end of constructor

}  //  end of class
