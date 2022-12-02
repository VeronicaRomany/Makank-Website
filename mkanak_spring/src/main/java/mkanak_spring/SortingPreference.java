package mkanak_spring;

public class SortingPreference {
    private boolean ascending;
    private String sortingCriteria;

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    public String getSortingCriteria() {
        return sortingCriteria;
    }

    public void setSortingCriteria(String sortingCriteria) {
        this.sortingCriteria = sortingCriteria;
    }
}
