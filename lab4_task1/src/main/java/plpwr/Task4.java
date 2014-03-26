/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plpwr;

import java.awt.FileDialog;
import java.io.File;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import static org.w3c.dom.Node.ELEMENT_NODE;
import org.w3c.dom.NodeList;

/**
 *
 * @author azochniak
 */
class Task4 {
    
    static class XmlUtil {

        private XmlUtil() {
        }

        public static List<Node> asList(NodeList n) {
            return n.getLength() == 0
                    ? Collections.<Node>emptyList() : new NodeListWrapper(n);
        }

        static final class NodeListWrapper extends AbstractList<Node>
                implements RandomAccess {

            private final NodeList list;

            NodeListWrapper(NodeList l) {
                list = l;
            }

            public Node get(int index) {
                return list.item(index);
            }

            public int size() {
                return list.getLength();
            }
        }
    }

    public Task4() throws Throwable {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        File file;
        try {
            File[] files={}; 
//            files = new Task2.FileDialogFluent(Window.getWindow(), "Choose file", FileDialog.LOAD).setVisibleFluent(true).getFiles();
            file = files[0];
        } catch (Throwable e) {
            file=new File("book.xml");
        }
        Document document = db.parse(file);
//        List<Node> nodes = XmlUtil.asList(document.getChildNodes().item(0).getChildNodes());
        List<String> header=new ArrayList<>();
        Node root=null;
        for(int i=0; i<document.getChildNodes().getLength(); ++i) {
            if (document.getChildNodes().item(i).getNodeName().equals("#text")) continue;
            root = document.getChildNodes().item(i);
        }
        NodeList nodes;
        nodes = root.getChildNodes();
        NodeList firstElement=null;
        for(int i=0; i<nodes.getLength(); ++i) {
            if (nodes.item(i).getNodeName().equals("#text")) continue;
            firstElement = nodes.item(i).getChildNodes();
        }
//        for (Node node : XmlUtil.asList(document.getChildNodes().item(0).getChildNodes().item(0).getChildNodes())) {
//            header.add(node.getNodeName());
//        }
        for(int i=0; i<firstElement.getLength(); ++i) {
//            System.out.println("ELO: "+firstElement.item(i).getNodeName());
            if(nodes.item(i).getNodeName().equals("#text")) continue;
            Element node = (Element)firstElement.item(i);
            header.add(node.getNodeName());
        }
        System.out.println("header: "+StringUtils.join(header.iterator(), '\t'));
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.join(header.iterator(), ';'));
//        for (Node node : nodes) {
        for (int i = 0; i < nodes.getLength(); i++) {
//            System.out.println(nodes.item(i).getNodeName());
            if(nodes.item(i).getNodeName().equals("#text")) continue;
            Element node = (Element)nodes.item(i);
            NodeList inNodes = node.getChildNodes();
//            for(Node n : XmlUtil.asList(e.getChildNodes())) {
            List<String> row=new ArrayList<>();
            for (int j = 0; j < inNodes.getLength(); j++) {
//                System.out.println(inNodes.item(j).getNodeName());
                if(inNodes.item(j).getNodeType()!=Node.ELEMENT_NODE) continue;
                String content = (inNodes.item(j).getChildNodes().item(0)).getNodeValue();
                content=content.replaceAll("\\s+", " ");
                row.add(content);
            }
            System.out.println("\nrow:\t"+StringUtils.join(row.iterator(),'\t'));
            sb.append(StringUtils.join(row.iterator(),'\t'));
        }
        setAns(sb.toString());
        
    }
    private String ans;

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    
    public static void main(String data[]) throws Throwable {
        new Task4();
    }
    
}
