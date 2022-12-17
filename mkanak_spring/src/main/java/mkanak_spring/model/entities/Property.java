package mkanak_spring.model.entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@NoArgsConstructor
@Table(name = "property")
public class Property {
    @Id
    @Column(name = "property_id")
    private long propertyID;
    @Column(name = "seller_id")
    private long sellerID;
    @Column(name = "rooms", nullable = false)
    private int roomNumber;
    @Column(name = "bathrooms", nullable = false)
    private int bathroomNumber;
    @Column(name= "price", nullable = false)
    private int price;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "property_address", nullable = false)
    private String address;
    @Column(name = "area", nullable = false)
    private int area;
    @Column(name = "for_rent", nullable = false)
    private boolean rent;
    @Column(name = "info")
    private String info;
    @Column(name = "property_type", nullable = false)
    private String type;
    @Column(name = "has_pictures")
    private boolean hasPictures;
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "property_id")
    private Post post;


    public long getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(long propertyID) {
        this.propertyID = propertyID;
    }

    public long getSellerID() {
        return sellerID;
    }

    public void setSellerID(long sellerID) {
        this.sellerID = sellerID;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getBathroomNumber() {
        return bathroomNumber;
    }

    public void setBathroomNumber(int bathroomNumber) {
        this.bathroomNumber = bathroomNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public boolean isRent() {
        return rent;
    }

    public void setRent(boolean rent) {
        this.rent = rent;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isHasPictures() {
        return hasPictures;
    }

    public void setHasPictures(boolean hasPictures) {
        this.hasPictures = hasPictures;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }


}
