package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.serialization.java.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainJSON {
    public static void main(String[] args) {

        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");

        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);

        final Person person = new Person(false, 30, new Contact(555555, "11-111"),  new String[] {"Worker", "Married"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", person.getSex());
        jsonObject.put("age", person.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);

        System.out.println(jsonObject.toString());

        System.out.println(new JSONObject(person).toString());

        System.out.println();

        JSONObject jsonSpecification = new JSONObject("{\"processor\":\"Snapdragon 888\", \"ram\":\"12\", \"storage\":\"256\"}");

        List<String> secondList = new ArrayList<>();
        secondList.add("800");
        secondList.add("1800");
        JSONArray jsonSupportedFrequencies = new JSONArray(secondList);

        final Device device = new Device(true, 999.99, "iPhone", new Device.Specification("A18",  8, 256),  new int[] {800, 1800});
        JSONObject secondJSONObject = new JSONObject();
        secondJSONObject.put("isOn", device.isOn());
        secondJSONObject.put("price", device.getPrice());
        secondJSONObject.put("model", device.getModel());
        secondJSONObject.put("specification", jsonSpecification);
        secondJSONObject.put("supported frequencies", jsonSupportedFrequencies);

        System.out.println(secondJSONObject.toString());

        System.out.println(new JSONObject(device).toString());
    }
}
