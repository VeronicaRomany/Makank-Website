package mkanak_spring.model;

import mkanak_spring.model.dao.SavedPostsDAO;
import mkanak_spring.model.entities.*;
import mkanak_spring.model.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;


@Service
public class PostManager {
    @Autowired SavedPostsDAO dao;
    @Autowired PostService service;

    public List<Post> getPersonPosts(int targetUserID, ViewingPreference preferences) {
        return null;
    }
    public List<Post> getHomePage(ViewingPreference preferences){
        List<Post> homePageContent= new ArrayList<>();
        for(int i=0;i<5;i++) {
            // homePageContent.add(getDummyPost());
        }
        return homePageContent;
    }

    public List<Post> getSavedPosts(int userID, ViewingPreference preferences){
        return null;
    }

    public List<Long> getSavedIDs(int userID){
        return dao.getSavedIDs((long) userID);
    }

    public boolean editPost(int userID, Post post){
        return false;
    }

    public boolean addToSavedPost(int userID, int postID){
        dao.addSavedPost((long) postID, (long) userID);
        return false;
    }

    public boolean removePostFromSaved(int userID, int postID){
        return false;
    }




    public List<Property> getSavedByID(int id){
        return service.getSavedProperties(id);
    }





    public Post buildPost(Post property, JSONObject post) {
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
        return property;
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
    /*
    private Post getDummyPost(){
        Apartment x = new Apartment();
        x.sellerID=5;
        x.address="22nd 45street";
        String[] pics = new String[5];
        pics[0] = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTYI7wP8BBfh928fLg3Ui5Slj7ROZW_zc3rag&usqp=CAU";
        x.pictures=pics;
        x.area=250;
        x.info="for sale owner is moving to Banha";
        x.type="villa";
        x.roomNumber=3;
        x.bathroomNumber=4;
        x.city="Alexandria, Egypt";
        x.rent=false;
        x.setElevator(true);

        Post p = new Post();
        p.setPostID(271);
        p.setProperty(x);
        return p;
    }

     */

}
