/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package task2;

import java.io.*;
import java.util.Random;
import javax.xml.bind.*;
import task2.food.Menu;
import task2.food.Menu.*;

/**
 *
 * @author azochniak
 */
public class Task2 {
    public static void main(String args[]) throws Throwable {
        JAXBContext jc = JAXBContext.newInstance("task2.food" );
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
        Menu menu = new Menu();
        
        for (int i = 0; i < 10; i++) {
            Food f = new Menu.Food();
            f.setCalories(randInt(100, 10000));
            String[] categories = new String[]{"junk food", "GMO", "natural"};
            f.setCategory(categories[randInt(0,categories.length-1)]);
            String[] descs = new String[]{"unavailable","available"};
            f.setDescription(descs[randInt(0,descs.length-1)]);
            String[] names = new String[]{"french fries", "potatoes", "pork"};
            f.setName(names[randInt(0,descs.length-1)]);
            f.setPrice(randInt(100, 10000)/1.0f);
            menu.getFood().add(f);
        }
        
        marshaller.marshal(menu,new FileOutputStream("/tmp/output.xml"));
        
        File file = new File("/tmp/output.xml");
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int)file.length()];
        fis.read(data);
        fis.close();
        
        String s = new String(data, "UTF-8");
        System.out.println(s);
    }
    
    /**
     * Returns a pseudo-random number between min and max, inclusive. The
     * difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimum value
     * @param max Maximum value. Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     */
    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

    // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
