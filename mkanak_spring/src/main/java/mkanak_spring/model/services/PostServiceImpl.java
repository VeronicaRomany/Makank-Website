package mkanak_spring.model.services;

import mkanak_spring.model.*;
import mkanak_spring.model.dao.PostDAO;
import mkanak_spring.model.dao.PropertyRepo;
import mkanak_spring.model.entities.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class PostServiceImpl implements PostService{
    @Autowired
    PostDAO postDAO;
    PropertyRepo propertyRepo;

    @Override
    public void savePost(JSONObject post) throws ParseException {
        PostManager postManager = new PostManager();
        System.out.println("Type: " + post.get("type"));
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(post.toString());
        JSONArray pictures = (JSONArray) object.get("pictures");
        if(post.get("type").toString().compareTo("villa") == 0) {
            Villa property = new Villa();
            property = postManager.buildVilla(post);
            property.setHasPictures(pictures.size() != 0);
            postDAO.saveVilla(property);
            List<PropertyPicture> pictureList = postManager.buildPropertyPictures(post, property.getPropertyID());
            postDAO.saveAllPictures(pictureList);
        }
        else {
            Apartment property = new Apartment();
            property = postManager.buildApartment(post);
            property.setHasPictures(pictures.size() != 0);
            postDAO.saveApartment(property);
            List<PropertyPicture> pictureList = postManager.buildPropertyPictures(post, property.getPropertyID());
            postDAO.saveAllPictures(pictureList);
        }
    }

    @Override
    public List<Post> getAllPosts() {
        return postDAO.getAllPosts();
    }

    @Override
    public List<Property> getSavedProperties(long id) {
        return propertyRepo.findAllSavedProperties(id);
    }


}
