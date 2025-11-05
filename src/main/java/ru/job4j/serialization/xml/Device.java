package ru.job4j.serialization.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "device")
@XmlAccessorType(XmlAccessType.FIELD)
public class Device {

    @XmlAttribute
    private boolean isOn;

    @XmlAttribute
    private double price;

    @XmlAttribute
    private String model;
    private Specification specification;

    @XmlElementWrapper(name = "supportedFrequencies")
    @XmlElement(name = "supportedFrequency")
    private int[] supportedFrequencies;

    public Device() { }

    public Device(boolean isOn, double price, String model, Specification specification, int[] supportedFrequencies) {
        this.isOn = isOn;
        this.price = price;
        this.model = model;
        this.specification = specification;
        this.supportedFrequencies = supportedFrequencies;
    }

    @Override
    public String toString() {
        return "Device{"
                + "isOn=" + isOn
                + ", price=" + price
                + ", model='" + model + '\''
                + ", specification=" + specification
                + ", supportedFrequencies=" + java.util.Arrays.toString(supportedFrequencies)
                + '}';
    }
}
