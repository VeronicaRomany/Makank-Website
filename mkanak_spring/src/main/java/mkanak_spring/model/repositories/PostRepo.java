package mkanak_spring.model.repositories;

import mkanak_spring.model.entities.Post;
import org.json.simple.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepo extends JpaRepository<Post, Long> , JpaSpecificationExecutor<Post> {

    void deleteByAddressAllIgnoreCase(String address);
    boolean existsByAddress(String address);
    long countByAddress(String address);

    @Query(value = "select distinct post_id from property where property_address like :add ;",nativeQuery = true)
    List<Long> getDistinctPostIDsByAddress(@Param("add") String address);

    @Query(value = "SELECT * FROM post_large_view(?1)", nativeQuery = true)
    JSONObject getPostLargeView(long postID);

}
