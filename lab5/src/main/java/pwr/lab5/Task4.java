/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab5;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;


/**
 *
 * @author azochniak
 */


public class Task4 {


    private interface AvrgCouter {
//        AvrgCouter(String filename);
        String getAns() throws Throwable;
    }
    private static class BenchmarkImpl implements Benchmark {

        public BenchmarkImpl() {
        }
        long timestart,timeend;
        @Override
        public void start() {
            timestart=System.currentTimeMillis();
        }

        @Override
        public void end() {
            timeend=System.currentTimeMillis();
        }

        @Override
        public long getTime() {
            return timeend-timestart;
        }
    }

    private static interface Benchmark {
        void start();
        void end();
        long getTime();
    }
    private class DOMParse extends BenchmarkImpl implements AvrgCouter {

        OutputStream os_baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(os_baos);
        Avrg avrg = new Avrg();
        public DOMParse() {
        }

        @Override
        public String getAns() throws Throwable {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            File file;
            file=new File("colors.xml");
            start();
            Document document = db.parse(file);
            NodeList nl = document.getElementsByTagName("value");
            for (int i = 0; i < nl.getLength(); i++) {
                if(nl.item(i).getNodeType()!=Node.ELEMENT_NODE) continue;
                Element el = (Element) nl.item(i);
//                System.out.println(el.getNodeName()+" "+el.getAttribute("color")+" "+el.getTextContent());
                avrg.add(el.getAttribute("color"),el.getTextContent());
            }
            end();
            return os_baos.toString()+avrg.getAns();
        }
        
    }
    static class Avrg {
        String colors[] = new String[]{"red", "green", "blue"};
        List<String> list = Arrays.asList(colors);
        int cid = -1;
        int noColors[] = new int[]{0,0,0};
        float avgs[] = new float[]{0,0,0};
        public void add(String color, String value) {
//            System.out.println(color+" "+value);
            cid=list.indexOf(color);
            float tmp=avgs[cid]*this.noColors[cid];
            tmp+=Integer.parseInt(value);
            this.noColors[cid]++;
            this.avgs[cid]=tmp/this.noColors[cid];
        }
        public String getAns() {
            return Arrays.toString(avgs);
        }
    }
    class SAXHandler extends DefaultHandler implements Benchmark, AvrgCouter {
        OutputStream os_baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(os_baos);

        @Override
        public void startDocument() {
            start();
        }

        @Override
        public void endDocument() {
            end();
        }
        Avrg avg = new Avrg();
        String color;
        boolean inelem=false;
        @Override
        public void startElement(String namespaceURI, String localName, String rawName, org.xml.sax.Attributes atts) {
            if("value".equalsIgnoreCase(localName)) {
                color=atts.getValue("color");
                inelem=true;
            }
        }

        @Override
        public void endElement(String namespaceURI, String localName, String rawName) {
            if("value".equalsIgnoreCase(localName)) 
                inelem=false;
        }

        @Override
        public void characters(char ch[], int start, int length) {
            if(!inelem) return;
            String value = (new String(ch, start, length));
//            System.out.println("parsing"+color+"="+value);
            avg.add(color, value);
            
        }
        Benchmark bench = new BenchmarkImpl();
        @Override
        public void start() {
            bench.start();
        }

        @Override
        public void end() {
            bench.end();
        }

        @Override
        public long getTime() {
            return bench.getTime();
        }

        @Override
        public String getAns() throws Throwable {
            XMLReader parser = XMLReaderFactory.createXMLReader();
            parser.setContentHandler(this);
//          parser.setFeature("http://xml.org/sax/features/validation", false);
            InputSource source = new InputSource(new InputStreamReader(new FileInputStream(new File("./colors.xml"))));
            start();
            parser.parse(source);
            end();
            return os_baos.toString()+avg.getAns();
        }

    }
    class StAX extends BenchmarkImpl implements AvrgCouter {
        Avrg avrg = new Avrg();
        @Override
        public String getAns() throws Throwable {
            OutputStream os_baos = new ByteArrayOutputStream();
            PrintStream out = new PrintStream(os_baos);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            factory.setProperty("javax.xml.stream.isValidating", "false");
            FileReader fileReader= new FileReader("colors.xml");
            XMLStreamReader reader = factory.createXMLStreamReader ( fileReader );
            String color="", value="";
            start();
            for (; reader.hasNext();) {
                reader.next();
                if(reader.getEventType()==XMLStreamReader.START_ELEMENT && reader.getAttributeCount()>0) {
                    avrg.add(reader.getAttributeValue(0), reader.getElementText());
//                    System.out.println(reader.getAttributeCount());
//                    System.out.println(reader.getAttributeValue(0));
//                    System.out.println(reader.getLocalName());
//                    System.out.println(reader.getElementText());
                }
            }
            end();
            return os_baos.toString()+avrg.getAns();
        }

    }
    static public void main(String data[]) throws Throwable {
        new Task4(null);
    }
    public static interface CallbbackNewMeasure {
        public void newMeasure(int n, long dom,  long sax, long stax);
    }
    CallbbackNewMeasure cb = null;
    public Task4(CallbbackNewMeasure cb) throws Throwable {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(os);
        for (int i = 0; i < 10; i++) {
            int cnt=1<<i;
            writeRandomColors(cnt, "colors.xml");
            DOMParse dom = new DOMParse();
            SAXHandler sax = new SAXHandler();
            StAX stax = new StAX();
            String ansrs[] = new String[] {dom.getAns(), sax.getAns(), stax.getAns()};
            long times[]=new long[]{dom.getTime(), sax.getTime(), stax.getTime()};
            if(cb!=null) 
                cb.newMeasure(cnt, times[0], times[1], times[2]);
            out.println(cnt +": "+ Arrays.toString(ansrs)+" \n"+Arrays.toString(times));
            }
        JOptionPane.showMessageDialog(null, os.toString());
    }
    
    private void writeRandomColors(int cnt, String filename) throws Throwable {
        Random generator = new Random(); 
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        //root elements
        Document doc = docBuilder.newDocument();

        Element rootElement = doc.createElement("values");
        doc.appendChild(rootElement);

        for (int i = 0; i < cnt; i++) {
            Element colorValue = doc.createElement("value");
            int r = generator.nextInt(255) + 0;
            colorValue.setAttribute("color", new String[]{"red","green","blue"}[generator.nextInt(3)]);
            colorValue.setTextContent(Integer.toString(r));
            rootElement.appendChild(colorValue);
        }
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);

        StreamResult result =  new StreamResult(new File(filename));
        transformer.transform(source, result);
    }
}
