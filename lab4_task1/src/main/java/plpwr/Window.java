/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plpwr;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author azochniak
 */
public class Window extends JFrame {
    
    class JButtonFluent extends JButton {
        public JButtonFluent addActionListenerFluent(ActionListener actionListener) {
            super.addActionListener(actionListener);
            return this;
        }

        public JButtonFluent setLabelFluent(String label) {
            super.setLabel(label);
            return this;
        }
        
    }

    public Window() {
        setVisible(true);
        setSize(400, 400);
        setTitle("AZ, Pwr 2014");
        setLocation(150, 150);
        setPreferredSize(new Dimension(310, 75));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JButtonFluent button = new JButtonFluent();
        add(new JButtonFluent().setLabelFluent("Task 1").addActionListenerFluent(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    App.main(null);
                } catch (Throwable ex) {
                    Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                    throw new RuntimeException(ex);
                }
            }
        }));
    }
    
    public static void main(String data[]) {
        new Window();
    }
}
