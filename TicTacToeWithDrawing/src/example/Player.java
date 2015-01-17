package example;

import java.awt.*;
import java.util.*;

public class Player {

    private static long ID;//Globalis, osztalyszintu valtozo

    private String name;
    private String text;
    private String level;
    private String message;
    private Date regDate;
    private short points;
    private short time;
    private short numberGames;
    private short numberWins;
    private short numberLoses;
    private short numberTie;
    private Simbols simbol;
    private Color simbolColor;
    private Color textColor;
    private ArrayList<String> steps;

    Player(String n, String l, short p, short g, short w, short lo, short t, short ti, String st, Simbols s) {
        //Az XML file-bol kapott adatokkal hivjuk meg a konstruktort, azert adunk meg mindent mert akkor toltjuk be az allapotat
        time = 600;//Alapertelmezetten 10 perc van a jatekra
        name = n;
        level = l;
        points = p;
        numberGames = g;
        numberWins = w;
        numberLoses = lo;
        numberTie = t;
        time = ti;
        steps = new ArrayList<>();
        simbol = s;
        ID++;
    }

    public void print() {
        System.out.println("name: " + name + ", level " + level + ", points " + points + ", GameNum " + numberGames + ", WinNum" + numberWins + ", LoseNum" + numberLoses + ", TieNum" + numberTie);
    }

    void decreaseTime() {
        time--;
    }

    void increasePoints() {
        points++;
    }

    void increaseNumberOfGames() {
        numberGames++;
    }

    void increaseNumberOfWins() {
        numberWins++;
    }

    void increaseNumberOfLoses() {
        numberLoses++;
    }

    void increaseNumberOfTies() {
        numberTie++;
    }

    void increasePoints(byte p) {
        points += p;
    }

    long getID() {
        return ID;
    }

    String getName() {
        return name;
    }

    void setName(String n) {
        name = n;
    }

    String getText() {
        return text;
    }

    void setText(String t) {
        text = t;
    }

    String getLevel() {
        return level;
    }

    void setLevel(String l) {
        level = l;
    }

    String getMessage() {
        return message;
    }

    void setMessage(String m) {
        message = m;
    }

    int getPoints() {
        return points;
    }

    void setPoints(short p) {
        points = p;
    }

    short getTime() {
        return time;
    }

    void setTime(short t) {
        time = t;
    }

    Simbols getSimbol() {
        return simbol;
    }

    void setSimbol(Simbols s) {
        simbol = s;
    }

    Color getSimbolColor() {
        return simbolColor;
    }

    void setSimbolColor(Color s) {
        simbolColor = s;
    }

    Color getTextColor() {
        return textColor;
    }

    void setTextColor(Color c) {
        textColor = c;
    }

    ArrayList<String> getSteps() {
        return steps;
    }

    void setSteps(ArrayList<String> s) {
        steps = s;
    }

    short getNumberGames() {
        return numberGames;
    }

    void setNumberGames(short nG) {
        numberGames = nG;
    }

    short getNumberWins() {
        return numberWins;
    }

    void setNumberWins(short nW) {
        numberWins = nW;
    }

    short getNumberLoses() {
        return numberLoses;
    }

    void setNumberLoses(short nL) {
        numberLoses = nL;
    }

    short getNumberTie() {
        return numberTie;
    }

    void setNumberTie(short nT) {
        numberTie = nT;
    }

    Date getRegDate() {
        return regDate;
    }

    void setRegDate(Date rD) {
        regDate = rD;
    }

}
