package mkanak_spring.model.services;
import mkanak_spring.model.*;
import mkanak_spring.model.dao.PostDAO;
import mkanak_spring.model.entities.*;
import mkanak_spring.model.repositories.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    PostDAO postDAO;
    @Autowired
    PostRepo postRepo;
    @Autowired
    JsonToObjectConverter converter;
    @Autowired
    ApartmentRepo apartmentRepo;
    @Autowired
    VillaRepo villaRepo;
    @Autowired
    PropertyRepo propertyRepo;
    @Autowired
    PropertyPictureRepo propertyPictureRepo;

    @Override
    public void savePost(Long propertyID, JSONObject post) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(post.toString());
        JSONArray pictures = (JSONArray) object.get("pictures");
        if(post.get("type").toString().compareTo("villa") == 0) {
            Villa property = converter.buildVilla(post);
            property.setHasPictures(pictures.size() != 0);
            property.setPropertyID(propertyID);
            this.saveVilla(property);
            List<PropertyPicture> pictureList = converter.buildPropertyPictures(post, property.getPropertyID());
            this.savePropertyPictures(pictureList);
        }
        else {
            Apartment property = converter.buildApartment(post);
            property.setHasPictures(pictures.size() != 0);
            property.setPropertyID(propertyID);
            this.saveApartment(property);
            List<PropertyPicture> pictureList = converter.buildPropertyPictures(post, property.getPropertyID());
            this.savePropertyPictures(pictureList);
        }
    }

    @Override
    public List<Post> getHomepagePosts(JSONObject preference,int pageNum,int pageSize) {
        ViewingPreference p = converter.parseViewingPreference(preference);
        return postDAO.getAllPosts(p,pageNum,pageSize);
    }

    @Override
    public List<Post> getSavedPosts(int id, JSONObject preference,int pageNum,int pageSize) {
        ViewingPreference v = converter.parseViewingPreference(preference);
        return postDAO.getSavedPostsByUserID(id,v,pageNum,pageSize);
    }

    @Override
    public List<Post> getProfilePosts(int targetUserID, JSONObject preferences,int pageNum,int pageSize) {
        ViewingPreference v = converter.parseViewingPreference(preferences);
        return postDAO.getPostsByUser(targetUserID,v,pageNum,pageSize);
    }

    @Override
    public List<Long> getSavedPostsIDs(int userID) {
        return postDAO.getSavedPostsIDs(userID);
    }

    @Override
    public void addToSavedPosts(JSONObject saveEntry) {
        int userID = (int) saveEntry.get("userID");
        int postID = (int) saveEntry.get("postID");
        postDAO.addToSavedPosts(userID,postID);
    }

    @Override
    public void removeFromSaved(JSONObject entry) {
        int userID = (int) entry.get("userID");
        int postID = (int) entry.get("postID");
        postDAO.removeFromSavedPosts(userID,postID);
    }

    @Override
    public void deletePost(long postID) {
        propertyRepo.deleteById(postID);
    }

    @Override
    public void editPost(JSONObject post) throws ParseException {
        Long postID = ((Number) post.get("postID")).longValue();
        System.out.println("id: " + postID);
        propertyPictureRepo.deletePicturesById(postID);
        if(post.get("type").toString().compareTo(this.getProperty(postID).get().getType()) != 0) {
            Property property = new Property();
            converter.buildPost(property, post);
            List<PropertyPicture> pictureList = converter.buildPropertyPictures(post, postID);
            property.setPropertyID(postID);
            property.setHasPictures(pictureList.size() != 0);
            System.out.println("aaa: " + property.getPropertyID());
            propertyRepo.updateProperty(property.getPropertyID(), property.getRoomNumber(), property.getBathroomNumber(),
                    property.getPrice(), property.getCity(), property.getAddress(), property.getArea(), property.isRent(),
                    property.getInfo(), property.getType(), property.getHasPictures());
            propertyPictureRepo.saveAll(pictureList);
            if(post.get("type").toString().compareTo("villa") != 0) {
                villaRepo.deleteVilla(postID);
                apartmentRepo.insertApartment(postID, (boolean)post.get("elevator"),
                        (int)post.get("level"), (boolean) post.get("studentHousing"));
            }
            else {
                apartmentRepo.deleteApartment(postID);
                villaRepo.insertVilla(postID, (boolean)post.get("hasGarden"),
                        (int)post.get("level"), (boolean) post.get("hasPool"));
            }
        }
        else this.savePost(postID, post);
    }

    @Override
    public JSONObject getPostDetails(long postID) {
        return postRepo.getPostLargeView(postID);
    }

    @Override
    public Optional<Property> getProperty(long propertyID) {
        return propertyRepo.findById(propertyID);
    }
    private void saveVilla(Villa villa) {
        villaRepo.save(villa);
    }
    private void saveApartment(Apartment apartment) {
        apartmentRepo.save(apartment);
    }
    private void savePropertyPictures(List<PropertyPicture> pictureList) {
        propertyPictureRepo.saveAll(pictureList);
    }
}
