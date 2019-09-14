package cards;

import javafx.scene.image.ImageView;

    public class Star {

    //  declare cards in this suit
    private Card one;
    private Card two;
    private Card three;
    private Card four;
    private Card five;
    private Card seven;
    private Card eight;

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

        public Star() {

        // define each card in this suit
        one = new Card("star", 1, new ImageView("cards/star1.png"));
        two = new Card("star", 2, new ImageView("cards/star2.png"));
        three = new Card("star", 3, new ImageView("cards/star3.png"));
        four = new Card("star", 4, new ImageView("cards/star4.png"));
        five = new Card("star", 5, new ImageView("cards/star5.png"));
        seven = new Card("star", 7, new ImageView("cards/star7.png"));
        eight = new Card("star", 8, new ImageView("cards/star8.png"));

    }  //  end of constructor

}  //  end of class
