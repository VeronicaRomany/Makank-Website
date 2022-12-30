package mkanak_spring.model.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mkanak_spring.model.ViewingPreference;
import mkanak_spring.model.entities.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JsonToObjectConverter {
    public ViewingPreference parseViewingPreference(JSONObject json){
        ObjectMapper m = new ObjectMapper();
        ViewingPreference v=null;
        try {
            v= m.readValue(json.toString(),ViewingPreference.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return v;
    }



    public void buildPost(Property property, JSONObject post) {
        property.setAddress((String) post.get("address"));
        property.setType((String) post.get("type"));
        property.setArea((Integer) post.get("area"));
        property.setRoomNumber((Integer) post.get("roomNumber"));
        property.setBathroomNumber((Integer) post.get("bathroomNumber"));
        property.setCity((String) post.get("city"));
        property.setInfo((String) post.get("info"));
        property.setRent((boolean) post.get("rent"));
        property.setPrice((Integer) post.get("price"));
        property.setSellerID(((Number) post.get("sellerID")).longValue());
    }

    public Apartment buildApartment(JSONObject property) {
        Apartment apartment = new Apartment();
        buildPost(apartment, property);
        apartment.setLevel((Integer) property.get("level"));
        apartment.setElevator((boolean) property.get("elevator"));
        apartment.setStudentHousing((boolean) property.get("studentHousing"));
        return apartment;
    }

    public Villa buildVilla(JSONObject property) {
        Villa villa = new Villa();
        buildPost(villa, property);
        villa.setNumberOfLevels((Integer) property.get("level"));
        villa.setHasGarden((boolean) property.get("hasGarden"));
        villa.setHasPool((boolean) property.get("hasPool"));
        return villa;
    }

    public List<PropertyPicture> buildPropertyPictures(JSONObject property, long postID) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(property.toString());
        JSONArray pictures = (JSONArray) object.get("pictures");
        List<PropertyPicture> pictureList = new ArrayList<>();
        for (Object picture : pictures) {
            String picLink = (String) picture;
            PropertyPicture propertyPicture = new PropertyPicture(postID, picLink);
            pictureList.add(propertyPicture);
        }
        return pictureList;
    }
}
