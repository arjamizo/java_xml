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

        JTextArea msg = new JTextArea(App.formatDocument(document));
        msg.setLineWrap(true);
        msg.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(msg);

        javax.swing.JOptionPane.showMessageDialog(null, scrollPane);
    }

    private static class FileDialogFluent extends FileDialog {

        public FileDialogFluent(JFrame window, String hehe, int LOAD) {
            super(window, hehe, LOAD);
        }

        public FileDialogFluent setVisibleFluent(boolean f) {
            super.setVisible(f);
            return this;
        }
    }

}
