package plpwr;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
    public static void main( String[] args ) 
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
                
                rootElement.appendChild(doc.createElement("Topics"));
 
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("file.xml"));
                
		transformer.transform(source, result);
		transformer.transform(source, new StreamResult(System.out));
                
 
		System.out.println("\nFile saved!");
 
	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
	}
}
