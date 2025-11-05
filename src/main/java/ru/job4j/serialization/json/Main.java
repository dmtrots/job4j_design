package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.serialization.java.Contact;

public class Main {
    public static void main(String[] args) {
        final Person person = new Person(false, 30, new Contact(555555, "11-111"),
                new String[] {"Worker", "Married"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));

        final String personJson =
                "{"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";
        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);

        final Device device = new Device(true, 999.99, "Galaxy Ultra", new Device.Specification("Snapdragon 888", 12, 256),
                new int[] {800, 1800, 2600});

        final Gson secondGson = new GsonBuilder().create();
        System.out.println(secondGson.toJson(device));

        final String deviceJson =
                "{"
                        + "\"isOn\":true,"
                        + "\"price\":999.99,"
                        + "\"model\":\"Galaxy Ultra\","
                        + "\"specification\":"
                        + "{"
                        + "\"processor\":\"Snapdragon 888\","
                        + "\"ram\":8,"
                        + "\"storage\":256"
                        + "},"
                        + "\"supportedFrequencies\":[800,1800,2600]"
                        + "}";
        final Device deviceMod = secondGson.fromJson(deviceJson, Device.class);
        System.out.println(deviceMod);
    }
}
