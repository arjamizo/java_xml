/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plpwr;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.StringWriter;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.util.EventReaderDelegate;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

/**
 *
 * @author azochniak
 */
class Task5 {
    OutputStream os_baos = new ByteArrayOutputStream();
    PrintStream result = new PrintStream(os_baos);

    public static class DTDReplacer extends EventReaderDelegate {

        private final XMLEvent dtd;
        private boolean sendDtd = false;

        public DTDReplacer(XMLEventReader reader, XMLEvent dtd) {
            super(reader);
            if (dtd.getEventType() != XMLEvent.DTD) {
                throw new IllegalArgumentException("" + dtd);
            }
            this.dtd = dtd;
        }

        @Override
        public XMLEvent nextEvent() throws XMLStreamException {
            if (sendDtd) {
                sendDtd = false;
                return dtd;
            }
            XMLEvent evt = super.nextEvent();
            if (evt.getEventType() == XMLEvent.START_DOCUMENT) {
                sendDtd = true;
            } else if (evt.getEventType() == XMLEvent.DTD) {
                // discard old DTD
                return super.nextEvent();
            }
            return evt;
        }

    }
public class SimpleErrorHandler implements ErrorHandler {
    public void warning(SAXParseException e) throws SAXException {
//        System.out.println(e.getMessage());
        result.println(e.getMessage());
    }

    public void error(SAXParseException e) throws SAXException {
//        System.out.println(e.getMessage());
        result.println(e.getMessage());
    }

    public void fatalError(SAXParseException e) throws SAXException {
//        System.out.println(e.getMessage());
        result.println(e.getMessage());
    }
}
    public Task5() throws Throwable {
//        dtdReplace();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(true);

        SAXParser parser = factory.newSAXParser();

        XMLReader reader = parser.getXMLReader();
        reader.setErrorHandler(new SimpleErrorHandler());
        reader.parse(new InputSource(getFile("select xml")));
        result.println("validated  finished");
    }

    protected void dtdReplace() throws XMLStreamException, FactoryConfigurationError {
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent dtd = eventFactory
                .createDTD("<!DOCTYPE Employee SYSTEM \""+getFile("select dtd")+"\">");

        XMLInputFactory inFactory = XMLInputFactory.newInstance();
        XMLOutputFactory outFactory = XMLOutputFactory.newInstance();
        XMLEventReader reader = inFactory
                .createXMLEventReader(new StreamSource(
                        getFile("select xml")));
        reader = new DTDReplacer(reader, dtd);
        XMLEventWriter writer = outFactory.createXMLEventWriter(System.out);
        writer.add(reader);
        writer.flush();
    }
    
    
    private String getFile(String title) {
        if(title.equals("select dtd")) {
//            return "dtd.dtd";
            throw new RuntimeException("should  not be used");
        } else if (title.equals("select xml")) {
            return "Lab1_task1.xml";
//            return "xml.xml";
        } else
            throw new RuntimeException("wrong title");
    }
    
    public static void main(String args[]) throws Throwable {
        String ans=new Task5().getAns();
        System.out.println(ans);
//        javax.swing.JOptionPane.showMessageDialog(null, ans);
    }
    
    public String getAns() {
        return os_baos. toString();
    }

}
