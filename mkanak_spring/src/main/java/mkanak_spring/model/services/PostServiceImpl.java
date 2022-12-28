package mkanak_spring.model.services;

import mkanak_spring.model.*;
import mkanak_spring.model.dao.PostDAO;
import mkanak_spring.model.entities.*;

import mkanak_spring.model.repositories.ApartmentRepo;
import mkanak_spring.model.repositories.PropertyRepo;
import mkanak_spring.model.repositories.VillaRepo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    PostDAO postDAO;
    @Autowired
    JsonToObjectConverter converter;
    @Autowired
    ApartmentRepo apartmentRepo;
    @Autowired
    VillaRepo villaRepo;
    @Autowired
    PropertyRepo propertyRepo;
    @Override
    public void savePost(JSONObject post) throws ParseException {
        JsonToObjectConverter converter = new JsonToObjectConverter();
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
    public List<Post> getHomepagePosts(JSONObject preference,int pageNum,int pageSize) {
        ViewingPreference p = converter.parseViewingPreference(preference);
        return postDAO.getAllPosts(p,pageNum,pageSize);
    }

    @Override
    public List<Post> getSavedPosts(int id, JSONObject preference,int pageNum,int pageSize) {
        ViewingPreference v = converter.parseViewingPreference(preference);
        return postDAO.getSavedPostsByUserID(id,v,pageNum,pageSize);
    }

    @Override
    public List<Post> getProfilePosts(int targetUserID, JSONObject preferences,int pageNum,int pageSize) {
        ViewingPreference v = converter.parseViewingPreference(preferences);
        return postDAO.getPostsByUser(targetUserID,v,pageNum,pageSize);
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
    public void deletePost(long postID) {
       // long postID = (Integer) details.get("postID");
        postDAO.deletePost(postID);
    }

    @Override
    public void editPost(JSONObject post) {
        //TODO
    }

    @Override
    public JSONObject getPostDetails(long postID) {
        return postDAO.getPostDetails(postID);
    }

    @Override
    public Optional<Property> getProperty(long propertyID) {

        return propertyRepo.findById(propertyID);
    }

}
