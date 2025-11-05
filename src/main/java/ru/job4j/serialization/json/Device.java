package ru.job4j.serialization.json;

public class Device {
    private final boolean isOn;
    private final double price;
    private final String model;
    private final Specification specification;
    private final int[] supportedFrequencies;

    public boolean isOn() {
        return isOn;
    }

    public double getPrice() {
        return price;
    }

    public String getModel() {
        return model;
    }

    public Specification getSpecification() {
        return specification;
    }

    public int[] getSupportedFrequencies() {
        return supportedFrequencies;
    }

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

    public static class Specification {
        private final String processor;
        private final int ram;
        private final int storage;

        public String getProcessor() {
            return processor;
        }

        public int getRam() {
            return ram;
        }

        public int getStorage() {
            return storage;
        }

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
}