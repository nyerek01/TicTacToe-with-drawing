package example;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class XML implements Interface {

    private Date d;
    private String s;

    XML() {

    }

    void fileName() {
        d = new Date();
        s = d.getHours() + "h_" + d.getMinutes() + "m_" + d.getSeconds() + "s";//A file neve az aktualis ido lesz
    }

    void xmlLogic(String s) {
        try {
            loadXML(path + s + ext);
        } catch (Exception e) {
            createXML(path + s + ext);
        }
    }

    void createXML(String s) {
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("Players");
            doc.appendChild(rootElement);

            //Lehetne ciklusban, vegig menni a Playereken, ha lenne egy adatszerkezet, mondjuk lista ahol tarolva vannak a playerek... Mert igy annyiszor kell leirni ahany jatekos van
            Element Player = doc.createElement("Player");
            rootElement.appendChild(Player);
            Player.setAttribute("ID", Game.human.getID() + "");

            Element Name = doc.createElement("Name");
            Name.appendChild(doc.createTextNode(Game.human.getName()));//.getName()
            Player.appendChild(Name);

            Element Level = doc.createElement("Level");
            Level.appendChild(doc.createTextNode(Game.human.getLevel()));//.getLevel()
//            Level.appendChild(doc.createTextNode("Beginner"));//.getLevel()
            Player.appendChild(Level);

            Element Points = doc.createElement("Points");
            Points.appendChild(doc.createTextNode(Game.human.getPoints() + ""));
//            Points.appendChild(doc.createTextNode(9 + ""));
            Player.appendChild(Points);

            Element NumberOfGames = doc.createElement("NumberOfGames");
            NumberOfGames.appendChild(doc.createTextNode(Game.human.getNumberGames() + ""));
//            NumberOfGames.appendChild(doc.createTextNode(9 + ""));
            Player.appendChild(NumberOfGames);

            Element Win = doc.createElement("Win");
            Win.appendChild(doc.createTextNode(Game.human.getNumberWins() + ""));
//            Win.appendChild(doc.createTextNode(6 + ""));
            Player.appendChild(Win);

            Element Lose = doc.createElement("Lose");
            Lose.appendChild(doc.createTextNode(Game.human.getNumberLoses() + ""));
//            Lose.appendChild(doc.createTextNode(1 + ""));
            Player.appendChild(Lose);

            Element Tie = doc.createElement("Tie");
            Tie.appendChild(doc.createTextNode(Game.human.getNumberTie() + ""));
//            Tie.appendChild(doc.createTextNode(2 + ""));
            Player.appendChild(Tie);

            Element Time = doc.createElement("Time");
//            Time.appendChild(doc.createTextNode(Game.human.getTime() + ""));
            Time.appendChild(doc.createTextNode(78 + ""));
            Player.appendChild(Time);

            Element Steps = doc.createElement("Steps");
            Steps.appendChild(doc.createTextNode(Printer.getList(Game.human.getSteps())));
//            Steps.appendChild(doc.createTextNode("" + 44));
            Player.appendChild(Steps);

            //---------------------------------------------Es itt van masodszor ugyan az
            Element Player2 = doc.createElement("Player");
            rootElement.appendChild(Player2);
            Player2.setAttribute("ID", Game.comp.getID() + "");

            Element Name2 = doc.createElement("Name");
            Name2.appendChild(doc.createTextNode(Game.comp.getName() + ""));//.getName()
            Player2.appendChild(Name2);

            Element Level2 = doc.createElement("Level");
            Level2.appendChild(doc.createTextNode(Game.comp.getLevel() + ""));//.getLevel()
            Player2.appendChild(Level2);

            Element Points2 = doc.createElement("Points");
            Points2.appendChild(doc.createTextNode(Game.comp.getPoints() + ""));
            Player2.appendChild(Points2);

            Element NumberOfGames2 = doc.createElement("NumberOfGames");
            NumberOfGames2.appendChild(doc.createTextNode(Game.comp.getNumberGames() + ""));
            Player2.appendChild(NumberOfGames2);

            Element Win2 = doc.createElement("Win");
            Win2.appendChild(doc.createTextNode(Game.comp.getNumberWins() + ""));
            Player2.appendChild(Win2);

            Element Lose2 = doc.createElement("Lose");
            Lose2.appendChild(doc.createTextNode(Game.comp.getNumberLoses() + ""));
            Player2.appendChild(Lose2);

            Element Tie2 = doc.createElement("Tie");
            Tie2.appendChild(doc.createTextNode(Game.comp.getNumberTie() + ""));
            Player2.appendChild(Tie2);

            Element Time2 = doc.createElement("Time");
            Time2.appendChild(doc.createTextNode(Game.comp.getTime() + ""));
            Player2.appendChild(Time2);

            Element Steps2 = doc.createElement("Steps");
            Steps2.appendChild(doc.createTextNode(Printer.getList(Game.comp.getSteps())));
            Player2.appendChild(Steps2);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(s));
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
    }

    void loadXML(String s) {
        short pPoints, pGameNumber, pWin, pLose, pTie, pTime;
        String pName, pLevel, pSteps;
        File xmlFile = null;
        try {
            xmlFile = new File(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Document d = null;
        try {
            d = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        try {
            NodeList nodeList = d.getDocumentElement().getElementsByTagName("Player");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element node = (Element) nodeList.item(i);

                Node name = node.getElementsByTagName("Name").item(0);
                pName = name.getFirstChild().getNodeValue();

                Node level = node.getElementsByTagName("Level").item(0);
                pLevel = level.getFirstChild().getNodeValue();

                Node p = node.getElementsByTagName("Points").item(0);
                pPoints = Short.parseShort(p.getFirstChild().getNodeValue());

                Node nOG = node.getElementsByTagName("NumberOfGames").item(0);
                pGameNumber = Short.parseShort(nOG.getFirstChild().getNodeValue());

                Node w = node.getElementsByTagName("Win").item(0);
                pWin = Short.parseShort(w.getFirstChild().getNodeValue());

                Node l = node.getElementsByTagName("Lose").item(0);
                pLose = Short.parseShort(l.getFirstChild().getNodeValue());

                Node ti = node.getElementsByTagName("Tie").item(0);
                pTie = Short.parseShort(ti.getFirstChild().getNodeValue());

                Node t = node.getElementsByTagName("Time").item(0);
                pTime = Short.parseShort(t.getFirstChild().getNodeValue());

                Node steps = node.getElementsByTagName("Steps").item(0);
                pSteps = steps.getFirstChild().getNodeValue();

                if (i == 0) {
                    Game.human = new Player(pName, pLevel, pPoints, pGameNumber, pWin, pLose, pTie, pTime, pSteps, Simbols.X);
                } else if (i < 2) {
                    Game.comp = new Player(pName, pLevel, pPoints, pGameNumber, pWin, pLose, pTie, pTime, pSteps, Simbols.O);
                }
            }
        } catch (NumberFormatException | DOMException e) {
            e.printStackTrace();
        }
    }

    public Date getD() {
        return d;
    }

    public void setD(Date date) {
        d = date;
    }

    public String getS() {
        return s;
    }

    public void setS(String str) {
        s = str;
    }

}
