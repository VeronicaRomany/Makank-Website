package mkanak_spring.controllers;

import mkanak_spring.model.FilterPreference;
import mkanak_spring.model.SortingPreference;
import mkanak_spring.model.ViewingPreference;
import mkanak_spring.model.entities.Post;
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
    public List<Post> getHomePage(@RequestParam String preference,
                                  @RequestParam(name = "pageNum",defaultValue = "0") int pageNum,
                                  @RequestParam(name = "pageSize",defaultValue = "20") int pageSize)
            throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(preference);
        return postService.getHomepagePosts(json,pageNum,pageSize);
    }


    //    ################ Profile posts ########################
    @GetMapping("/{targetUserID}")
    public List<Post> getProfilePosts(@PathVariable int targetUserID,
                                      @RequestParam String preference,
                                      @RequestParam(name = "pageNum",defaultValue = "0") int pageNum,
                                      @RequestParam(name = "pageSize",defaultValue = "20") int pageSize
                                      ) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(preference);
        return postService.getProfilePosts(targetUserID,json,pageNum,pageSize);
    }




    //   ################ Manipulation posts ########################
    @PostMapping("/new")
    public void addPost(@RequestBody JSONObject postDetails) throws ParseException {
        System.out.println("details: " + postDetails);
        postService.savePost(postDetails);
    }

    @PutMapping("/edit")
    public boolean editPost(@RequestParam int userID,
                            @RequestBody String post)
            throws ParseException {

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
    public List<Post> getSavedPosts(@PathVariable int userID,
                                    @RequestParam String preference,
                                    @RequestParam(name = "pageNum",defaultValue = "0") int pageNum,
                                    @RequestParam(name = "pageSize",defaultValue = "20") int pageSize
                                    ) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(preference);
        return postService.getSavedPosts(userID,json,pageNum,pageSize);
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
