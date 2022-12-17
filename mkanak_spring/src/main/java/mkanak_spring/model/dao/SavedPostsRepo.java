package mkanak_spring.model.dao;

import mkanak_spring.model.entities.SavedPostsEntry;
import mkanak_spring.model.entities.SavedPostsEntryID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SavedPostsRepo  extends JpaRepository<SavedPostsEntry, SavedPostsEntryID> {

    @Query(value = "select post_id from saved_items where user_id = :userID ;",nativeQuery = true)
    List<Long> getUserSavedPostsIDs(@Param("userID") Long userID);

    @Transactional
    @Modifying
    @Query("delete from SavedPostsEntry s where s.userID = ?1 and s.postID = ?2")
    void deleteSavedPost(Long userID, Long postID);


}
