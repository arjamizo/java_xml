/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pwr.lab5;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author azochniak
 */

class SAXHandler extends DefaultHandler {
    boolean inpos=false;
    String tmp;
    Set<String> thereAre=new HashSet();
    @Override
    public void startDocument() {
//        System.out.println("Staring..");
    }

    @Override
    public void endDocument() {
//        System.out.println("Finishing.");
    }

    @Override
    public void startElement(String namespaceURI, String localName, String rawName, org.xml.sax.Attributes atts) {
//        System.out.println("" + localName + atts.getValue("id"));
        inpos = localName.equals("pos");
        tmp=atts.getValue("id");
    }

    @Override
    public void endElement(String namespaceURI, String localName, String rawName) {
//        System.out.println("Element ends: " + localName);
        inpos = !localName.equals("pos");
    }

    @Override
    public void characters(char ch[], int start, int length) {
        if(!inpos) return;
        String label=new String(ch, start, length);
        thereAre.add(label);
        System.out.println(label+": "+tmp);
    }
    
    public List<String> whatIsMissing() {
        List<String> fullSet=new ArrayList();
        fullSet.add("White king");
        fullSet.add("White queen");
        fullSet.add("White rook");
        fullSet.add("White rook");
        fullSet.add("White bishop");
        fullSet.add("White bishop");
        fullSet.add("White knight");
        fullSet.add("White knight");
        for (int i = 0; i < 8; i++) {
            fullSet.add("White Pawn");
        }
        for (String string : thereAre) {
            fullSet.remove(string);
        }
        return fullSet;
    }
}

public class Task1 {

    static public void main(String data[]) throws Throwable {
        new Task1();
    }
    
    public Task1() throws Throwable {
        XMLReader parser = XMLReaderFactory.createXMLReader();
        SAXHandler handler = new SAXHandler();
        parser.setContentHandler(handler);
        //System.getProperty("user.dir")
        InputSource source = new InputSource(new InputStreamReader(new FileInputStream(new File("chess.xml"))));
        parser.parse(source);
        String ans;
        System.out.println(ans="Following paws are missing for white player: \n" + handler.whatIsMissing());
        JOptionPane.showMessageDialog(null, ans);
    }
    
}
