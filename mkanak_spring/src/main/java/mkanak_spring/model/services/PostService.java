package mkanak_spring.model.services;

import mkanak_spring.model.entities.Post;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;

public interface PostService {
    void createPost(JSONObject post) throws ParseException;
    List<Post> getHomepagePosts(JSONObject preference,int pageNum,int pageSize) ;
    List<Post> getSavedPosts(int id, JSONObject preference,int pageNum,int pageSize);
    List<Post> getProfilePosts(int targetUserID, JSONObject preferences, int pageNum,int pageSize);
    List<Long> getSavedPostsIDs(int userID);
    void addToSavedPosts(JSONObject entry);
    void removeFromSaved(JSONObject entry);
    void deletePost(JSONObject details);
    void editPost(JSONObject post);
    JSONObject getPostDetails(long postID);
}
