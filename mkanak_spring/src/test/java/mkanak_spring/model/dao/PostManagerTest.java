package mkanak_spring.model.dao;

import mkanak_spring.model.PostManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PostManagerTest {
    
    @Test
    void throwNullPointerExceptionOnPropertyPicturesTest() {
        JSONObject object = new JSONObject();
        PostManager postManager = new PostManager();
        assertThrows(NullPointerException.class, () -> postManager.buildPropertyPictures(object, 0L),
                "Passing a null object should throw an exception");
    }
    @Test
    void throwClassCastExceptionOnPropertyPicturesTest() {
        JSONObject object = new JSONObject();
        object.put("pictures", 0);
        PostManager postManager = new PostManager();
        assertThrows(ClassCastException.class, () -> postManager.buildPropertyPictures(object, 0L),
                "Passing an invalid object class should throw an exception");
    }

}