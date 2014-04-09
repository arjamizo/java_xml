/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package task3;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import task3.book.Catalog;
import task3.book.Catalog.*;

/**
 *
 * @author azochniak
 */
public class Task3 {
    
    public static void main( String[] args ) throws Throwable
    {
        JAXBContext jc = JAXBContext.newInstance("task3.book" );
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        Catalog xdml = (Catalog)unmarshaller.unmarshal(new File("Book.xml"));
        for (Book pos : xdml.getBook()) {
            System.out.println(pos.getAuthor()+" "+pos.getTitle());
        }
    }
}
