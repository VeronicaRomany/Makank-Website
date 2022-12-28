package mkanak_spring.model.services;

import mkanak_spring.model.entities.Post;
import mkanak_spring.model.entities.Property;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;
import java.util.Optional;

public interface PostService {
    void savePost(JSONObject post) throws ParseException;
    List<Post> getHomepagePosts(JSONObject preference,int pageNum,int pageSize) ;
    List<Post> getSavedPosts(int id, JSONObject preference,int pageNum,int pageSize);
    List<Post> getProfilePosts(int targetUserID, JSONObject preferences, int pageNum,int pageSize);
    List<Long> getSavedPostsIDs(int userID);
    void addToSavedPosts(JSONObject entry);
    void removeFromSaved(JSONObject entry);
    void deletePost(long postID);
    void editPost(JSONObject post);
    JSONObject getPostDetails(long postID);
    Optional<Property> getProperty(long propertyID);
}
