package mkanak_spring.model.services;

import mkanak_spring.model.*;
import mkanak_spring.model.dao.PostDAO;
import mkanak_spring.model.dao.PropertyRepo;
import mkanak_spring.model.entities.*;
import org.json.simple.JSONObject;
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
        if(post.get("type").toString().compareTo("villa") == 0) {
            Villa property = new Villa();
            property = postManager.buildVilla(post);
            Post newPost = postManager.addPost(property);
            postDAO.savePost(newPost);
            List<PropertyPicture> pictureList = postManager.buildPropertyPictures(post, newPost.getPostID());
            property.setHasPictures(pictureList.size() != 0);
            property.setPost(newPost);
            postDAO.saveVilla(property);
            postDAO.saveAllPictures(pictureList);
        }
        else {
            Apartment property = new Apartment();
            property = postManager.buildApartment(post);
            Post newPost = postManager.addPost(property);
            postDAO.savePost(newPost);
            List<PropertyPicture> pictureList = postManager.buildPropertyPictures(post, newPost.getPostID());
            property.setHasPictures(pictureList.size() != 0);
            property.setPost(newPost);
            postDAO.saveApartment(property);
            postDAO.saveAllPictures(pictureList);
        }
    }

    @Override
    public List<Post> getAllPosts() {
        return postDAO.getAll();
    }

    @Override
    public List<Property> getSavedProperties(long id) {
        return propertyRepo.findAllSavedProperties(id);
    }


}
