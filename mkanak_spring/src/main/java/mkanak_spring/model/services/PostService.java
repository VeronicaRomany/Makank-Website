package mkanak_spring.model.services;

import mkanak_spring.model.Post;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
    void savePost(JSONObject post) throws ParseException;
    List<Post> getAllPosts();
    JSONObject getPostDetails(long postID);
}
