package mkanak_spring.model.filters.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import mkanak_spring.model.ViewingPreference;
import mkanak_spring.model.entities.Post;

public class PostPriceRangeSpecification implements PostSpecification{
    private final ViewingPreference preference;
    public PostPriceRangeSpecification(ViewingPreference v){
        super();
        this.preference = v;
    }

    @Override
    public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if(this.preference == null || !this.preference.isFiltered()) return null;
        if(this.preference.getFilterPreference().getMaxPrice() == -1 || this.preference.getFilterPreference().getMinPrice() == -1) return null;
        int min = this.preference.getFilterPreference().getMinPrice();
        int max = this.preference.getFilterPreference().getMaxPrice();
        return criteriaBuilder.between(root.get("price"),min,max);
    }
}
