package mkanak_spring.model.entities;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "saved_items")
@NoArgsConstructor
@IdClass(SavedPostsEntryID.class)
public class SavedPostsEntry {
    @Id
    @Column(name = "user_id")
    private Long userID;
    @Id
    @Column(name = "post_id")
    private Long postID;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getPostID() {
        return postID;
    }

    public void setPostID(Long postID) {
        this.postID = postID;
    }
}
