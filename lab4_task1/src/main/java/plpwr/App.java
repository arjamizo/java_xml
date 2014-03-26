package plpwr;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

/**
 * Hello world!
 *
 */
public class App 
{
    /**
     * @link http://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/
     * @param args 
     */
    public static void main( String[] args ) throws Throwable
    {
	  try {
 
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("ComputerEngineering");
		doc.appendChild(rootElement);
 
		Element course = doc.createElement("Course");
                course.setAttribute("Name", "Application programming - Java and XML technologies");
                course.setAttribute("Author", "Artur");
		rootElement.appendChild(course);
                
                course.appendChild(doc.createElement("Topics"));
 
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("file.xml"));
                
                transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //other way of beautifying from http://examples.javacodegeeks.com/core-java/xml/dom/remove-node-from-dom-document/
		transformer.transform(source, result);
		transformer.transform(source, new StreamResult(System.out));
                
                StringWriter writer = new StringWriter();
		transformer.transform(source, new StreamResult(writer));
                javax.swing.JOptionPane.showMessageDialog(null, beautifyXML(writer.toString()));
 
		System.out.println("\nFile saved!");
 
	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
	}

    private static String beautifyXML(String in) throws Throwable {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(in));
        Document document = db.parse(is);
        return formatDocument(document);
    }

    protected static String formatDocument(Document document) throws IOException {
        OutputFormat format = new OutputFormat(document);
        format.setLineWidth(65);
        format.setIndenting(true);
        format.setIndent(2);
        Writer out = new StringWriter();
        XMLSerializer serializer = new XMLSerializer(out, format);
        serializer.serialize(document);
        return out.toString();
    }
}
