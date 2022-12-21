package mkanak_spring.model.filters.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import mkanak_spring.model.ViewingPreference;

import mkanak_spring.model.entities.Post;
import org.springframework.data.jpa.domain.Specification;

public class PostPriceRangeSpecification implements Specification<Post> {
    private final ViewingPreference preference;
    public PostPriceRangeSpecification(ViewingPreference v){
        super();
        this.preference = v;
    }

    @Override
    public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        int min = this.preference.getFilterPreference().getMinPrice();
        int max = this.preference.getFilterPreference().getMaxPrice();
        return criteriaBuilder.between(root.get("price"),min,max);
    }
}
