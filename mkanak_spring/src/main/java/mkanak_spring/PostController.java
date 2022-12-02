package mkanak_spring;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/posts")
public class PostController {
    PostManager app = new PostManager();
    @GetMapping("/{targetUserID}")
    public List<Post> getPersonPosts(@PathVariable int targetUserID, @RequestParam ViewingPreference preferences){
            return app.getPersonPosts(targetUserID,preferences);
    }

    @GetMapping("/homepage")
    public List<Post> getHomePage(@RequestParam String preference){
//        GSON gson = new GSON();
        System.out.println("GOT A REQUEST >> "+preference);
//
//        ViewingPreference p= gson.fromJSON(preference,ViewingPreference.class);
        ViewingPreference p= new ViewingPreference();
        return app.getHomePage(p);
    }

    @GetMapping("/saved/{targetUserID}")
    public List<Post> getSavedPosts(@PathVariable int userID,@RequestBody ViewingPreference preferences){
        return app.getPersonPosts(userID,preferences);
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
