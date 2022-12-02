package mkanak_spring;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PostManager {
    List<Post> getPersonPosts(int targetUserID, ViewingPreference preferences) {
        return new ArrayList<>();
    }
    List<Post> getHomePage(ViewingPreference preferences){
        List<Post> homePageContent= new ArrayList<>();
        for(int i=0;i<5;i++){
            homePageContent.add(getDummyPost());
        }
        return homePageContent;
    }

    List<Post> getSavedPosts(int userID, ViewingPreference preferences){
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


    private Post getDummyPost(){
        Villa x = new Villa();
        x.sellerID=5;
        x.address="22nd 45street";
        String[] pics = new String[5];
        pics[0] = "https://i.ibb.co/HtcV5Zk/red.png";
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
        p.postID=217;
        p.property=x;
        p.publishDate=new Date(System.currentTimeMillis());
        return p;
    }

}
