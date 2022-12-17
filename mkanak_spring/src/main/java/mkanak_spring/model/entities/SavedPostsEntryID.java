package mkanak_spring.model.entities;

import jakarta.persistence.Column;

import java.io.Serializable;

public class SavedPostsEntryID implements Serializable {
        @Column(name = "user_id")
        private Long userID;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SavedPostsEntryID that = (SavedPostsEntryID) o;

        if (!userID.equals(that.userID)) return false;
        return postID.equals(that.postID);
    }

    @Override
    public int hashCode() {
        int result = userID.hashCode();
        result = 31 * result + postID.hashCode();
        return result;
    }
}
