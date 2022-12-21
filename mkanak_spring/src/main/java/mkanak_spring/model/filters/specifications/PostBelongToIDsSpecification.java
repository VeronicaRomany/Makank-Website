package mkanak_spring.model.filters.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import mkanak_spring.model.ViewingPreference;

import mkanak_spring.model.entities.Post;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class PostBelongToIDsSpecification implements Specification<Post> {

    private final ViewingPreference preference;
    private final List<Long> groupIDs;
    public PostBelongToIDsSpecification(ViewingPreference v, List<Long> ids){
        super();
        this.preference = v;
        this.groupIDs=ids;
    }


    @Override
    public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return root.get("propertyID").in(groupIDs);
    }
}
