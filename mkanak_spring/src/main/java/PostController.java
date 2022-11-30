import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/posts")
public class PostController {
    PostManager app = new PostManager();
    @GetMapping("/{targetUserID}")
    List<Post> getPersonPosts(@PathVariable int targetUserID, @RequestParam ViewingPreferences preferences){
            return app.getPersonPosts(targetUserID,preferences);
    }

    @GetMapping("/homepage")
    List<Post> getHomePage(@RequestParam ViewingPreferences preferences){
        return app.getHomePage(preferences);
    }

    @GetMapping("/saved/{targetUserID}")
    List<Post> getSavedPosts(@PathVariable int userID,@RequestParam ViewingPreferences preferences){
        return app.getPersonPosts(userID,preferences);
    }

    @PutMapping("/edit")
    boolean editPost(@RequestParam int userID, @RequestBody Post post){
        return app.editPost(userID,post);
    }

    @PostMapping("/")
    boolean addToSavedPost(int userID, int postID){
        return app.addToSavedPost(userID, postID);
    }

    boolean removePostFromSaved(int userID, int postID){
        return app.removePostFromSaved(userID, postID);
    }

}
