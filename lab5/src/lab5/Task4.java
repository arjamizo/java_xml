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
import java.util.ArrayList;
import java.util.Arrays;
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


public class Task4 {
class SAXHandler extends DefaultHandler {
    OutputStream os_baos = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(os_baos);
    
    @Override
    public void startDocument() {
//        System.out.println("Staring..");
    }

    @Override
    public void endDocument() {
        out.println("RGB: "+Arrays.toString(avgs));
    }
    
    boolean inelem=false;
    String colors[] = new String[]{"red", "green", "blue"};
    int color = -1;
    int noColors[] = new int[]{0,0,0};
    float avgs[] = new float[]{0,0,0};
    @Override
    public void startElement(String namespaceURI, String localName, String rawName, org.xml.sax.Attributes atts) {
        if("value".equalsIgnoreCase(localName)) {
            color=Arrays.asList(colors).indexOf(atts.getValue("color"));
            inelem=true;
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String rawName) {
        if("value".equalsIgnoreCase(localName)) 
            inelem=false;
    }

    @Override
    public void characters(char ch[], int start, int length) {
        if(!inelem) return;
        int value = Integer.parseInt(new String(ch, start, length));
        System.out.println("parsing"+color+"="+value);
        float tmp=avgs[color]*this.noColors[color];
        tmp+=value;
        this.noColors[color]++;
        this.avgs[color]=tmp/this.noColors[color];
    }
    
    }
    static public void main(String data[]) throws Throwable {
        new Task4();
    }
    
    public Task4() throws Throwable {
        XMLReader parser = XMLReaderFactory.createXMLReader();
        SAXHandler saxHandler = new SAXHandler();
        parser.setContentHandler(saxHandler);
//        parser.setFeature("http://xml.org/sax/features/validation", false);
        
        InputSource source = new InputSource(new InputStreamReader(new FileInputStream(new File("./colors.xml"))));
        parser.parse(source);
        System.out.print(saxHandler.os_baos);
        JOptionPane.showMessageDialog(null, saxHandler.os_baos.toString());
    }
    
}
