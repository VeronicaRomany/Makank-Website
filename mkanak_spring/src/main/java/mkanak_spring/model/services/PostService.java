package mkanak_spring.model.services;

import mkanak_spring.model.entities.Post;
import mkanak_spring.model.entities.Property;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;
import java.util.Optional;

public interface PostService {
    void savePost(Long propertyID, JSONObject post) throws ParseException;
    List<Post> getHomepagePosts(JSONObject preference,int pageNum,int pageSize) ;
    long getHomepagePostsCount(JSONObject preference) ;

    List<Post> getSavedPosts(int id, JSONObject preference,int pageNum,int pageSize);
    List<Post> getProfilePosts(int targetUserID, JSONObject preferences, int pageNum,int pageSize);
    void addToSavedPosts(JSONObject entry);
    void removeFromSaved(JSONObject entry);
    void editPost(JSONObject post) throws ParseException;
    JSONObject getPostDetails(long postID) throws ParseException;
    JSONObject getProperty(long propertyID) throws ParseException;

    List<Post> getSavedPosts(int id, JSONObject preference,int pageNum,int pageSize);
    List<Long> getSavedPostsIDs(int userID);
    long getSavedPostsCount(int id, JSONObject preferences);

    void addToSavedPosts(JSONObject entry);
    void removeFromSaved(JSONObject entry);

    List<Post> getProfilePosts(int targetUserID, JSONObject preferences, int pageNum,int pageSize);
    long getProfilePostsCount(int targetUserID, JSONObject preferences);

    void deletePost(long postID);
    void editPost(JSONObject post) throws ParseException;


}
