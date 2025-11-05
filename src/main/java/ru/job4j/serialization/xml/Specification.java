package ru.job4j.serialization.xml;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "specification")
public class Specification {

    @XmlAttribute
    private String processor;

    @XmlAttribute
    private int ram;

    @XmlAttribute
    private int storage;

    public Specification() { }

    public Specification(String processor, int ram, int storage) {
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
    }

    @Override
    public String toString() {
        return "Specification{"
                + "processor='" + processor + '\''
                + ", ramGb=" + ram
                + ", storageGb=" + storage
                + '}';
    }
}
