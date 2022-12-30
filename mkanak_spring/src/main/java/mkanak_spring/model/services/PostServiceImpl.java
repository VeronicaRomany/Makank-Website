package mkanak_spring.model.services;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import mkanak_spring.model.entities.*;
import mkanak_spring.model.filters.PostSpecificationBuilder;
import mkanak_spring.model.preferences.ViewingPreference;
import mkanak_spring.model.repositories.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import mkanak_spring.model.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    @Autowired
    PropertyRepo propertyRepo;

    @Override
    public void savePost(Long propertyID, JSONObject post) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(post.toString());
        JSONArray pictures = (JSONArray) object.get("pictures");
        if(post.get("type").toString().compareTo("villa") == 0) {
            Villa property = converter.buildVilla(post);
            property.setHasPictures(pictures.size() != 0);
            property.setPropertyID(propertyID);
            this.saveVilla(property);
            List<PropertyPicture> pictureList = converter.buildPropertyPictures(post, property.getPropertyID());
            this.savePropertyPictures(pictureList);
        }
        else {
            Apartment property = converter.buildApartment(post);
            property.setHasPictures(pictures.size() != 0);
            property.setPropertyID(propertyID);
            this.saveApartment(property);
            List<PropertyPicture> pictureList = converter.buildPropertyPictures(post, property.getPropertyID());
            this.savePropertyPictures(pictureList);
        }
    }

    @Override
    public List<Post> getHomepagePosts(JSONObject preference,int pageNum,int pageSize) {
        ViewingPreference p = converter.parseViewingPreference(preference);
        return this.getAllPosts(p,pageNum,pageSize);
    }

    @Override
    public long getHomepagePostsCount(JSONObject preference) {
        ViewingPreference p = converter.parseViewingPreference(preference);
        PostSpecificationBuilder pb;
        if(preference!=null && p.isFiltered()
                && Objects.equals(p.getFilterPreference().getPropertyType(), "apartment")
                && p.getFilterPreference().isStudentHousing()) {

            List<Long> studentHouseIDs= apartmentRepo.getStudentHousingIDs(p.getFilterPreference().isStudentHousing());
            pb = new PostSpecificationBuilder(p,studentHouseIDs);
        } else {
            pb = new PostSpecificationBuilder(p);
        }
        return this.buildAndCount(pb);
    }

    @Override
    public List<Post> getSavedPosts(int id, JSONObject preference,int pageNum,int pageSize) {
        ViewingPreference v = converter.parseViewingPreference(preference);
        return this.getSavedPostsByUserID(id,v,pageNum,pageSize);
    }

    @Override
    public long getSavedPostsCount(int id, JSONObject preferences) {
        List<Long> postIDsSaved = savedPostsRepo.getUserSavedPostsIDs((long) id); //get saved posts by that user
        ViewingPreference preference = converter.parseViewingPreference(preferences);
        if(preference!=null && preference.isFiltered()
                && Objects.equals(preference.getFilterPreference().getPropertyType(), "apartment")
                && preference.getFilterPreference().isStudentHousing()) {
            List<Long> studentHouseIDs= apartmentRepo.getStudentHousingIDs(preference.getFilterPreference().isStudentHousing());
            Set<Long> result = postIDsSaved.stream()
                    .distinct()
                    .filter(studentHouseIDs::contains)
                    .collect(Collectors.toSet());
            postIDsSaved=result.stream().toList();
        }
        PostSpecificationBuilder pb = new PostSpecificationBuilder(preference,postIDsSaved);
        return buildAndCount(pb);
    }


    @Override
    public List<Post> getProfilePosts(int targetUserID, JSONObject preferences,int pageNum,int pageSize) {
        ViewingPreference v = converter.parseViewingPreference(preferences);
        return this.getPostsByUser(targetUserID,v,pageNum,pageSize);
    }

    @Override
    public long getProfilePostsCount(int targetUserID, JSONObject preferences) {
        ViewingPreference preference = converter.parseViewingPreference(preferences);
        PostSpecificationBuilder pb;
        if(preference!=null && preference.isFiltered()
                && Objects.equals(preference.getFilterPreference().getPropertyType(), "apartment")
                && preference.getFilterPreference().isStudentHousing()) {

            List<Long> studentHouseIDs= apartmentRepo.getStudentHousingIDs(preference.getFilterPreference().isStudentHousing());
            pb = new PostSpecificationBuilder(preference,targetUserID,studentHouseIDs);
        } else {
            pb = new PostSpecificationBuilder(preference,targetUserID);
        }
        return buildAndCount(pb);
    }

    @Override
    public List<Long> getSavedPostsIDs(int userID) {
        return savedPostsRepo.getUserSavedPostsIDs((long) userID);
    }

    @Override
    public void addToSavedPosts(JSONObject saveEntry) {
        int userID = (int) saveEntry.get("userID");
        int postID = (int) saveEntry.get("postID");
        SavedPostsEntry entry = new SavedPostsEntry();
        entry.setPostID((long) postID);
        entry.setUserID((long) userID);
        savedPostsRepo.save(entry);
    }

    @Override
    public void removeFromSaved(JSONObject entry) {
        long userID = (int) entry.get("userID");
        long postID = (int) entry.get("postID");
        savedPostsRepo.deleteSavedPost(userID,postID);
    }

    @Override
    public void deletePost(long postID) {
        propertyRepo.deleteById(postID);
    }

    @Override
    public void editPost(JSONObject post) throws ParseException {
        Long postID = ((Number) post.get("postID")).longValue();
        System.out.println("id: " + postID);
        propertyPictureRepo.deletePicturesById(postID);
        if(post.get("type").toString().compareTo((String) this.getProperty(postID).get("type")) != 0) {
            Property property = new Property();
            converter.buildPost(property, post);
            List<PropertyPicture> pictureList = converter.buildPropertyPictures(post, postID);
            property.setPropertyID(postID);
            property.setHasPictures(pictureList.size() != 0);
            System.out.println("aaa: " + property.getPropertyID());
            propertyRepo.updateProperty(property.getPropertyID(), property.getRoomNumber(), property.getBathroomNumber(),
                    property.getPrice(), property.getCity(), property.getAddress(), property.getArea(), property.isRent(),
                    property.getInfo(), property.getType(), property.getHasPictures());
            propertyPictureRepo.saveAll(pictureList);
            if(post.get("type").toString().compareTo("villa") != 0) {
                villaRepo.deleteVilla(postID);
                apartmentRepo.insertApartment(postID, (boolean)post.get("elevator"),
                        (int)post.get("level"), (boolean) post.get("studentHousing"));
            }
            else {
                apartmentRepo.deleteApartment(postID);
                villaRepo.insertVilla(postID, (boolean)post.get("hasGarden"),
                        (int)post.get("level"), (boolean) post.get("hasPool"));
            }
        }
        else this.savePost(postID, post);
    }

    @Override
    public JSONObject getPostDetails(long postID) throws ParseException {
        Gson gson = new Gson();
        String json = gson.toJson(postRepo.getPostLargeView(postID));
        JSONParser parser = new JSONParser();
        JSONObject post = (JSONObject) parser.parse(json);
        return (JSONObject) post.get("post_large_view");
    }

    @Override
    public JSONObject getProperty(long propertyID) throws ParseException {
        Gson gson = new Gson();
        String property = gson.toJson(propertyRepo.findById(propertyID).orElse(null));
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(property);

        json.put("pictures", this.getPropertyPictures(propertyID));
        return json;
    }
    private void saveVilla(Villa villa) {
        villaRepo.save(villa);
    }
    private void saveApartment(Apartment apartment) {
        apartmentRepo.save(apartment);
    }
    private void savePropertyPictures(List<PropertyPicture> pictureList) {
        propertyPictureRepo.saveAll(pictureList);
    }
    private List<String> getPropertyPictures(long propertyID) {
        return propertyPictureRepo.getPropertyPictures(propertyID);
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


    private long buildAndCount(PostSpecificationBuilder pb){
        Specification<Post> sps = pb.build();
        Sort s = pb.getSort();
        if(sps==null) {
            return postRepo.count();
        }
        else {
            return postRepo.count(sps);
        }
    }




    public List<Post> getPostsByUser(int id,ViewingPreference preference, int pageNum,int pageSize){
        PostSpecificationBuilder pb;
        if(preference!=null && preference.isFiltered()
                && Objects.equals(preference.getFilterPreference().getPropertyType(), "apartment")
                && preference.getFilterPreference().isStudentHousing()) {

            List<Long> studentHouseIDs= apartmentRepo.getStudentHousingIDs(preference.getFilterPreference().isStudentHousing());
            pb = new PostSpecificationBuilder(preference,id,studentHouseIDs);
        } else {
            pb = new PostSpecificationBuilder(preference,id);
        }
        return buildAndReturn(pb,pageNum,pageSize);
    }


    public List<Post> getSavedPostsByUserID(int id,ViewingPreference preference, int pageNum,int pageSize){
        List<Long> postIDsSaved = savedPostsRepo.getUserSavedPostsIDs((long) id); //get saved posts by that user
        if(preference!=null && preference.isFiltered()
                && Objects.equals(preference.getFilterPreference().getPropertyType(), "apartment")
                && preference.getFilterPreference().isStudentHousing()) {
            List<Long> studentHouseIDs= apartmentRepo.getStudentHousingIDs(preference.getFilterPreference().isStudentHousing());
            Set<Long> result = postIDsSaved.stream()
                    .distinct()
                    .filter(studentHouseIDs::contains)
                    .collect(Collectors.toSet());
            postIDsSaved=result.stream().toList();
        }
        PostSpecificationBuilder pb = new PostSpecificationBuilder(preference,postIDsSaved);
        return buildAndReturn(pb,pageNum,pageSize);
    }




}
