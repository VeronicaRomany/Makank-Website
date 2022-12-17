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
    PostRepo postRepo;
    @Autowired
    ApartmentRepo apartmentRepo;
    @Autowired
    VillaRepo villaRepo;
    @Autowired
    PropertyPictureRepo propertyPictureRepo;

    public void savePost(Post post) {
        postRepo.save(post);
    }

    public List<Post> getAll() {
        return postRepo.findAll();
    }

    public void saveApartment(Apartment property) {
        apartmentRepo.save(property);
    }

    public void saveVilla(Villa property) {
        villaRepo.save(property);
    }

    public void saveAllPictures(List<PropertyPicture> propertyPictureList) {
        propertyPictureRepo.saveAll(propertyPictureList);
    }

}
