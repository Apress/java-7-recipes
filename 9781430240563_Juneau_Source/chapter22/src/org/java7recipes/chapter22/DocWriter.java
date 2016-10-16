package org.java7recipes.chapter22;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author John O'Conner (john@joconner.com)
 */
public class DocWriter {

    public void run(String outputFile) throws FileNotFoundException, XMLStreamException, IOException {
        Patient[] patients = new Patient[3];
        patients[0].setId(BigInteger.valueOf(1));
        patients[0].setName("John Smith");
        patients[0].setDiagnosis("Common Cold");
        patients[1].setId(BigInteger.valueOf(2));
        patients[1].setName("Jane Doe");
        patients[1].setDiagnosis("Broken Ankle");
        patients[2].setId(BigInteger.valueOf(3));
        patients[2].setName("Jack Brown");
        patients[2].setDiagnosis("Food Allergy");
        XMLOutputFactory factory = XMLOutputFactory.newFactory();
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            XMLStreamWriter writer = factory.createXMLStreamWriter(fos, "UTF-8");
            writer.writeStartDocument();
            writer.writeCharacters("\n");
            writer.writeStartElement("patients");
            writer.writeCharacters("\n");
            for (Patient p : patients) {
                writer.writeCharacters("\t");
                writer.writeStartElement("patient");
                writer.writeAttribute("id", String.valueOf(p.getId()));
                writer.writeCharacters("\n\t\t");
                writer.writeStartElement("name");
                writer.writeCharacters(p.getName());
                writer.writeEndElement();
                writer.writeCharacters("\n\t\t");
                writer.writeStartElement("diagnosis");
                writer.writeCharacters(p.getDiagnosis());
                writer.writeEndElement();
                writer.writeCharacters("\n\t");
                writer.writeEndElement();
                writer.writeCharacters("\n");
            }
            writer.writeEndElement();
            writer.writeEndDocument();
            writer.close();
        }

    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.printf("Usage: java org.java7recipes.chapter22.DocWriter <outputXmlFile>\n");
            System.exit(1);
        }
        DocWriter app = new DocWriter();
        try {
            app.run(args[0]);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DocWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLStreamException ex) {
            Logger.getLogger(DocWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DocWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
