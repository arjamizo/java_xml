package pwr.lab6;

import java.io.File;
import javax.xml.bind.*;
import pwr.jaxb.ChessBoard;

/**
 * Hello world!
 *
 */
public class Task1 
{
    public static void main( String[] args ) throws Throwable
    {
        JAXBContext jc = JAXBContext.newInstance("pwr.jaxb" );
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        ChessBoard xdml = (ChessBoard)unmarshaller.unmarshal(new File("chess.xml"));
        for (ChessBoard.Pos pos : xdml.getPos()) {
            System.out.println(pos.getValue()+" "+pos.getId());
        }
    }
}
