package mkanak_spring.model.dao;

import mkanak_spring.model.entities.Post;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> , JpaSpecificationExecutor<Post> {
    List<Post> findBySellerIDIn(Collection<Long> sellerIDS);
    List<Post> findBySellerIDIn(Specification<Post> sps, Collection<Long> sellerIDS);

    List<Post> findByPropertyIDIn(Collection<Long> propertyIDS);
    List<Post> findByPropertyIDIn(Specification<Post> sps, Collection<Long> propertyIDS);



}
