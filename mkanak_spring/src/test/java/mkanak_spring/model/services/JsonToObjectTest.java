package mkanak_spring.model.services;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonToObjectTest {
    static JsonToObject jsonToObject = new JsonToObject();
    @Test
    void throwNullPointerExceptionOnPropertyPicturesTest() {
        JSONObject object = new JSONObject();
        assertThrows(NullPointerException.class, () -> jsonToObject.buildPropertyPictures(object, 0L),
                "Passing a null object should throw an exception");
    }
    @Test
    void throwClassCastExceptionOnPropertyPicturesTest() {
        JSONObject object = new JSONObject();
        object.put("pictures", 0);
        assertThrows(ClassCastException.class, () -> jsonToObject.buildPropertyPictures(object, 0L),
                "Passing an invalid object class should throw an exception");
    }

    @Test
    void getUserFromJson() {
        JSONObject json = new JSONObject();
        json = jsonUser();
        json.remove("profile_pic_link");
        json.put("profile_pic_link", 1);
        JSONObject finalJson = json;
        assertThrows(ClassCastException.class, () -> jsonToObject.getUserFromJson(finalJson),
                "Passing an invalid object class should throw an exception");
    }

    @Test
    void buildApartment() {
        JSONObject apartment = new JSONObject();
        apartment = jsonApartment();
        apartment.remove("studentHousing");
        JSONObject finalApartment = apartment;
        assertThrows(NullPointerException.class, () -> jsonToObject.buildApartment(finalApartment),
                "Passing a null object class should throw an exception");
    }


    JSONObject jsonUser() {
        JSONObject json = new JSONObject();
        json.put("userID", 1L);
        json.put("address", "add");
        json.put("username", "yara");
        json.put("name", "yaraaaa");
        json.put("descritpion", "hi");
        json.put("password", "password");
        json.put("email", "yara@gmail.com");
        json.put("profile_pic_link", "null.jpg");
        json.put("phone_number", "01234567890");

        return json;
    }

    JSONObject jsonApartment() {
        JSONObject json = new JSONObject();
        json.put("address", "alex");
        json.put("type", "apartment");
        json.put("area", 52);
        json.put("roomNumber", 3);
        json.put("bathroomNumber", 2);
        json.put("city", "alex");
        json.put("info", "nnn");
        json.put("rent", true);
        json.put("price", 1000);
        json.put("sellerID", 1L);
        json.put("level", 5);
        json.put("elevator", true);
        json.put("studentHousing", true);
        return json;
    }
}