package mkanak_spring.model.services;

import mkanak_spring.model.entities.*;
import mkanak_spring.model.repositories.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {
    //  private static JsonToObject converter = new JsonToObject();
    @Mock
    private JsonToObject converter;
    @Mock
    private ApartmentRepo apartmentRepo;
    @Mock
    private VillaRepo villaRepo;
    @Mock
    private PropertyPictureRepo propertyPictureRepo;
    @Mock
    private PostRepo postRepo;
    @Mock
    private SavedPostsRepo savedPostsRepo;
    @Mock
    private PropertyRepo propertyRepo;

    @InjectMocks
    PostServiceImpl postService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createApartmentInvocationsTest() throws ParseException {
        JSONObject post = postJSON(false);
        Apartment apartment = buildApartment(post);
        when(converter.buildApartment(post)).thenReturn(apartment);
        when(converter.buildPropertyPictures(post, 1L)).thenReturn(buildPics(post, 1L));
        postService.savePost(1L, post);
        verify(apartmentRepo, times(1)).save(apartment);
        List<PropertyPicture> pictureList = buildPics(post, 1L);
        verify(propertyPictureRepo, times(1)).saveAll(pictureList);
    }
    @Test
    void createVillaInvocationsTest() throws ParseException {
        JSONObject post = postJSON(true);
        Villa villa = buildVilla(post);
        when(converter.buildVilla(post)).thenReturn(villa);
        when(converter.buildPropertyPictures(post, 1L)).thenReturn(buildPics(post, 1L));
        postService.savePost(1L, post);
        verify(villaRepo, times(1)).save(villa);
        List<PropertyPicture> pictureList = buildPics(post, 1L);
        verify(propertyPictureRepo, times(1)).saveAll(pictureList);
    }
    @Test
    void editPostDifferentTypeToVillaInvocationsTest() throws ParseException {
        JSONObject post = postJSON(true);
        Villa villa = buildVilla(post);
        JSONObject postEdit = postJSON(false);
        Apartment apartment = buildApartment(postEdit);
      //  JSONObject postEdit = postJSON(false);
        Property property = new Property();
        buildProperty(property, post);
//        when(converter.buildVilla(post)).thenReturn(villa);
        when(propertyRepo.findById(1L)).thenReturn(Optional.of(apartment));
        when(converter.buildPropertyPictures(post, 1L)).thenReturn(buildPics(post, 1L));
        postService.editPost(post);
        verify(villaRepo, times(1)).insertVilla(1L, villa.isHasGarden(), villa.getNumberOfLevels(), villa.isHasPool());
        List<PropertyPicture> pictureList = buildPics(post, 1L);
        verify(propertyPictureRepo, times(1)).deletePicturesById(1L);
        verify(propertyPictureRepo, times(1)).saveAll(pictureList);
    }
    @Test
    void editPostDifferentTypeToApartmentInvocationsTest() throws ParseException {
        JSONObject post = postJSON(true);
        Villa villa = buildVilla(post);
        JSONObject postEdit = postJSON(false);
        Apartment apartment = buildApartment(postEdit);
        //  JSONObject postEdit = postJSON(false);
        Property property = new Property();
        buildProperty(property, postEdit);
//        when(converter.buildVilla(post)).thenReturn(villa);
        when(propertyRepo.findById(1L)).thenReturn(Optional.of(villa));
        when(converter.buildPropertyPictures(postEdit, 1L)).thenReturn(buildPics(post, 1L));
        postService.editPost(postEdit);
        verify(apartmentRepo, times(1)).insertApartment(1L, apartment.isElevator(), apartment.getLevel(), apartment.isStudentHousing());
        List<PropertyPicture> pictureList = buildPics(postEdit, 1L);
        verify(propertyPictureRepo, times(1)).deletePicturesById(1L);
        verify(propertyPictureRepo, times(1)).saveAll(pictureList);
    }
    @Test
    void createPostThrows() {
        Throwable throwable = catchThrowable(() -> postService.savePost(1L, null));
        ///then
        then(throwable).isInstanceOf(NullPointerException.class);
    }
    @Test
    void editPostThrows() {
        Throwable throwable = catchThrowable(() -> postService.editPost(null));
        ///then
        then(throwable).isInstanceOf(NullPointerException.class);
    }

    @Test
    void deleteFromSavedTest() {
        JSONObject jentry = new JSONObject();
        jentry.put("postID", 1);
        jentry.put("userID", 1);
        postService.removeFromSaved(jentry);
        ArgumentCaptor<SavedPostsEntry> objectArgumentCaptor = ArgumentCaptor.forClass(SavedPostsEntry.class);
        verify(savedPostsRepo).delete(objectArgumentCaptor.capture());
        SavedPostsEntry capturedObject = objectArgumentCaptor.getValue();
        assertThat(capturedObject.getPostID()).isEqualTo(1L);
        assertThat(capturedObject.getUserID()).isEqualTo(1L);
    }

    private JSONObject postJSON(boolean isVilla) {
        JSONObject post = new JSONObject();
        post.put("area", 11);
        post.put("hasPictures", false);
        post.put("postID", 1L);
        post.put("roomNumber", 3);
        post.put("bathroomNumber", 3);
        post.put("level", 3);
        post.put("address", "smouha");
        post.put("city", "Alex");
        post.put("rent", true);
        post.put("price", 5000);
        post.put("sellerID", 5);
        List<String> pics = new ArrayList<>();
        post.put("pictures", pics);
        if(isVilla) {
            post.put("hasGarden", true);
            post.put("hasPool", true);
            post.put("type", "villa");
        }
        else {
            post.put("elevator", true);
            post.put("studentHousing", false);
            post.put("type", "apartment");
        }
        return post;
    }

    private Apartment buildApartment(JSONObject post) {
        JsonToObject convert = new JsonToObject();
        return convert.buildApartment(post);
    }
    private Villa buildVilla(JSONObject post) {
        JsonToObject convert = new JsonToObject();
        return convert.buildVilla(post);
    }
    private void buildProperty(Property property, JSONObject post) {
        JsonToObject convert = new JsonToObject();
        convert.buildPost(property, post);
    }

    private List<PropertyPicture> buildPics(JSONObject post, Long ID) throws ParseException {
        JsonToObject convert = new JsonToObject();
        return convert.buildPropertyPictures(post, ID);
    }
}