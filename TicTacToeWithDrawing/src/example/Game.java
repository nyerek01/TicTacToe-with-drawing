package example;

public class Game {

    static Player human;
    static Player comp;
    static Board b;
    static GUI g;

    public static void main(String[] args) {
        human = new Player("Human", Simbols.X);
        comp = new Player("Computer", Simbols.O);
        b = new Board();
        g = new GUI();
    }
}
