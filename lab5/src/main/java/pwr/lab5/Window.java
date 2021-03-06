/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pwr.lab5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.CategoryTableXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author azochniak
 */
public class Window extends javax.swing.JPanel implements ActionListener {
    public static void main(String[] args) {
        try {
            JFrame frame = new JFrame();
            Window win = new Window();
    //        frame.add(pane);
            frame.add(win);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.pack();

            JButton tab[] = new JButton[]{win.jButton1,win.jButton2,win.jButton3,win.jButton4,win.jButton5};
            for (JButton jb : tab) {
                jb.addActionListener(win);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getSource().toString();
        Matcher m = Pattern.compile("text=.*?(\\d+)").matcher(str);
        m.find();
        System.out.println(str);
        int id = Integer.parseInt(m.group(1));
        System.out.println(id);
//        String backup=System.getProperty("user.dir");
//        String progDir = (new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath())).getAbsolutePath();
//        try{
//            System.setProperty("user.dir", progDir);
//            File f=new File("./chess.xml");
//            if(!f.exists()) 
//                throw new FileNotFoundException("could not find chess.xml");
//        } catch (Exception ex) {
//            System.setProperty("user.dir", backup);
//        }
        try {
            if(id==1) new Task1();
            if(id==2) new Task2();
            if(id==3) new Task3();
            if(id==4) {
                new Task4(new Task4.CallbbackNewMeasure() {

                    @Override
                    public void newMeasure(int n, long dom, long sax, long stax) {
                        series[0].add(n, dom);
                        series[1].add(n, sax);
                        series[2].add(n, stax);
                    }
                });
                showChart(this);
            }
        } catch (Throwable ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage()+"\nCWD: "+System.getProperty("user.dir")+"\nIn case of problems with accessing files, try running from console: java -jar *.jar");
            throw new RuntimeException(ex);
        }
    }
    
    /**
     * Creates new form Window
     */
    XYSeries series[] = new XYSeries[]{
     new XYSeries("DOM")
    , new XYSeries("SAX")
    , new XYSeries("StAX")};
    XYSeriesCollection dataset = new XYSeriesCollection();
    public Window() {
        initComponents();
        dataset.addSeries(series[0]);
        dataset.addSeries(series[1]);
        dataset.addSeries(series[2]);
    }
    
    public void showChart(Window win) {
        final JFreeChart chart = ChartFactory.createXYLineChart(
            "DOM, SAX, StAX parsing time",      // chart title
            "number of XML nodes",                      // x axis label
            "time [ms]",                      // y axis label
            win.dataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );
        ChartFrame pane = new ChartFrame("ELO",chart);
        pane.pack();
        pane.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        jButton3.setText("jButton3");

        jButton4.setText("jButton4");

        jButton5.setText("jButton5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton5)
                    .addComponent(jButton4)
                    .addComponent(jButton3))
                .addContainerGap(323, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(129, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    // End of variables declaration//GEN-END:variables

}
