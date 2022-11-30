import javax.swing.text.View;
import java.util.ArrayList;
import java.util.List;

public class PostManager {


    List<Post> getPersonPosts(int targetUserID, ViewingPreferences preferences) {
        return new ArrayList<>();
    }
    List<Post> getHomePage(ViewingPreferences preferences){
        return new ArrayList<>();
    }

    List<Post> getSavedPosts(int userID,ViewingPreferences preferences){
        return new ArrayList<>();
    }

    boolean editPost(int userID, Post post){
        return false;
    }

    boolean addToSavedPost(int userID, int postID){
        return false;
    }

    boolean removePostFromSaved(int userID, int postID){
        return false;
    }

}
