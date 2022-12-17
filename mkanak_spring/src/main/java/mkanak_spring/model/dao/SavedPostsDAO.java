package mkanak_spring.model.dao;

import mkanak_spring.model.entities.SavedPostsEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SavedPostsDAO {
    @Autowired private SavedPostsRepo savedPostsRepo;
    public void addSavedPost(Long savedPostID,Long userID){
        SavedPostsEntry entry = new SavedPostsEntry();
        entry.setPostID(savedPostID);
        entry.setUserID(userID);
        savedPostsRepo.save(entry);
    }
    public void deleteFromSavedPosts(Long savedPostID,Long userID){
        savedPostsRepo.deleteSavedPost(userID,savedPostID);
    }

    public List<Long> getSavedIDs(Long id){
        return savedPostsRepo.getUserSavedPostsIDs(id);
    }
}
