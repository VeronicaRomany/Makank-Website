package mkanak_spring.model.filters.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import mkanak_spring.model.ViewingPreference;
import mkanak_spring.model.entities.Post;

public class PostInfoSpecification implements PostSpecification{
    private final ViewingPreference preference;
    public PostInfoSpecification(ViewingPreference v){
        super();
        this.preference = v;
    }
    @Override
    public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if(this.preference == null || !this.preference.isFiltered() || this.preference.getFilterPreference().getInfoSearchWord() == null)
            return null;
        if(this.preference.getFilterPreference().getInfoSearchWord().equals(""))
            return null;
        String str = "%" + this.preference.getFilterPreference().getInfoSearchWord() + "%";
        return criteriaBuilder.like(root.get("info"),str);
    }
}
