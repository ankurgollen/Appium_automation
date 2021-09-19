package Common;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Common {

    public static AndroidDriver<WebElement> androidDriver;
    public static IOSDriver<WebElement> iOSDriver;
    public static MobileDriver<WebElement> mobileDriver;
    public static WebDriver webDriver;
    public static WebDriverWait wait;
    public static Wait<WebDriver> waitFluent;
    public static String inputFile;
    public String seperator = ",";

    ///The Function to Read XML File using DocumentBuilderFactory
    public static String GetXmlValue(String variablename, String XMLFile) throws IOException, ParserConfigurationException, SAXException {
        String valueOfElement = null;
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(new File(XMLFile));
        doc.getDocumentElement().normalize();
        NodeList listOfElements = doc.getElementsByTagName("Element");
        for (int s = 0; s < listOfElements.getLength(); s++) {
            Node firstElementNode = listOfElements.item(s);
            if (firstElementNode.getNodeType() == Node.ELEMENT_NODE) {
                Element firstNameOfElement = (Element) firstElementNode;
                NodeList NameOfElementList = firstNameOfElement.getElementsByTagName("NameOfElement");
                Element NameOfElement = (Element) NameOfElementList.item(0);
                NodeList textFNList = NameOfElement.getChildNodes();
                String nameOfElementTag = ((Node) textFNList.item(0)).getNodeValue().trim();
                if (nameOfElementTag.equals(variablename)) {
                    NodeList valueList = firstNameOfElement.getElementsByTagName("Value");
                    Element valueElement = (Element) valueList.item(0);
                    NodeList value = valueElement.getChildNodes();
                    valueOfElement = ((Node) value.item(0)).getNodeValue().trim();
                    break;
                }
            }
        }
        return valueOfElement;
    }

    ///<summary>
    ///Description: The Function to Read XML File using DocumentBuilderFactory
    ///</summary>
    public static Map<String, String> GetXmlValueType(String variablename, String XMLFile) throws IOException, ParserConfigurationException, SAXException {
        String valueOfElement = null;
        String elementType = null;
        Map<String, String> data = new HashMap<String, String>();
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(new File(XMLFile));
        doc.getDocumentElement().normalize();
        NodeList listOfElements = doc.getElementsByTagName("Element");
        for (int s = 0; s < listOfElements.getLength(); s++) {
            Node firstElementNode = listOfElements.item(s);
            if (firstElementNode.getNodeType() == Node.ELEMENT_NODE) {
                Element firstNameOfElement = (Element) firstElementNode;
                String NameOfElementList = firstNameOfElement.getAttribute("nameOfElement");

                if (NameOfElementList.equals(variablename)) {
                    valueOfElement = firstNameOfElement.getAttribute("value");
                    data.put("Value", valueOfElement);
                    elementType = firstNameOfElement.getAttribute("type");
                    data.put("Type", elementType);
                    break;
                }
            }
        }
        return data;
    }

    ///<summary>
    ///Description: The Function to Read the CSV File
    ///</summary>
    public String[] ReadCSV(String testCaseId, String separator, String filename) {
        BufferedReader br = null;
        String[] values = null;
        String line = "";

        try {
            File file = new File(filename);
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                if (line.trim().startsWith(testCaseId.trim())) {
                    values = line.split(separator);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return values;
    }

    ///Description: The Function to Read the CSV File with values and columns
    public Map<String, String> ReadCSV(String testCaseId) {
        BufferedReader br = null;
        String[] values = null;
        String[] cols = null;
        Map<String, String> data = new HashMap<String, String>();
        String line = "";

        try {
            File file = new File(inputFile);
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                cols = line.split(seperator);
                break;
            }
            while ((line = br.readLine()) != null) {
                if (line.trim().startsWith(testCaseId.trim())) {
                    values = line.split(seperator);
                }
            }
            for (int i = 0; i < cols.length; i++) {
                data.put(cols[i], values[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }
}


