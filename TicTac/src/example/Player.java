package example;

import java.awt.*;
import java.util.*;

public class Player {

    private String name;
    private String text;
    private String message;
    private int points;
    private short time;
    private Simbols simbol;
    private Color simbolColor;
    private Color textColor;
    private ArrayList<String> steps;

    Player() {
    }

    Player(String n) {
        name = n;
    }

    Player(String s, Simbols si) {
        name = s;
        simbol = si;
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

    String getMessage() {
        return message;
    }

    void setMessage(String m) {
        message = m;
    }

    int getPoints() {
        return points;
    }

    void setPoints(int p) {
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
}
