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


public class Task3 {
class User {
    String desc="";
    User add(String desc) {
        this.desc+=desc+" ";
        return this;
    }

        @Override
        public String toString() {
            return "\nUser{" + "desc=" + desc + '}';
        }
    }
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
        out.println(users.toString());
    }
    
    boolean inelem=false,append=false;
    String thistag;
    List<User> users = new ArrayList<>();
    Set<String> important = new HashSet(Arrays.asList(new String[]{"name","surname","phone"}));
    @Override
    public void startElement(String namespaceURI, String localName, String rawName, org.xml.sax.Attributes atts) {
        if("employee".equalsIgnoreCase(localName)) {
            inelem=true;
            users.add(new User());
        }
        if(!inelem) return;
        if(important.contains(localName)) {
            append=true;
            thistag=new String(localName);
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String rawName) {
        if("employee".equalsIgnoreCase(localName)) 
            inelem=false;
        if(important.contains(localName)) {
            append=false;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) {
        if(!append) return;
//        if(new String(ch, start, length).trim().length()>0)
        users.get(users.size()-1).add(thistag+": "+new String(ch, start, length));
    }
    
    }
    static public void main(String data[]) throws Throwable {
        new Task3();
    }
    
    public Task3() throws Throwable {
        XMLReader parser = XMLReaderFactory.createXMLReader();
        SAXHandler saxHandler = new SAXHandler();
        parser.setContentHandler(saxHandler);
//        parser.setFeature("http://xml.org/sax/features/validation", false);
        
        InputSource source = new InputSource(new InputStreamReader(new FileInputStream(new File("./Lab1_task1.xml"))));
        parser.parse(source);
        System.out.print(saxHandler.os_baos);
        JOptionPane.showMessageDialog(null, saxHandler.os_baos.toString());
    }
    
}
