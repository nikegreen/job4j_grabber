package ru.job4j.design.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/**
 * Нужно добавить зависимости в pom.xml
 * <dependency>
 *     <groupId>javax.xml.bind</groupId>
 *     <artifactId>jaxb-api</artifactId>
 *     <version>2.3.1</version>
 * </dependency>
 * <dependency>
 *     <groupId>javax.activation</groupId>
 *     <artifactId>activation</artifactId>
 *     <version>1.1.1</version>
 * </dependency>
 * <dependency>
 *     <groupId>org.glassfish.jaxb</groupId>
 *     <artifactId>jaxb-runtime</artifactId>
 *     <version>2.3.1</version>
 * </dependency>
 */
public class ReportBuilderXml implements  ReportBuilder {
     @Override
    public String getHeader() {
        return "";
    }

    @Override
    public String getBody(List<Employee> employees) {
        String xml = "";
        try {
            JAXBContext context = JAXBContext.newInstance(Employeers.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(new Employeers(employees), writer);
                xml = writer.getBuffer().toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xml;
    }

    @Override
    public String getFooter() {
        return "";
    }
}
