package mkanak_spring.model;

public class FilterPreference {
    private String purchaseChoice;
    private String propertyType;
    //added
    private int minPrice;
    private int maxPrice;
    private int minArea;
    private int maxArea;
    private String university;
    private String infoSearchWord;
    //
    private boolean withPictures;
    private boolean studentHousing;
    private String citySearchWord;


    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getInfoSearchWord() {
        return infoSearchWord;
    }

    public void setInfoSearchWord(String infoSearchWord) {
        this.infoSearchWord = infoSearchWord;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getMinArea() {
        return minArea;
    }

    public void setMinArea(int minArea) {
        this.minArea = minArea;
    }

    public int getMaxArea() {
        return maxArea;
    }

    public void setMaxArea(int maxArea) {
        this.maxArea = maxArea;
    }

    public String getPurchaseChoice() {
        return purchaseChoice;
    }

    public void setPurchaseChoice(String purchaseChoice) {
        this.purchaseChoice = purchaseChoice;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public boolean isWithPictures() {
        return withPictures;
    }

    public void setWithPictures(boolean withPictures) {
        this.withPictures = withPictures;
    }

    public boolean isStudentHousing() {
        return studentHousing;
    }

    public void setStudentHousing(boolean studentHousing) {
        this.studentHousing = studentHousing;
    }

    public String getCitySearchWord() {
        return citySearchWord;
    }

    public void setCitySearchWord(String citySearchWord) {
        this.citySearchWord = citySearchWord;
    }
}
