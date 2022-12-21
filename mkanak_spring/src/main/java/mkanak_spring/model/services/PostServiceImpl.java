package mkanak_spring.model.services;

import mkanak_spring.model.*;
import mkanak_spring.model.dao.PostDAO;
import mkanak_spring.model.dao.PostRepo;
import mkanak_spring.model.entities.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PostServiceImpl implements PostService{
    @Autowired
    PostDAO postDAO;
    @Autowired
    JsonToObjectConverter converter;

    @Override
    public void savePost(JSONObject post) throws ParseException {
        System.out.println("Type: " + post.get("type"));
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(post.toString());
        JSONArray pictures = (JSONArray) object.get("pictures");
        if(post.get("type").toString().compareTo("villa") == 0) {
            Villa property = new Villa();
            property = converter.buildVilla(post);
            property.setHasPictures(pictures.size() != 0);
            postDAO.saveVilla(property);
            List<PropertyPicture> pictureList = converter.buildPropertyPictures(post, property.getPropertyID());
            postDAO.saveAllPictures(pictureList);
        }
        else {
            Apartment property = new Apartment();
            property = converter.buildApartment(post);
            property.setHasPictures(pictures.size() != 0);
            postDAO.saveApartment(property);
            List<PropertyPicture> pictureList = converter.buildPropertyPictures(post, property.getPropertyID());
            postDAO.saveAllPictures(pictureList);
        }
    }

    @Override
    public List<Post> getHomepagePosts(JSONObject preference) {
        ViewingPreference p = converter.parseViewingPreference(preference);
        return postDAO.getAllPosts(p);
    }

    @Override
    public List<Post> getSavedPosts(int id, JSONObject preference) {
        ViewingPreference v = converter.parseViewingPreference(preference);
        return postDAO.getSavedPostsByUserID(v,id);
    }

    @Override
    public List<Post> getProfilePosts(int targetUserID, JSONObject preferences) {
        ViewingPreference v = converter.parseViewingPreference(preferences);
        return postDAO.getPostsByUser(v,targetUserID);
    }

    @Override
    public List<Long> getSavedPostsIDs(int userID) {
        return postDAO.getSavedPostsIDs(userID);
    }

    @Override
    public void addToSavedPosts(JSONObject saveEntry) {
        int userID = (int) saveEntry.get("userID");
        int postID = (int) saveEntry.get("postID");
        postDAO.addToSavedPosts(userID,postID);
    }

    @Override
    public void removeFromSaved(JSONObject entry) {
        int userID = (int) entry.get("userID");
        int postID = (int) entry.get("postID");
        postDAO.removeFromSavedPosts(userID,postID);
    }

    @Override
    public void deletePost(JSONObject details) {

    }

    @Override
    public void editPost(JSONObject post) {

    }


    private List<Post> getDummyPosts(ViewingPreference preference){
        List<Post> dummyContent= new ArrayList<>();
        for(int i=0;i<5;i++) {
             dummyContent.add(getDummyPost());
        }
        return dummyContent;
    }






    private Post getDummyPost(){
        Apartment x = new Apartment();
        x.setSellerID(5);
        x.setAddress("22nd 45street");
        String[] pics = new String[5];
        pics[0] = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTYI7wP8BBfh928fLg3Ui5Slj7ROZW_zc3rag&usqp=CAU";
        x.setHasPictures(true);
        x.setArea(250);
        x.setInfo("for sale owner is moving to Banha");
        x.setType("villa");
        x.setRoomNumber(3);
        x.setBathroomNumber(4);
        x.setCity("Alexandria, Egypt");
        x.setRent(false);
        x.setElevator(true);
        return x;
    }





    @Override
    public JSONObject getPostDetails(long postID) {
        return postDAO.getPostDetails(postID);
    }
}
