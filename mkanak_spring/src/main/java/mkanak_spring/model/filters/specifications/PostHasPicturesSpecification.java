package mkanak_spring.model.filters.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import mkanak_spring.model.ViewingPreference;
import mkanak_spring.model.entities.Post;

public class PostHasPicturesSpecification implements PostSpecification{
    private final ViewingPreference preference;
    public PostHasPicturesSpecification(ViewingPreference v){
        super();
        this.preference = v;
    }
    @Override
    public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if(this.preference == null || !this.preference.isFiltered()) return null;
        if(!this.preference.getFilterPreference().isWithPictures()) return null;
        return criteriaBuilder.isTrue(root.get("hasPictures"));
    }
}
