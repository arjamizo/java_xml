/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package task2;

import java.io.*;
import javax.xml.bind.*;
import task2.food.Menu;

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
        marshaller.marshal(menu,new FileOutputStream("/tmp/output.xml"));
        
        File file = new File("/tmp/output.xml");
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int)file.length()];
        fis.read(data);
        fis.close();
        
        String s = new String(data, "UTF-8");
        System.out.println(s);
    }
}
