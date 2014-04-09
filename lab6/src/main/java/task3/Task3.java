/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package task3;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.math.DoubleMath;
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
        xdml.getBook().remove(xdml.getBook().get(0));
        Iterables.removeIf(xdml.getBook(), new Predicate<Book>() {

            public boolean apply(Book t) {
                return (t.getGenre().contains("Fan"));
            }
        });
        for (Book pos : xdml.getBook()) {
            System.out.println(pos.getAuthor()+" "+pos.getTitle());
        }
        System.out.println("Max elem="+getMaxPrice(xdml.getBook()));
        System.out.println("Avrg price="+getAvrgPrice(xdml.getBook()));
        System.out.println("Avrg price="+getOldestBook(xdml.getBook()));
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

    //http://www.leveluplunch.com/java/examples/calculate-average-of-array/}
    private static float getAvrgPrice(List<Book> books) {
        List<Float> list=new ArrayList<Float>();
        for (Book book : books) {
            list.add(book.getPrice());
        }
        return new Float(DoubleMath.mean(list));
    }
    private static Book getOldestBook(List<Book> books) {
        Ordering<Book> o = new Ordering<Book>() {
            @Override
            public int compare(Book left, Book right) {
                return left.getPublishDate().compare(right.getPublishDate());
            }
        };
        return o.min(books);
    }
}
