/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab5;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
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


public class Task2 {

class SAXHandler extends DefaultHandler {
    OutputStream os_baos = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(os_baos);
    
    int in=0;
    
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
        in++;
        out.println("Start tag:" + localName);
    }

    @Override
    public void endElement(String namespaceURI, String localName, String rawName) {
        out.println("End tag: " + localName);
        in--;
    }

    @Override
    public void characters(char ch[], int start, int length) {
        if(new String(ch, start, length).trim().length()>0)
            out.println(new String(ch, start, length));
    }
    }
    static public void main(String data[]) throws Throwable {
        new Task2();
    }
    
    public Task2() throws Throwable {
        XMLReader parser = XMLReaderFactory.createXMLReader();
        SAXHandler saxHandler = new SAXHandler();
        parser.setContentHandler(saxHandler);
        InputSource source = new InputSource(new InputStreamReader(new FileInputStream(new File("orders.xml"))));
        parser.parse(source);
        System.out.print(saxHandler.os_baos);
        JOptionPane.showMessageDialog(null, saxHandler.os_baos.toString());
    }
    
}
