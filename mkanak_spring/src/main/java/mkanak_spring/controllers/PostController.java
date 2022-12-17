package mkanak_spring.controllers;

import mkanak_spring.model.Post;
import mkanak_spring.model.PostManager;
import mkanak_spring.model.ViewingPreference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.google.gson.Gson;

@CrossOrigin
@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired private PostManager app;
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

    @GetMapping("/saved/{targetUserID}")
    public List<Post> getSavedPosts(@PathVariable int userID,@RequestBody ViewingPreference preferences){
        return app.getPersonPosts(userID,preferences);
    }

    @GetMapping("/saved/ids/{userID}")
    public List<Long> getSavedIDs(@PathVariable int userID){
        return app.getSavedIDs(userID);
    }


//    @PutMapping("/edit")
    public boolean editPost(@RequestParam int userID, @RequestBody Post post){
        return app.editPost(userID,post);
    }

//    @PostMapping("/savePost")
    public boolean addToSavedPost(int userID, int postID){
        return app.addToSavedPost(userID, postID);
    }

    public boolean removePostFromSaved(int userID, int postID){
        return app.removePostFromSaved(userID, postID);
    }

}
