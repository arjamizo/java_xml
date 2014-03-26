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
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author azochniak
 */
class Task2 {

    public Task2() throws Throwable {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        File[] files = new FileDialogFluent(Window.getWindow(), "Choose file", FileDialog.LOAD).setVisibleFluent(true).getFiles();
        Document document = db.parse(files[0]);
        
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

    private void modifyDocument(Document document) {
        Element element = (Element) document.getChildNodes().item(0);
        element.getParentNode().removeChild(element);
        
        ((Element)element).setNodeValue("NEW VALUE!");
        document.appendChild(element);
        
        ((Element)document.getChildNodes().item(0)).appendChild(new DocumentWrapper(document).createElement("book").setAttribute("author", "sb from Pwr"));
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

}
