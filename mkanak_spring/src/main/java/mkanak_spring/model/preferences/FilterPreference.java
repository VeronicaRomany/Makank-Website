package mkanak_spring.model.preferences;

public class FilterPreference {
    private String purchaseChoice="any";
    private String propertyType="any";
    private int minPrice=-1;
    private int maxPrice=-1;
    private int minArea=-1;
    private int maxArea=-1;
    private String infoSearchWord="";
    private boolean withPictures=false;
    private boolean studentHousing=false;
    private String citySearchWord="any";

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
