package mkanak_spring.model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import mkanak_spring.model.ViewingPreference;
import mkanak_spring.model.entities.Apartment;
import mkanak_spring.model.entities.Post;
import mkanak_spring.model.entities.PropertyPicture;
import mkanak_spring.model.entities.Villa;
import mkanak_spring.model.filters.PostSpecificationBuilder;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class PostDAO {
    @Autowired
    ApartmentRepo apartmentRepo;
    @Autowired
    VillaRepo villaRepo;
    @Autowired
    PropertyPictureRepo propertyPictureRepo;
    @Autowired
    PostRepo postRepo;
    @Autowired
    SavedPostsRepo savedPostsRepo;


    public void saveApartment(Apartment property) {
        apartmentRepo.save(property);
    }

    public void saveVilla(Villa property) {
        villaRepo.save(property);
    }

    public void saveAllPictures(List<PropertyPicture> propertyPictureList) {
        propertyPictureRepo.saveAll(propertyPictureList);
    }

    public List<Post> getAllPosts(ViewingPreference preference) {
        if(preference!=null && preference.isFiltered()
                && Objects.equals(preference.getFilterPreference().getPropertyType(), "apartment")
                && preference.getFilterPreference().isStudentHousing()) // need to filter by student housing
            return this.getPostsByApartmentDetails(preference);

        PostSpecificationBuilder pb = new PostSpecificationBuilder(preference);
        Specification<Post> sps = pb.build();
        if(sps==null)
            return postRepo.findAll();
        else
            return postRepo.findAll(sps);
    }

    public List<Post> getPostsByApartmentDetails(ViewingPreference preference){
        List<Long> studentHouseIDs= apartmentRepo.getStudentHousingIDs(preference.getFilterPreference().isStudentHousing());
        PostSpecificationBuilder pb = new PostSpecificationBuilder(preference);
        Specification<Post> sps = pb.build();
        if(sps==null)
            return postRepo.findByPropertyIDIn(studentHouseIDs);
        else
            return postRepo.findByPropertyIDIn(sps,studentHouseIDs);
    }


    public List<Post> getPostsByUser(ViewingPreference preference, int id){
        PostSpecificationBuilder pb = new PostSpecificationBuilder(preference,id);
        Specification<Post> sps = pb.build();
        if(sps==null)
            return postRepo.findAll();
        else
            return postRepo.findAll(sps);
    }


    public List<Post> getSavedPostsByUserID(ViewingPreference preference, int id){
        List<Long> postIDsSaved = savedPostsRepo.getUserSavedPostsIDs((long) id);
        PostSpecificationBuilder pb = new PostSpecificationBuilder(preference);
        Specification<Post> sps = pb.build();
        if(sps==null)
            return postRepo.findByPropertyIDIn(postIDsSaved);
        else
            return postRepo.findByPropertyIDIn(sps,postIDsSaved);
    }

}
