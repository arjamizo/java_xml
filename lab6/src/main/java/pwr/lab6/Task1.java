package pwr.lab6;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
        List<String> thereAre=new ArrayList<String>();
        for (ChessBoard.Pos pos : xdml.getPos()) {
            System.out.println(pos.getValue()+" "+pos.getId()+" attr="+pos.getAttr());
            thereAre.add(pos.getValue());
        }
        System.out.println("Following are missing: "+whatIsMissing(thereAre));
    }
    static public List<String> whatIsMissing(List<String> thereAre) {
        List<String> fullSet=new ArrayList();
        fullSet.add("White king");
        fullSet.add("White queen");
        fullSet.add("White rook");
        fullSet.add("White rook");
        fullSet.add("White bishop");
        fullSet.add("White bishop");
        fullSet.add("White knight");
        fullSet.add("White knight");
        for (int i = 0; i < 8; i++) {
            fullSet.add("White Pawn");
        }
        for (String string : thereAre) {
            fullSet.remove(string);
        }
        return fullSet;
    }
}
