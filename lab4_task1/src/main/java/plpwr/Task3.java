/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plpwr;

import au.com.bytecode.opencsv.CSVReader;
import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.File;
import java.util.List;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import plpwr.DocumentWrapper.ElementWrapper;
import static plpwr.Task2.showDialog;

/**
 *
 * @author azochniak
 */
class Task3 {

    /***
     * @link http://stackoverflow.com/a/18068820/781312
     */
    public Task3() throws Throwable {
        Reader filereader;
        try {
            File[] files = {}; 
//            files= new Task2.FileDialogFluent(Window.getWindow(), "Choose file", FileDialog.LOAD).setVisibleFluent(true).getFiles();
            File file=files[0];
            filereader = new FileReader(file);
        } catch (Throwable t) {
            String input="name,dateOfBirth,dept,jobTitle\n"+
            "John,1962-11-24,accounting,senior accountant\n"+
            "Tina,1962-09-26,administration,manager\n"+
            "Karen,1972-01-10, marketing,graphic designer\n"+
            "Michael,1978-02-11,research,programmer\n"+
            "Sandra,1976-10-26,marketing,account manager";
            filereader=new StringReader(input);
        }
        CSVReader reader = new CSVReader(filereader);
        List<String[]> rows = reader.readAll();
        String header[]=rows.remove(0);
        System.out.println(StringUtils.join(header,'\t'));
        
        for (String[] str : rows) {
            System.out.println(StringUtils.join(str,'\t'));
        }
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();
        Element el=doc.createElement("employees");
        for (String[] row : rows) {
            Element e=doc.createElement("employee");
            for (int i = 0; i < row.length; i++) {
                System.out.println("ROW"+row[i]);
                Element a = doc.createElement(header[i]);
                a.appendChild(doc.createTextNode(row[i]));
                e.appendChild(a);
            }
            el.appendChild(e);
        }
        doc.appendChild(el);
        
        
        JTextArea msg = new JTextArea(App.formatDocument(doc));
        msg.setLineWrap(true);
        msg.setWrapStyleWord(true);

        showDialog(null, msg);
    }
    
}
