import java.io.File;
import java.text.ParseException;
import java.io.StringReader;
import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.xml.sax.InputSource;

public class Tree {

    private static Document convertStringToXMLDocument(String xmlString) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = null;
        try {

            builder = factory.newDocumentBuilder();

            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Directory xmlToDoc(Node doc) {
        Directory Dir;
        Element dir = (Element) doc;

        Dir = new Directory(dir.getAttribute("name"));
        if (!(doc.getFirstChild() == null)) {

            NodeList nList = doc.getChildNodes();
            for (int i = 0; i < nList.getLength(); i++) {
                if (nList.item(i).getNodeName() != "directory") {
                    Element e = (Element) nList.item(i);
                    Files f1 = new Files(e.getAttribute("name"));
                    Dir.add(f1);

                } else {
                    Dir.add(xmlToDoc(nList.item(i)));
                }

            }
        }
        return Dir;

    }

    public static Directory xmlToDoc(String xml) {
        Directory Dir;
        Document doc = convertStringToXMLDocument(xml);
        Element dir = (Element) doc.getFirstChild();
        Dir = new Directory(dir.getAttribute("name"));
        NodeList nList = doc.getFirstChild().getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName() != "directory") {
                Element e = (Element) nList.item(i);
                Files f1 = new Files(e.getAttribute("name"));
                Dir.add(f1);

            } else {
                // Element e = (Element) nList.item(i);
                Dir.add(xmlToDoc(nList.item(i)));
            }

        }
        return Dir;
    }

    public static String pathToXML(File folder) {
        String xml = "";
        xml = xml + "<directory name='" + folder.getName() + "'>";
        for (File file : folder.listFiles()) {
            if (!file.isDirectory()) {
                xml = xml + "<file name='" + file.getName() + "'/>";
            } else {
                xml = xml + pathToXML(file);
            }
        }
        xml = xml + "</directory>";

        return xml;
    }

    public static void main(String[] args) {
        String path ="/Users/thierno/Documents/tree";
        File folder = new File(path);
        System.out.println("\nPath: "+path+"\n");
        System.out.println("\nXML:\n");
        System.out.println(pathToXML(folder));
        System.out.println("\n\n");
        Directory Dir = xmlToDoc(pathToXML(folder));
        System.out.println("Tree:\n");
        Dir.show();
        System.out.println("\n");

    }
}