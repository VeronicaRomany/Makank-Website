package mkanak_spring.model.dao;

import mkanak_spring.model.entities.Post;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> , JpaSpecificationExecutor<Post> {

@Query
        (value = "Select " +
                "post_id,seller_id,property_type,price,city," +
                "property_address,area,info,rooms,bathrooms," +
                "for_rent,has_pictures " +
                "from saved_items natural join properties where user_id = :id ;", nativeQuery = true)
    List<Post> findAllSavedProperties(@Param("id") long userID);
}
