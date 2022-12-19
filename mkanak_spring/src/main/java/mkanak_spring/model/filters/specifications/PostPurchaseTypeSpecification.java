package mkanak_spring.model.filters.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import mkanak_spring.model.ViewingPreference;
import mkanak_spring.model.entities.Post;

public class PostPurchaseTypeSpecification implements PostSpecification {
    private final ViewingPreference preference;
    public PostPurchaseTypeSpecification(ViewingPreference v){
        super();
        this.preference = v;
    }
    @Override
    public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if(this.preference == null || !this.preference.isFiltered() || this.preference.getFilterPreference().getPurchaseChoice() == null) return null;
        if(this.preference.getFilterPreference().getPurchaseChoice().equals("")|| this.preference.getFilterPreference().getPurchaseChoice().equals("any")) return null;
        String str = this.preference.getFilterPreference().getPurchaseChoice();
        return criteriaBuilder.equal(root.get("rent"),str.equals("rent"));
    }
}
