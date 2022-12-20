package mkanak_spring.model.filters.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import mkanak_spring.model.ViewingPreference;
import mkanak_spring.model.entities.Post;

import org.springframework.data.jpa.domain.Specification;

public class PostPropertyTypeSpecification implements Specification<Post> {
    private final ViewingPreference preference;
    public PostPropertyTypeSpecification(ViewingPreference v){
        super();
        this.preference = v;
    }
    @Override
    public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        String str = this.preference.getFilterPreference().getPropertyType();
        return criteriaBuilder.like(root.get("type"),str);
    }
}
