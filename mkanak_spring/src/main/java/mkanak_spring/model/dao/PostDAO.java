package mkanak_spring.model.dao;

import mkanak_spring.model.ViewingPreference;
import mkanak_spring.model.entities.*;
import mkanak_spring.model.filters.PostSpecificationBuilder;
import mkanak_spring.model.repositories.*;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;


/**
 *  This Class is for retrieval
 */
@Component
public class PostDAO {
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


    public void saveApartment(Apartment property) {
        apartmentRepo.save(property);
    }

    public void saveVilla(Villa property) {
        villaRepo.save(property);
    }

    public void saveAllPictures(List<PropertyPicture> propertyPictureList) {
        propertyPictureRepo.saveAll(propertyPictureList);
    }

    public List<Post> getAllPosts(ViewingPreference preference) {
        PostSpecificationBuilder pb;
        if(preference!=null && preference.isFiltered()
            && Objects.equals(preference.getFilterPreference().getPropertyType(), "apartment")
            && preference.getFilterPreference().isStudentHousing()) {
            // need to filter by student housing part
            List<Long> studentHouseIDs= apartmentRepo.getStudentHousingIDs(preference.getFilterPreference().isStudentHousing());
            pb = new PostSpecificationBuilder(preference,studentHouseIDs);
        } else {
            pb = new PostSpecificationBuilder(preference);
        }
        Specification<Post> sps = pb.build();
        Sort s = pb.getSort();
        Pageable p =null ;
        if(sps==null && s == null)
            return postRepo.findAll();
        else if(s==null)
            return postRepo.findAll(sps);
        else if(sps==null)
            return postRepo.findAll(s);
        else
            return postRepo.findAll(sps,s);
    }




    public List<Post> getPostsByUser(ViewingPreference preference, int id){
        PostSpecificationBuilder pb = new PostSpecificationBuilder(preference,id);
        Specification<Post> sps = pb.build();
        if(sps==null)
            return postRepo.findAll();
        else
            return postRepo.findAll(sps);
    }


    public List<Post> getSavedPostsByUserID(ViewingPreference preference, int id){
        List<Long> postIDsSaved = savedPostsRepo.getUserSavedPostsIDs((long) id);
        PostSpecificationBuilder pb = new PostSpecificationBuilder(preference,postIDsSaved);
        Specification<Post> sps = pb.build();
        if(sps==null)
            return postRepo.findAll();
        else
            return postRepo.findAll(sps);
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

    public JSONObject getPostDetails(long postID) {
        return postRepo.getPostLargeView(postID);
    }

}
