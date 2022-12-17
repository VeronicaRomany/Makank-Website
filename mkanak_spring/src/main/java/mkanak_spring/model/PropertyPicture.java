package mkanak_spring.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "property_pictures")
@NoArgsConstructor
@IdClass(PropertyPictureId.class)
public class PropertyPicture {
    @Id
    @Column(name = "post_id")
    private Long propertyID;
    @Id
    @Column(name = "pic_link")
    private String pictureLink;

    public PropertyPicture(Long propertyID, String pictureLink) {
        this.propertyID = propertyID;
        this.pictureLink = pictureLink;
    }

    public Long getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(Long propertyID) {
        this.propertyID = propertyID;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }
}
