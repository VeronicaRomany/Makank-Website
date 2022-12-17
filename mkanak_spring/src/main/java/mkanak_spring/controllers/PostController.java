package mkanak_spring.controllers;

import mkanak_spring.model.entities.Post;
import mkanak_spring.model.PostManager;
import mkanak_spring.model.ViewingPreference;
import mkanak_spring.model.entities.Property;
import mkanak_spring.model.services.PostService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.google.gson.Gson;

@CrossOrigin
@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired private PostManager app;
    @Autowired PostService postService;

    @GetMapping("/{targetUserID}")
    public List<Post> getPersonPosts(@PathVariable int targetUserID, @RequestParam ViewingPreference preferences){
            return app.getPersonPosts(targetUserID,preferences);
    }

    @GetMapping("/homepage")
    public List<Post> getHomePage(@RequestParam String preference){
        Gson gson = new Gson();
        System.out.println("GOT A REQUEST >> "+preference);

        ViewingPreference p= gson.fromJson(preference,ViewingPreference.class);
        return app.getHomePage(p);
    }

    @PostMapping("/new")
    public void addPost(@RequestBody JSONObject postDetails) throws ParseException {
        Post post = new Post();
     //   post = app.addPost(postDetails);
     //   System.out.println(post.getPostID());
     //   System.out.println(post.getPublishDate());
     //   System.out.println(post.getProperty().getAddress());
      //  System.out.println(post.getProperty().getArea());
        postService.savePost(postDetails);
    }

    @GetMapping("/saved/{targetUserID}")
    public List<Property> getSavedPosts(@PathVariable int userID){
        return app.getSavedByID(userID);
    }

    @GetMapping("/saved/ids/{userID}")
    public List<Long> getSavedIDs(@PathVariable int userID){
        return app.getSavedIDs(userID);
    }


//    @PutMapping("/edit")
    public boolean editPost(@RequestParam int userID, @RequestBody Post post){
        return app.editPost(userID,post);
    }

    @PostMapping("/savePost")
    public boolean addToSavedPost(@RequestBody JSONObject saveEntry){
        int userID = (int) saveEntry.get("userID");
        int postID = (int) saveEntry.get("postID");
        return app.addToSavedPost(userID,postID);
    }

    public boolean removePostFromSaved(int userID, int postID){
        return app.removePostFromSaved(userID, postID);
    }

}
