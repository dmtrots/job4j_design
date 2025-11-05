package ru.job4j.serialization.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        Person person = new Person(false, 30, new Contact("11-111"), "Worker", "Married");
        JAXBContext context = JAXBContext.newInstance(Person.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(person, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Person result = (Person) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

        Device device = new Device(true, 999.99, "Galaxy Ultra", new Specification("Snapdragon 888", 8, 256), new int[] {800, 1800, 2600});
        JAXBContext secondContext = JAXBContext.newInstance(Device.class);
        Marshaller secondMarshaller = secondContext.createMarshaller();
        secondMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String secondXML = "";
        try (StringWriter writer = new StringWriter()) {
            secondMarshaller.marshal(device, writer);
            secondXML = writer.getBuffer().toString();
            System.out.println(secondXML);
        }
       Unmarshaller secondUnmarshaller = secondContext.createUnmarshaller();
        try (StringReader reader = new StringReader(secondXML)) {
            Device result = (Device) secondUnmarshaller.unmarshal(reader);
            System.out.println(result);
        }

    }
}
