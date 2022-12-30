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
                                  @RequestParam(name = "pageSize",defaultValue = "10") int pageSize)
            throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(preference);
        return postService.getHomepagePosts(json,pageNum,pageSize);
    }


    @GetMapping("/homepage/count")
    public long getHomePage(@RequestParam String preference)
            throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(preference);
        return postService.getHomepagePostsCount(json);
    }




    //    ################ Profile posts ########################
    @GetMapping("/profile/{targetUserID}")
    public List<Post> getProfilePosts(@PathVariable int targetUserID,
                                      @RequestParam String preference,
                                      @RequestParam(name = "pageNum",defaultValue = "0") int pageNum,
                                      @RequestParam(name = "pageSize",defaultValue = "10") int pageSize
                                      ) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(preference);
        return postService.getProfilePosts(targetUserID,json,pageNum,pageSize);
    }

    @GetMapping("/profile/{targetUserID}/count")
    public long getProfilePostsCount(@PathVariable int targetUserID,@RequestParam String preference) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(preference);
        return postService.getProfilePostsCount(targetUserID,json);
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
        long idJson = (long) postService.getProperty(postID).get("sellerID");
        if (!securityGuard.verifyJWTtoken((int)idJson, bearerToken))
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
                                    @RequestParam(name = "pageSize",defaultValue = "10") int pageSize
                                    ) throws Exception {
        if(!securityGuard.verifyJWTtoken(userID,bearerToken))
            throw new Exception("error");

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(preference);
        return postService.getSavedPosts(userID,json,pageNum,pageSize);
    }

    @GetMapping("/saved/{userID}/count")
    public long getSavedPostsCount(@RequestHeader("Authorization") String bearerToken,
                                    @PathVariable int userID,
                                    @RequestParam String preference
    ) throws Exception {
        if(!securityGuard.verifyJWTtoken(userID,bearerToken))
            throw new Exception("error");

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(preference);
        return postService.getSavedPostsCount(userID,json);
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
    public JSONObject getPostDetails(@PathVariable int postID) throws ParseException {
        return postService.getPostDetails(postID);
    }

    // 8yary el endpoint
    @GetMapping("/info/{propertyID}")
    public JSONObject getProperty(@PathVariable int propertyID) throws ParseException {
        return postService.getProperty(propertyID);
    }

}
