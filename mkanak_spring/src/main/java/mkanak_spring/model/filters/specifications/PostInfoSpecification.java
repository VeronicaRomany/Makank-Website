package mkanak_spring.model.filters.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import mkanak_spring.model.preferences.ViewingPreference;

import mkanak_spring.model.entities.Post;
import org.springframework.data.jpa.domain.Specification;

public class PostInfoSpecification implements Specification<Post> {
    private final ViewingPreference preference;
    public PostInfoSpecification(ViewingPreference v){
        super();
        this.preference = v;
    }
    @Override
    public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        String str = "%" + this.preference.getFilterPreference().getInfoSearchWord() + "%";
        return criteriaBuilder.like(criteriaBuilder.upper(root.get("info")),str.toUpperCase());
    }
}
