
package books;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Main {

    
    public static void main(String[] args) {
        
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse("D:/ITAcademy kursevi/8. Java web servisi i XML/JavaWebServisiAssignment_1/src/books/Catalog.xml");
            
            XPathFactory xpf = XPathFactory.newInstance();
            XPath xpath = xpf.newXPath();
            XPathExpression exp = xpath.compile("catalog/book[substring-before(publish_date,'-')>2005 and price>10]");
            
            NodeList res = (NodeList)exp.evaluate(doc, XPathConstants.NODESET);
            
            for (int i = 0; i < res.getLength(); i++) {
            Element el = (Element) res.item(i);
                
                StringBuilder sb = new StringBuilder();
                sb.append("Book ID : " + el.getAttribute("id") + "\n");
                sb.append("Author : " + el.getElementsByTagName("author").item(0).getTextContent() + "\n");
                sb.append("Title : " + el.getElementsByTagName("title").item(0).getTextContent() + "\n");
                sb.append("Genre : " + el.getElementsByTagName("genre").item(0).getTextContent() + "\n");
                sb.append("Price : " + el.getElementsByTagName("price").item(0).getTextContent() + "\n");
                sb.append("Publish date : " + el.getElementsByTagName("publish_date").item(0).getTextContent() + "\n");
                sb.append("Description : " + el.getElementsByTagName("description").item(0).getTextContent() + "\n");
                
                
                JOptionPane.showMessageDialog(null, sb, "Required book:", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException ex) {
            ex.printStackTrace();
        }
    }
    
}
