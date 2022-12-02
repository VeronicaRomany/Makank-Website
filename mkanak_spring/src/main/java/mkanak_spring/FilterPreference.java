package mkanak_spring;

public class FilterPreference {
    private String purchaseChoice;
    private String propertyType;
    private boolean withPictures;
    private boolean studentHousing;
    private String addressSearchWord;
    private String citySearchWord;

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

    public String getAddressSearchWord() {
        return addressSearchWord;
    }

    public void setAddressSearchWord(String addressSearchWord) {
        this.addressSearchWord = addressSearchWord;
    }

    public String getCitySearchWord() {
        return citySearchWord;
    }

    public void setCitySearchWord(String citySearchWord) {
        this.citySearchWord = citySearchWord;
    }
}
