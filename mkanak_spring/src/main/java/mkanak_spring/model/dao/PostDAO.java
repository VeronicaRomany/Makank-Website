package mkanak_spring.model.dao;

import mkanak_spring.model.preferences.ViewingPreference;
import mkanak_spring.model.entities.*;
import mkanak_spring.model.filters.PostSpecificationBuilder;
import mkanak_spring.model.repositories.*;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
        // TODO
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
