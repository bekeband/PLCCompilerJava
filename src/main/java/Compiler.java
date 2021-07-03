import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.beans.XMLDecoder;
import java.io.File;

import java.awt.*;       // Using AWT's Graphics and Color
import java.awt.event.*; // Using AWT event classes and listener interfaces
import javax.swing.*;    // Using Swing's components and containers

public class Compiler {

    String SourceFileName;
    String PreprocessorFileName;
    String XrefFileName;

    public Compiler(String sourceFileName, String preprocessorFileName, String xrefFileName) {
        SourceFileName = sourceFileName;
        PreprocessorFileName = preprocessorFileName;
        XrefFileName = xrefFileName;
    }

    public Compiler() {
    }

    public void CreateDemoFile() {
        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // root element
            Element rootElement = doc.createElement("networks");
            doc.appendChild(rootElement);

            // supercars element
            Element network = doc.createElement("network");
            rootElement.appendChild(network);

            // setting attribute to element
            Attr titleattr = doc.createAttribute("title");
            titleattr.setValue("first network");
            network.setAttributeNode(titleattr);

            // setting attribute to element
            Attr commentattr = doc.createAttribute("comment");
            commentattr.setValue("This is the first network.");
            network.setAttributeNode(commentattr);

            // network element
            Element boolElement = doc.createElement("AND");
            Attr attrType = doc.createAttribute("ADDR");
            attrType.setValue("0x5673");
            boolElement.setAttributeNode(attrType);
            network.appendChild(boolElement);

            boolElement = doc.createElement("AND");
            attrType = doc.createAttribute("ADDR");
            attrType.setValue("0x22311");
            boolElement.setAttributeNode(attrType);
            network.appendChild(boolElement);

            boolElement = doc.createElement("AND");
            attrType = doc.createAttribute("CONST");
            attrType.setValue("0x01");
            boolElement.setAttributeNode(attrType);
            network.appendChild(boolElement);


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(SourceFileName));
            transformer.transform(source, result);

            // Output to console for testing
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getSourceFileName() {
        return SourceFileName;
    }

    public void setSourceFileName(String sourceFileName) {
        SourceFileName = sourceFileName;
    }

    public String getPreprocessorFileName() {
        return PreprocessorFileName;
    }

    public void setPreprocessorFileName(String preprocessorFileName) {
        PreprocessorFileName = preprocessorFileName;
    }

    public String getXrefFileName() {
        return XrefFileName;
    }

    public void setXrefFileName(String xrefFileName) {
        XrefFileName = xrefFileName;
    }
}
