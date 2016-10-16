package org.java7recipes.chapter22;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
/**
 *
 * @author John O'Conner (john@joconner.com)
 */
public class TransformXml {
    
    
    public void run(String xmlFile, String xslFile, String outputFile) throws FileNotFoundException, TransformerConfigurationException, TransformerException {
        InputStream xslInputStream = new FileInputStream(xslFile);
        Source xslSource = new StreamSource(xslInputStream);
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(xslSource);
        InputStream xmlInputStream = new FileInputStream(xmlFile);
        StreamSource in = new StreamSource(xmlInputStream);
        StreamResult out = new StreamResult(outputFile);
        transformer.transform(in, out);
        
    }
    
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.printf("Usage: java org.java7recipes.chapter22.TransformXml <xmlFile> <xslFile> <outputFile>\n");
            System.exit(1);
        }
        TransformXml app = new TransformXml();
        try {
            app.run(args[0], args[1], args[2]);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (TransformerConfigurationException ex) {
            ex.printStackTrace();
        } catch (TransformerException ex) {
            ex.printStackTrace();
        }
    }
}
