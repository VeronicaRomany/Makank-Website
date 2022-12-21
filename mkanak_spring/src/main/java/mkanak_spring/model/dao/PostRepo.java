package mkanak_spring.model.dao;

import mkanak_spring.model.Post;
import org.json.simple.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
    @Query(value = "SELECT * FROM post_large_view(?1)", nativeQuery = true)
    JSONObject getPostLargeView(long postID);
}
