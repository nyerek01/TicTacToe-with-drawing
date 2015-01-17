package example;

public class Game {

    static Player human;//XML osztalyban kap erteket, az adatok betoltese utan
    static Player comp;
    static Board b;
    static GUI g;
    static XML x;

    public static void main(String[] args) {
        x = new XML();
        x.loadXML("./src/xml/players.xml");//Mesteskor ezt a file-t kene modositani, nem pedig egy ujat letrehozni
        b = new Board();
        g = new GUI();
    }
}
