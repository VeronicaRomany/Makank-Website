package mkanak_spring.model.filters.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import mkanak_spring.model.ViewingPreference;
import mkanak_spring.model.entities.Post;

public class PostCertainIDSpecification implements PostSpecification {

    private final ViewingPreference preference;
    private int sellerID=0;
    public PostCertainIDSpecification(ViewingPreference v, int id){
        super();
        this.preference = v;
        this.sellerID=id;
    }

    @Override
    public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if(this.preference == null || !this.preference.isFiltered()) return null;
        if(this.sellerID == -1) return null;
        return criteriaBuilder.equal(root.get("propertyID"),sellerID);
    }
}
