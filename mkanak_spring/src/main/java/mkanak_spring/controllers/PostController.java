package mkanak_spring.controllers;

import com.auth0.jwt.algorithms.Algorithm;
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
    private final SecurityGuard securityGuard = new SecurityGuard();

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


    //TODO check if the json objects contains the id named (seller_id)

    //   ################ Manipulation posts ########################
    @PostMapping("/new")
    public void addPost(@RequestHeader("Authorization") String bearerToken,
                        @RequestBody JSONObject jsonObject) throws Exception {
        int idJson = (int) jsonObject.get("seller_id");
        if(!securityGuard.verifyJWTtoken(idJson,bearerToken))
            throw new Exception("error");
        postService.createPost(jsonObject);
    }

    @PostMapping("/edit")
    public boolean editPost(@RequestHeader("Authorization") String bearerToken,
                            @RequestBody JSONObject jsonObject)
            throws Exception {
        //TODO check the recieved body of the request if valid
        int idJson = (int) jsonObject.get("seller_id");
        if(!securityGuard.verifyJWTtoken(idJson,bearerToken))
            throw new Exception("error");

//        JSONParser parser = new JSONParser();
//        JSONObject jsonObject = (JSONObject) parser.parse(jsonObject);
        postService.editPost(jsonObject);
        return false;
    }

    @PostMapping("/delete")
    public boolean deletePost(@RequestHeader("Authorization") String bearerToken,
                              @RequestBody JSONObject jsonObject) throws Exception {
        //TODO check if post deletion is with the ids

        int idJson = (int) jsonObject.get("seller_id");
        if(!securityGuard.verifyJWTtoken(idJson,bearerToken))
            throw new Exception("error");

//        JSONParser parser = new JSONParser();
//        JSONObject json = (JSONObject) parser.parse(details);

        postService.deletePost(jsonObject);
        return false;
    }


    // ###################### Saved Posts ####################################
    @GetMapping("/saved/{userID}")
    public List<Post> getSavedPosts(@RequestHeader("Authorization") String bearerToken,
                                    @PathVariable int userID,
                                    @RequestParam String preference,
                                    @RequestParam(name = "pageNum",defaultValue = "0") int pageNum,
                                    @RequestParam(name = "pageSize",defaultValue = "20") int pageSize
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
//        JSONParser parser = new JSONParser();
//        JSONObject json = (JSONObject) parser.parse(saveEntry);
//        System.out.println(json);
        postService.addToSavedPosts(jsonObject);
    }

    @PostMapping("/unsavePost")
    public void removePostFromSaved(@RequestBody JSONObject jsonObject) throws ParseException {
//        JSONParser parser = new JSONParser();
//        JSONObject json = (JSONObject) parser.parse(entry);

        postService.removeFromSaved(jsonObject);
    }

    @GetMapping("/details/{postID}")
    public JSONObject getPostDetails(@PathVariable int postID){
        return postService.getPostDetails(postID);
    }

}
