/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package task3;

import com.google.common.collect.Ordering;
import com.google.common.primitives.*;
import java.io.File;
import java.util.*;
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
        System.out.println("Max elem="+getMaxPrice(xdml.getBook()));
    }
    
    static Book getMaxPrice(List<Book> books) {
        Ordering<Book> o = new Ordering<Book>() {
            @Override
            public int compare(Book left, Book right) {
                return Floats.compare(left.getPrice(), right.getPrice());
            }
        };
        return o.max(books);
    }
}
