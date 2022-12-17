package mkanak_spring.model.dao;

import mkanak_spring.model.entities.Apartment;
import mkanak_spring.model.entities.Post;
import mkanak_spring.model.entities.PropertyPicture;
import mkanak_spring.model.entities.Villa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public void saveApartment(Apartment property) {
        apartmentRepo.save(property);
    }

    public void saveVilla(Villa property) {
        villaRepo.save(property);
    }

    public void saveAllPictures(List<PropertyPicture> propertyPictureList) {
        propertyPictureRepo.saveAll(propertyPictureList);
    }

    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

}
