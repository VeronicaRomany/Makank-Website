package mkanak_spring.controllers;

import mkanak_spring.model.FilterPreference;
import mkanak_spring.model.SortingPreference;
import mkanak_spring.model.ViewingPreference;
import mkanak_spring.model.entities.Post;
import mkanak_spring.model.services.PostService;
import org.json.simple.JSONObject;
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
    public List<Post> getHomePage(JSONObject preferences){
        return postService.getHomepagePosts(preferences);
    }


    //    ################ Profile posts ########################
    @GetMapping("/{targetUserID}")
    public List<Post> getProfilePosts(@PathVariable int targetUserID, @RequestParam JSONObject preferences){
        return postService.getProfilePosts(targetUserID,preferences);
    }




    //   ################ Manipulation posts ########################
    @PostMapping("/new")
    public void addPost(@RequestBody JSONObject postDetails) throws ParseException {
        postService.savePost(postDetails);
    }

    @PutMapping("/edit")
    public boolean editPost(@RequestParam int userID, @RequestBody JSONObject post){
        //verification steps
        postService.editPost(post);
        return false;
    }

    @PostMapping("/delete")
    public boolean deletePost(@RequestBody JSONObject details){
        postService.deletePost(details);
        return false;
    }


    // ###################### Saved Posts ####################################
    @GetMapping("/saved/{targetUserID}")
    public List<Post> getSavedPosts(@PathVariable int userID){
        return postService.getSavedPosts(userID,null);
    }

    @GetMapping("/saved/ids/{userID}")
    public List<Long> getSavedIDs(@PathVariable int userID){
        return postService.getSavedPostsIDs(userID);
    }


    @PostMapping("/savePost")
    public void addToSavedPost(@RequestBody JSONObject saveEntry){
        postService.addToSavedPosts(saveEntry);
    }

    @PostMapping("/unsavePost")
    public void removePostFromSaved(JSONObject entry){
        postService.removeFromSaved(entry);
    }

}
