/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab5;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
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
        System.out.println(new String(ch, start, length)+": "+tmp);
    }
}

public class Task1 {

    static public void main(String data[]) throws Throwable {
        new Task1();
    }
    
    public Task1() throws Throwable {
        XMLReader parser = XMLReaderFactory.createXMLReader();
        parser.setContentHandler(new SAXHandler());
        InputSource source = new InputSource(new InputStreamReader(new FileInputStream(new File("chess.xml"))));
        parser.parse(source);
    }
    
}
