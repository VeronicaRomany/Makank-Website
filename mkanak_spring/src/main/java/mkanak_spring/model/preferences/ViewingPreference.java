package mkanak_spring.model.preferences;

public class ViewingPreference {
    private boolean sorted;
    private boolean filtered;
    private FilterPreference filterPreference;
    private SortingPreference sortingPreference;


    public boolean isSorted() {
        return sorted;
    }

    public void setSorted(boolean sorted) {
        this.sorted = sorted;
    }

    public boolean isFiltered() {
        return filtered;
    }

    public void setFiltered(boolean filtered) {
        this.filtered = filtered;
    }

    public FilterPreference getFilterPreference() {
        return filterPreference;
    }

    public void setFilterPreference(FilterPreference filterPreference) {
        this.filterPreference = filterPreference;
    }

    public SortingPreference getSortingPreference() {
        return sortingPreference;
    }

    public void setSortingPreference(SortingPreference sortingPreference) {
        this.sortingPreference = sortingPreference;
    }
}
