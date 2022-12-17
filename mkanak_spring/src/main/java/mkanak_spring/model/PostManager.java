package mkanak_spring.model;

import mkanak_spring.model.dao.SavedPostsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PostManager {
    @Autowired SavedPostsDAO dao;
    public List<Post> getPersonPosts(int targetUserID, ViewingPreference preferences) {
        return null;
    }
    public List<Post> getHomePage(ViewingPreference preferences){
        List<Post> homePageContent= new ArrayList<>();
        for(int i=0;i<5;i++){
            homePageContent.add(getDummyPost());
        }
        return homePageContent;
    }

    public List<Post> getSavedPosts(int userID, ViewingPreference preferences){
        return null;
    }

    public List<Long> getSavedIDs(int userID){
        return dao.getSavedIDs((long) userID);
    }

    public boolean editPost(int userID, Post post){
        return false;
    }

    public boolean addToSavedPost(int userID, int postID){
        return false;
    }

    public boolean removePostFromSaved(int userID, int postID){
        return false;
    }

    private Post getDummyPost(){
        Apartment x = new Apartment();
        x.sellerID=5;
        x.address="22nd 45street";
        String[] pics = new String[5];
        pics[0] = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTYI7wP8BBfh928fLg3Ui5Slj7ROZW_zc3rag&usqp=CAU";
        x.pictures=pics;
        x.area=250;
        x.info="for sale owner is moving to Banha";
        x.type="villa";
        x.roomNumber=3;
        x.bathroomNumber=4;
        x.city="Alexandria, Egypt";
        x.rent=false;
        x.setElevator(true);

        Post p = new Post();
        p.setPostID(271);
        p.setProperty(x);
        return p;
    }

}
