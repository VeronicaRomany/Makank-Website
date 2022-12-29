package mkanak_spring.controllers;

import com.google.gson.Gson;
import mkanak_spring.model.FilterPreference;
import mkanak_spring.model.SortingPreference;
import mkanak_spring.model.ViewingPreference;
import mkanak_spring.model.entities.Post;
import mkanak_spring.model.entities.User;
import mkanak_spring.model.services.PostService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired PostService postService;

    //   ################## HOME PAGE ########################
    @GetMapping("/homepage")
    public List<Post> getHomePage(@RequestParam String preference) throws ParseException {
        JSONParser parser = new JSONParser();

//        System.out.println("request received <<<<<< "+preference);
        JSONObject json = (JSONObject) parser.parse(preference);
        return postService.getHomepagePosts(json);
    }


    //    ################ Profile posts ########################
    @GetMapping("userPost/{userID}")
    public List<Post> getProfilePosts(@PathVariable int userID, @RequestParam String preference) throws ParseException {
        System.out.println("tdyresersd");
        System.out.println("postUserID" + userID);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(preference);
        return postService.getProfilePosts(userID,json);
    }

    //   ################ Manipulation posts ########################
    @PostMapping("/new")
    public void addPost(@RequestBody JSONObject postDetails) throws ParseException {
        System.out.println("details: " + postDetails);
        postService.savePost(postDetails);
    }

    @PutMapping("/edit")
    public boolean editPost(@RequestParam int userID, @RequestBody String post) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(post);
        postService.editPost(json);
        return false;
    }

    @PostMapping("/delete")
    public boolean deletePost(@RequestBody String details) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(details);
        postService.deletePost(json);
        return false;
    }


    // ###################### Saved Posts ####################################
    @GetMapping("/saved/{userID}")
    public List<Post> getSavedPosts(@PathVariable int userID,@RequestParam String preference) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(preference);
        return postService.getSavedPosts(userID,json);
    }

    @GetMapping("/saved/ids/{userID}")
    public List<Long> getSavedIDs(@PathVariable int userID){
        return postService.getSavedPostsIDs(userID);
    }


    @PostMapping("/savePost")
    public void addToSavedPost(@RequestBody String saveEntry) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(saveEntry);
        postService.addToSavedPosts(json);
    }

    @PostMapping("/unsavePost")
    public void removePostFromSaved(@RequestBody String entry) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(entry);
        postService.removeFromSaved(json);
    }

    @GetMapping("/details/{postID}")
    public JSONObject getPostDetails(@PathVariable int postID){
        return postService.getPostDetails(postID);
    }

}
