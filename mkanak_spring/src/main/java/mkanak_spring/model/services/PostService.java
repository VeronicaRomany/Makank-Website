package mkanak_spring.model.services;

import mkanak_spring.model.entities.Post;
import mkanak_spring.model.entities.Property;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;

public interface PostService {
    void savePost(JSONObject post) throws ParseException;
    List<Post> getAllPosts();
    List<Property> getSavedProperties(long id);

}
