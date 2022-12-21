package mkanak_spring.model.filters;

import mkanak_spring.model.FilterPreference;
import mkanak_spring.model.ViewingPreference;

import mkanak_spring.model.entities.Post;
import mkanak_spring.model.filters.specifications.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PostSpecificationBuilder {
    private final List<Specification<Post>> possibleSpecifications;
    private List<Long> postIDs;
    private int id =-1 ;
    private ViewingPreference v;
    public PostSpecificationBuilder(ViewingPreference v ){
        this.possibleSpecifications=new ArrayList<>();
        this.v= v;
    }

    public PostSpecificationBuilder(ViewingPreference v, int id ){
        this(v);
        this.id=id;
    }

    public PostSpecificationBuilder(ViewingPreference v, List<Long> ids ){
        this(v);
        this.postIDs=ids;
    }




    public Specification<Post> build(){

        setupSpecifications(v);
        if(possibleSpecifications==null || possibleSpecifications.isEmpty())
            return null;
        Specification<Post> result = possibleSpecifications.get(0);
        for(int i=1;i<possibleSpecifications.size();i++)
            result = Specification.where(result).and(possibleSpecifications.get(i));
        return result;
    }


    private void setupSpecifications(ViewingPreference v){
        if(v==null) return;
        if(v.isFiltered())
            this.setupFiltersSpecification(v);
        if(v.isSorted())
            this.setupSortingSpecification(v);
        if(this.id !=-1)
            this.possibleSpecifications.add(new PostCertainIDSpecification(v,id));
        if(this.postIDs!=null)
            this.possibleSpecifications.add(new PostBelongToIDsSpecification(v,postIDs));
    }

    private void setupFiltersSpecification(ViewingPreference v){
        FilterPreference f = v.getFilterPreference();
        if(f==null) return;
        if(!f.getPurchaseChoice().equalsIgnoreCase("any")) //not both, we need to filter
            this.possibleSpecifications.add(new PostPurchaseTypeSpecification(v));
        if(!f.getPropertyType().equalsIgnoreCase("any"))
            this.possibleSpecifications.add(new PostPropertyTypeSpecification(v));
        if(f.getMinPrice()!=-1 && f.getMaxPrice()!=-1) //both numbers are set
            this.possibleSpecifications.add(new PostPriceRangeSpecification(v));
        if(f.getMinArea()!=-1 && f.getMaxArea()!= -1)
            this.possibleSpecifications.add(new PostAreaRangeSpecification(v));
        if(!Objects.equals(f.getInfoSearchWord(), "")){
            Specification<Post> s1 = new PostInfoSpecification(v);
            Specification<Post> s2 = new PostAddressSpecification(v);
            Specification<Post> orSpec =  Specification.where(s1).or(s2);
            this.possibleSpecifications.add(orSpec);
        }
        if(f.isWithPictures())
            this.possibleSpecifications.add(new PostHasPicturesSpecification(v));
        if(!f.getCitySearchWord().equalsIgnoreCase("any"))
            this.possibleSpecifications.add(new PostCitySpecification(v));
    }
    private void setupSortingSpecification(ViewingPreference v){
        // TO DO
    }

}
