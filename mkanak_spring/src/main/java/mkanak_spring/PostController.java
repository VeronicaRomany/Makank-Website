package mkanak_spring;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/posts")
public class PostController {
    PostManager app = new PostManager();
    @GetMapping("/{targetUserID}")
    List<Post> getPersonPosts(@PathVariable int targetUserID, @RequestParam ViewingPreference preferences){
            return app.getPersonPosts(targetUserID,preferences);
    }

    @GetMapping("/homepage")
    List<Post> getHomePage(@RequestBody ViewingPreference preference){
        return app.getHomePage(preference);
    }

    @GetMapping("/saved/{targetUserID}")
    List<Post> getSavedPosts(@PathVariable int userID,@RequestParam ViewingPreference preferences){
        return app.getPersonPosts(userID,preferences);
    }

    @PutMapping("/edit")
    boolean editPost(@RequestParam int userID, @RequestBody Post post){
        return app.editPost(userID,post);
    }

    @PostMapping("/savePost")
    boolean addToSavedPost(int userID, int postID){
        return app.addToSavedPost(userID, postID);
    }

    boolean removePostFromSaved(int userID, int postID){
        return app.removePostFromSaved(userID, postID);
    }

}
