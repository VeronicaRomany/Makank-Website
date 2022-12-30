package mkanak_spring.controllers;


import com.auth0.jwt.algorithms.Algorithm;
import mkanak_spring.model.entities.Post;
import mkanak_spring.model.entities.Property;
import mkanak_spring.model.services.PostService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired PostService postService;
    private final SecurityGuard securityGuard = new SecurityGuard();

    //   ################## HOME PAGE ########################
    @GetMapping("/homepage")
    public List<Post> getHomePage(@RequestParam String preference,
                                  @RequestParam(name = "pageNum",defaultValue = "0") int pageNum,
                                  @RequestParam(name = "pageSize",defaultValue = "50") int pageSize)
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


    //TODO check if the json objects contains the id named (seller_id)

    //   ################ Manipulation posts ########################
    @PostMapping("/new")
    public void addPost(@RequestHeader("Authorization") String bearerToken,
                            @RequestBody JSONObject postDetails) throws Exception {
        int idJson = (int) postDetails.get("sellerID");
        if(!securityGuard.verifyJWTtoken(idJson,bearerToken))
            throw new Exception("error");
        System.out.println("details: " + postDetails);
        postService.savePost(null, postDetails);
    }


    @PostMapping("/edit")
    public boolean editPost(@RequestHeader("Authorization") String bearerToken,
                            @RequestBody JSONObject post) throws Exception {
        int idJson = (int) post.get("sellerID");
        if(!securityGuard.verifyJWTtoken(idJson,bearerToken))
            throw new Exception("error");
        postService.editPost(post);
        return false;
    }

    @DeleteMapping("/delete/{postID}")
    public boolean deletePost(@RequestHeader("Authorization") String bearerToken,
                              @PathVariable int postID) throws Exception {
        int idJson = (int) postService.getPostDetails(postID).get("seller_id");
        if (!securityGuard.verifyJWTtoken(idJson, bearerToken))
            throw new Exception("error");

        postService.deletePost(postID);
        return false;
    }


    // ###################### Saved Posts ####################################
    @GetMapping("/saved/{userID}")
    public List<Post> getSavedPosts(@RequestHeader("Authorization") String bearerToken,
                                    @PathVariable int userID,
                                    @RequestParam String preference,
                                    @RequestParam(name = "pageNum",defaultValue = "0") int pageNum,
                                    @RequestParam(name = "pageSize",defaultValue = "50") int pageSize
                                    ) throws Exception {
        if(!securityGuard.verifyJWTtoken(userID,bearerToken))
            throw new Exception("error");

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(preference);
        return postService.getSavedPosts(userID,json,pageNum,pageSize);
    }

    @GetMapping("/saved/ids/{userID}")
    public List<Long> getSavedIDs(@RequestHeader("Authorization") String bearerToken,
                                  @PathVariable int userID){
        return postService.getSavedPostsIDs(userID);
    }


    @PostMapping("/savePost")
    public void addToSavedPost(@RequestBody JSONObject jsonObject) throws ParseException {
        postService.addToSavedPosts(jsonObject);
    }

    @PostMapping("/unsavePost")
    public void removePostFromSaved(@RequestBody JSONObject jsonObject) throws ParseException {
        postService.removeFromSaved(jsonObject);
    }

    @GetMapping("/details/{postID}")
    public JSONObject getPostDetails(@PathVariable int postID){
        return postService.getPostDetails(postID);
    }

    // 8yary el endpoint
    @GetMapping("/info/{propertyID}")
    public JSONObject getProperty(@PathVariable int propertyID) throws ParseException {
        return postService.getProperty(propertyID);
    }

}
