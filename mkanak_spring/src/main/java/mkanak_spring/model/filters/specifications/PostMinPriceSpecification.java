package mkanak_spring.model.filters.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import mkanak_spring.model.preferences.ViewingPreference;
import mkanak_spring.model.entities.Post;
import org.springframework.data.jpa.domain.Specification;

public class PostMinPriceSpecification implements Specification<Post> {

    private final ViewingPreference preference;
    public PostMinPriceSpecification(ViewingPreference v){
        super();
        this.preference = v;
    }

    @Override
    public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        int min = this.preference.getFilterPreference().getMinPrice();
        return criteriaBuilder.greaterThanOrEqualTo(root.get("price"),min);
    }
}
