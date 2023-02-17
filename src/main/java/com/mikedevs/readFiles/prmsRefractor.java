package com.mikedevs.readFiles;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class prmsRefractor {
    public static void main(String[] args){
        String fileName = "C:\\Users\\63259\\Desktop\\prms nucleo\\PRMS Insumos\\BestDeal\\PRM10MON50SWX220203102535.xml";

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try (InputStream is = new FileInputStream(fileName)){

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(is);

            NodeList nodeList = doc.getElementsByTagName("HEADER");

            for (int i = 0; i <= nodeList.getLength(); i++){
                System.out.println(nodeList.item(i).getNodeName() + ": " + nodeList.item(i).getTextContent());
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
