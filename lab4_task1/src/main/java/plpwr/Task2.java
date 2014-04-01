/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plpwr;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.FileDialog;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author azochniak
 */
class Task2 {

    public Task2() throws Throwable {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        File[] files = {}; 
//        files = new FileDialogFluent(Window.getWindow(), "Choose file", FileDialog.LOAD).setVisibleFluent(true).getFiles();
        File file;
//        file=files[0];
        file=new File("book.xml");
        Document document = db.parse(file);
        
        modifyDocument(document);

        JTextArea msg = new JTextArea(App.formatDocument(document));
        msg.setLineWrap(true);
        msg.setWrapStyleWord(true);

        showDialog(null, msg);
    }

    /**
     * @link http://benohead.com/java-make-joptionpane-dialog-resizable/
     *
     * @param frame
     * @param component
     */
    public static void showDialog(Component frame, final Component component) {
        // wrap a scrollpane around the component
        JScrollPane scrollPane = new JScrollPane(component);
        // make the dialog resizable
        component.addHierarchyListener(new HierarchyListener() {
            public void hierarchyChanged(HierarchyEvent e) {
                java.awt.Window window = SwingUtilities.getWindowAncestor(component);
                if (window instanceof Dialog) {
                    Dialog dialog = (Dialog) window;
                    if (!dialog.isResizable()) {
                        dialog.setResizable(true);
                    }
                }
            }
        });
        JOptionPane.showMessageDialog(frame, scrollPane);
    }

    private void modifyDocument(Document document) throws Throwable {
        Element element = (Element) document.getChildNodes().item(0);
        element.getParentNode().removeChild(element);
        
        ((Element)element).setNodeValue("NEW VALUE!");
        document.appendChild(element);
        
        ((Element)document.getChildNodes().item(0)).appendChild(new DocumentWrapper(document).createElement("book").setAttribute("author", "sb from Pwr"));
        
        System.out.println(((Element)document.getChildNodes().item(0)).getNodeName());
        Element bk104=document.getElementById("bk104");
//    XPath xpath = XPathFactory.newInstance().newXPath();
//    String expression = "/book[@id='bk104']";
//    Node bk104 = (Node) xpath.evaluate(expression, document, XPathConstants.NODE);  
        System.out.println(bk104);
//      ((Element)document.getChildNodes().item(0)).removeChild(bk104);
        XPathFactory builder = XPathFactory.newInstance();
        XPath xpath = builder.newXPath();

        String label = "Person 3";
        XPathExpression exp = xpath.compile("//book[@id = 'bk104']");

        Node node = (Node) exp.evaluate(document, XPathConstants.NODE);  
        System.out.println("asd" +node.getTextContent());
        node.getParentNode().removeChild(node);
    }

    public static class FileDialogFluent extends FileDialog {

        public FileDialogFluent(JFrame window, String hehe, int LOAD) {
            super(window, hehe, LOAD);
        }

        public FileDialogFluent setVisibleFluent(boolean f) {
            super.setVisible(f);
            return this;
        }
    }
    
    public static void main(String args[]) {
        try {
            new Task2();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

}
