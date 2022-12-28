package mkanak_spring.model.services;


import mkanak_spring.model.entities.*;

import mkanak_spring.model.filters.PostSpecificationBuilder;
import mkanak_spring.model.preferences.ViewingPreference;
import mkanak_spring.model.repositories.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PostServiceImpl implements PostService{


    @Autowired
    ApartmentRepo apartmentRepo;
    @Autowired
    VillaRepo villaRepo;
    @Autowired
    PropertyPictureRepo propertyPictureRepo;
    @Autowired
    PostRepo postRepo;
    @Autowired
    SavedPostsRepo savedPostsRepo;
    @Autowired
    JsonToObject converter;

    @Override
    public void createPost(JSONObject post) throws ParseException {
        JsonToObject converter = new JsonToObject();
        System.out.println("Type: " + post.get("type"));
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(post.toString());
        JSONArray pictures = (JSONArray) object.get("pictures");
        if(post.get("type").toString().compareTo("villa") == 0) {
            Villa property = new Villa();
            property = converter.buildVilla(post);
            property.setHasPictures(pictures.size() != 0);
            this.saveVilla(property);
            List<PropertyPicture> pictureList = converter.buildPropertyPictures(post, property.getPropertyID());
            this.saveAllPictures(pictureList);
        }
        else {
            Apartment property = new Apartment();
            property = converter.buildApartment(post);
            property.setHasPictures(pictures.size() != 0);
            this.saveApartment(property);
            List<PropertyPicture> pictureList = converter.buildPropertyPictures(post, property.getPropertyID());
            this.saveAllPictures(pictureList);
        }
    }

    @Override
    public List<Post> getHomepagePosts(JSONObject preference,int pageNum,int pageSize) {
        ViewingPreference p = converter.parseViewingPreference(preference);
        return this.getAllPosts(p,pageNum,pageSize);
    }

    @Override
    public List<Post> getSavedPosts(int id, JSONObject preference,int pageNum,int pageSize) {
        ViewingPreference v = converter.parseViewingPreference(preference);
        return this.getSavedPostsByUserID(id,v,pageNum,pageSize);
    }

    @Override
    public List<Post> getProfilePosts(int targetUserID, JSONObject preferences,int pageNum,int pageSize) {
        ViewingPreference v = converter.parseViewingPreference(preferences);
        return this.getPostsByUser(targetUserID,v,pageNum,pageSize);
    }

    @Override
    public List<Long> getSavedPostsIDs(int userID) {
        return this.getSavedPostsIDs(userID);
    }

    @Override
    public void addToSavedPosts(JSONObject saveEntry) {
        int userID = (int) saveEntry.get("userID");
        int postID = (int) saveEntry.get("postID");
        this.addToSavedPosts(userID,postID);
    }

    @Override
    public void removeFromSaved(JSONObject entry) {
        int userID = (int) entry.get("userID");
        int postID = (int) entry.get("postID");
        this.removeFromSavedPosts(userID,postID);
    }

    @Override
    public void deletePost(JSONObject details) {
        //TODO
    }

    @Override
    public void editPost(JSONObject post) {
        //TODO
    }

    @Override
    public JSONObject getPostDetails(long postID) {
        return postRepo.getPostLargeView(postID);
    }


    private Property getDummyPost(){
        Apartment x = new Apartment();
        x.setSellerID(5);
        x.setAddress("22nd 45street");
        String[] pics = new String[5];
        pics[0] = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTYI7wP8BBfh928fLg3Ui5Slj7ROZW_zc3rag&usqp=CAU";
        x.setHasPictures(true);
        x.setArea(250);
        x.setInfo("for sale owner is moving to Banha");
        x.setType("villa");
        x.setRoomNumber(3);
        x.setBathroomNumber(4);
        x.setCity("Alexandria, Egypt");
        x.setRent(false);
        x.setElevator(true);
        return x;
    }






    public void saveApartment(Apartment property) {
        apartmentRepo.save(property);
    }

    public void saveVilla(Villa property) {
        villaRepo.save(property);
    }

    public void saveAllPictures(List<PropertyPicture> propertyPictureList) {
        propertyPictureRepo.saveAll(propertyPictureList);
    }



    public List<Post> getAllPosts(ViewingPreference preference, int pageNum, int pageSize) {
        PostSpecificationBuilder pb;
        if(preference!=null && preference.isFiltered()
                && Objects.equals(preference.getFilterPreference().getPropertyType(), "apartment")
                && preference.getFilterPreference().isStudentHousing()) {

            List<Long> studentHouseIDs= apartmentRepo.getStudentHousingIDs(preference.getFilterPreference().isStudentHousing());
            pb = new PostSpecificationBuilder(preference,studentHouseIDs);
        } else {
            pb = new PostSpecificationBuilder(preference);
        }
        return buildAndReturn(pb,pageNum,pageSize);
    }


    private List<Post> buildAndReturn(PostSpecificationBuilder pb,int pageNum,int pageSize){
        Specification<Post> sps = pb.build();
        Sort s = pb.getSort();
        if(sps==null && s == null) {
            Pageable page = PageRequest.of(pageNum, pageSize);
            return postRepo.findAll(page).toList();
        }
        else if(s==null) {
            Pageable page = PageRequest.of(pageNum, pageSize);
            return postRepo.findAll(sps,page).toList();
        }
        else if(sps==null) {
            Pageable page = PageRequest.of(pageNum, pageSize,s);
            return postRepo.findAll(page).toList();
        }
        else {
            Pageable page = PageRequest.of(pageNum, pageSize,s);
            return postRepo.findAll(sps, page).toList();
        }
    }

    public List<Post> getPostsByUser(int id,ViewingPreference preference, int pageNum,int pageSize){
        PostSpecificationBuilder pb = new PostSpecificationBuilder(preference,id);
        return buildAndReturn(pb,pageNum,pageSize);
    }


    public List<Post> getSavedPostsByUserID(int id,ViewingPreference preference, int pageNum,int pageSize){
        List<Long> postIDsSaved = savedPostsRepo.getUserSavedPostsIDs((long) id);
        PostSpecificationBuilder pb = new PostSpecificationBuilder(preference,postIDsSaved);
        return buildAndReturn(pb,pageNum,pageSize);
    }

    public void removeFromSavedPosts(long userID, long postID){
        savedPostsRepo.deleteSavedPost(userID,postID);
    }

    public void addToSavedPosts(long userID, long postID){
        SavedPostsEntry entry = new SavedPostsEntry();
        entry.setPostID(postID);
        entry.setUserID(userID);
        savedPostsRepo.save(entry);
    }

    public List<Long> getSavedPostsIDs(long id){
        return savedPostsRepo.getUserSavedPostsIDs(id);
    }


}
