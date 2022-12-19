package mkanak_spring.model.filters;

import mkanak_spring.model.ViewingPreference;
import mkanak_spring.model.entities.Post;
import mkanak_spring.model.filters.specifications.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class PostSpecificationBuilder {
    private ViewingPreference preference;
    private final List<PostSpecification> possibleSpecifications;

    public PostSpecificationBuilder(ViewingPreference v ){
           preference=v;
           this.possibleSpecifications=new ArrayList<>();
           //add all possible specifications
           this.possibleSpecifications.add(new PostAddressSpecification(preference));
           this.possibleSpecifications.add(new PostAreaRangeSpecification(preference));
           this.possibleSpecifications.add(new PostCitySpecification(preference));
           this.possibleSpecifications.add(new PostHasPicturesSpecification(preference));
           this.possibleSpecifications.add(new PostPriceRangeSpecification(preference));
           this.possibleSpecifications.add(new PostPropertyTypeSpecification(preference));
           this.possibleSpecifications.add(new PostPurchaseTypeSpecification(preference));
    }

    public PostSpecificationBuilder(ViewingPreference v, int id ){
        this(v);
        this.possibleSpecifications.add(new PostCertainIDSpecification(v,id));
    }


    public Specification<Post> build(){
        Specification<Post> result = null;
        for(Specification<Post> sp : possibleSpecifications){
            if(sp!=null)
                result = Specification.where(result).and(sp);
        }
        return result;
    }
}
