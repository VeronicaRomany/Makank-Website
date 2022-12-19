package mkanak_spring.model.filters.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import mkanak_spring.model.ViewingPreference;
import mkanak_spring.model.entities.Post;

public class PostAreaRangeSpecification implements PostSpecification{
    private final ViewingPreference preference;
    public PostAreaRangeSpecification(ViewingPreference v){
        super();
        this.preference = v;
    }

    @Override
    public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if(this.preference == null || !this.preference.isFiltered()) return null;
        if(this.preference.getFilterPreference().getMaxArea() == -1 || this.preference.getFilterPreference().getMinArea() == -1) return null;
        int min = this.preference.getFilterPreference().getMinArea();
        int max = this.preference.getFilterPreference().getMaxArea();
        return criteriaBuilder.between(root.get("area"),min,max);    }
}
