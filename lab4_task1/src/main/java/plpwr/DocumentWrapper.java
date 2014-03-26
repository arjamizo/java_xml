/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plpwr;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author azochniak
 */
class DocumentWrapper {

    public DocumentWrapper(Document document) {
        this.document=document;
    }
    private Document document;

    public Document getDocument() {
        return document;
    }

    ElementWrapper createElement(String book) {
        return new ElementWrapper(document.createElement(book));
    }

    public static class ElementWrapper {

        public ElementWrapper(Element createElement) {
            this.element=createElement;
        }
        private Element element;

        public Node getElement() {
            return element;
        }

        Element setAttribute(String author, String sb_from_Pwr) {
            element.setAttribute(author, sb_from_Pwr);
            return element;
        }
    }
    
}
