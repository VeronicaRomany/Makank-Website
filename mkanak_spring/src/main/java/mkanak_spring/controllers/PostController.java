package mkanak_spring.controllers;

import mkanak_spring.model.Post;
import mkanak_spring.model.PostManager;
import mkanak_spring.model.ViewingPreference;
import mkanak_spring.model.services.PostService;
import mkanak_spring.model.services.UserService;
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
    @Autowired
    PostService postService;

    PostManager app = new PostManager();
    @GetMapping("/{targetUserID}")
    public List<Post> getPersonPosts(@PathVariable int targetUserID, @RequestParam ViewingPreference preferences){
            return app.getPersonPosts(targetUserID,preferences);
    }

    @GetMapping("/homepage")
    public List<Post> getHomePage(@RequestParam String preference){
        Gson gson = new Gson();
        System.out.println("GOT A REQUEST >> "+preference);
//
        ViewingPreference p= gson.fromJson(preference,ViewingPreference.class);
//        ViewingPreference p= new ViewingPreference();
        return postService.getAllPosts();
    }

    @PostMapping("/new")
    public void addPost(@RequestBody JSONObject postDetails) throws ParseException {
        postService.savePost(postDetails);
    }

    @GetMapping("/saved/{targetUserID}")
    public List<Post> getSavedPosts(@PathVariable int userID,@RequestBody ViewingPreference preferences){
        return app.getPersonPosts(userID,preferences);
    }

/*    @PutMapping("/edit")
    public boolean editPost(@RequestParam int userID, @RequestBody Post post){
        return app.editPost(userID,post);
    }

 */

//    @PostMapping("/savePost")
    public boolean addToSavedPost(int userID, int postID){
        return app.addToSavedPost(userID, postID);
    }

    public boolean removePostFromSaved(int userID, int postID){
        return app.removePostFromSaved(userID, postID);
    }

    @GetMapping("/details/{postID}")
    public JSONObject getPostDetails(@PathVariable int postID){
        return postService.getPostDetails(postID);
    }
}
