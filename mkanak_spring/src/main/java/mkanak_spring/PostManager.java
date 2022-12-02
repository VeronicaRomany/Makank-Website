package mkanak_spring;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class PostManager {
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
        pics[0] = "https://cdn.britannica.com/43/93843-050-A1F1B668/White-House-Washington-DC.jpg";
        x.pictures=pics;
        x.area=250;
        x.info="for sale owner is moving to Banha";
        x.type="villa";
        x.roomNumber=3;
        x.bathroomNumber=4;
        x.city="Alexandria, Egypt";
        x.rent=false;

        Post p = new Post();
        p.setPostID(271);
        p.setProperty(x);
        return p;
    }

}
