package mkanak_spring.model.filters;

import mkanak_spring.model.entities.Post;
import mkanak_spring.model.preferences.FilterPreference;
import mkanak_spring.model.preferences.SortingPreference;
import mkanak_spring.model.preferences.ViewingPreference;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostSpecificationBuilderTest {
    private PostSpecificationBuilder builder;

    @Test
    void buildWithNullPreference() {
        builder = new PostSpecificationBuilder(null);
        Specification<Post> spcs = builder.build();
        assertNull(spcs);
    }

    @Test
    void buildWithEmptyPreferenceNotSortedNotFiltered() {
        ViewingPreference v = getEmptyPreference();
        builder = new PostSpecificationBuilder(v);
        Specification<Post> spcs = builder.build();
        assertNull(spcs);
    }

    @Test
    void buildWithFilteredPreferencePriceMax() {
        ViewingPreference v = getEmptyPreference();
        v.setFiltered(true);
        v.getFilterPreference().setMaxPrice(5000000);
        builder = new PostSpecificationBuilder(v);
        Specification<Post> spcs = builder.build();
        assertNotNull(spcs);
    }

    @Test
    void buildWithFilteredPreferencePriceMin() {
        ViewingPreference v = getEmptyPreference();
        v.setFiltered(true);
        v.getFilterPreference().setMinPrice(100);
        builder = new PostSpecificationBuilder(v);
        Specification<Post> spcs = builder.build();
        assertNotNull(spcs);
    }

    @Test
    void buildWithFilteredPreferencePriceRange() {
        ViewingPreference v = getEmptyPreference();
        v.setFiltered(true);
        v.getFilterPreference().setMinPrice(100);
        v.getFilterPreference().setMaxPrice(5000000);
        builder = new PostSpecificationBuilder(v);
        Specification<Post> spcs = builder.build();
        assertNotNull(spcs);
    }

    @Test
    void buildWithFilteredPreferencePriceRangeNegative() {
        ViewingPreference v = getEmptyPreference();
        v.setFiltered(true);
        v.getFilterPreference().setMinPrice(-1);
        v.getFilterPreference().setMaxPrice(-1);
        builder = new PostSpecificationBuilder(v);
        Specification<Post> spcs = builder.build();
        assertNull(spcs);
    }

    @Test
    void buildWithFilteredPreferenceAreaRange() {
        ViewingPreference v = getEmptyPreference();
        v.setFiltered(true);
        v.getFilterPreference().setMinArea(100);
        v.getFilterPreference().setMaxArea(5000000);
        builder = new PostSpecificationBuilder(v);
        Specification<Post> spcs = builder.build();
        assertNotNull(spcs);
    }

    @Test
    void buildWithFilteredPreferenceAreaMin() {
        ViewingPreference v = getEmptyPreference();
        v.setFiltered(true);
        v.getFilterPreference().setMinArea(100);
        builder = new PostSpecificationBuilder(v);
        Specification<Post> spcs = builder.build();
        assertNotNull(spcs);
    }

    @Test
    void buildWithFilteredPreferenceAreaMax() {
        ViewingPreference v = getEmptyPreference();
        v.setFiltered(true);
        v.getFilterPreference().setMaxArea(100100);
        builder = new PostSpecificationBuilder(v);
        Specification<Post> spcs = builder.build();
        assertNotNull(spcs);
    }

    @Test
    void buildWithFilteredPreferenceAreaNegatives() {
        ViewingPreference v = getEmptyPreference();
        v.setFiltered(true);
        v.getFilterPreference().setMinArea(-1);
        v.getFilterPreference().setMaxArea(-1);
        builder = new PostSpecificationBuilder(v);
        Specification<Post> spcs = builder.build();
        assertNull(spcs);
    }


    @Test
    void buildWithInfoSearchWord() {
        ViewingPreference v = getEmptyPreference();
        v.setFiltered(true);
        v.getFilterPreference().setInfoSearchWord("test");
        builder = new PostSpecificationBuilder(v);
        Specification<Post> spcs = builder.build();
        assertNotNull(spcs);
    }

    @Test
    void buildWithInfoSearchEmpty() {
        ViewingPreference v = getEmptyPreference();
        v.setFiltered(true);
        v.getFilterPreference().setInfoSearchWord("");
        builder = new PostSpecificationBuilder(v);
        Specification<Post> spcs = builder.build();
        assertNull(spcs);
    }



    @Test
    void buildWithIDforUser() {
        ViewingPreference v = getEmptyPreference();
        int id =10;
        builder = new PostSpecificationBuilder(v,id);
        Specification<Post> spcs = builder.build();
        assertNotNull(spcs);
    }


    @Test
    void buildWithIDsforPosts() {
        ViewingPreference v = getEmptyPreference();
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        builder = new PostSpecificationBuilder(v,ids);
        Specification<Post> spcs = builder.build();
        assertNotNull(spcs);
    }

    @Test
    void buildWithIDsforPostsAndIDforUser() {
        ViewingPreference v = getEmptyPreference();
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        int id =10;
        builder = new PostSpecificationBuilder(v,id,ids);
        Specification<Post> spcs = builder.build();
        assertNotNull(spcs);
    }


    @Test
    void buildWithPurchaseType() {
        ViewingPreference v = getEmptyPreference();
        v.setFiltered(true);
        v.getFilterPreference().setPurchaseChoice("rent");
        builder = new PostSpecificationBuilder(v);
        Specification<Post> spcs = builder.build();
        assertNotNull(spcs);
    }

    @Test
    void buildPictures() {
        ViewingPreference v = getEmptyPreference();
        v.setFiltered(true);
        v.getFilterPreference().setWithPictures(true);
        builder = new PostSpecificationBuilder(v);
        Specification<Post> spcs = builder.build();
        assertNotNull(spcs);
    }

    @Test
    void buildWithoutPicturesReturnNull() {
        ViewingPreference v = getEmptyPreference();
        v.setFiltered(true);
        v.getFilterPreference().setWithPictures(false);
        builder = new PostSpecificationBuilder(v);
        Specification<Post> spcs = builder.build();
        assertNull(spcs);
    }

    @Test
    void buildWithPurchaseTypeAny() {
        ViewingPreference v = getEmptyPreference();
        v.setFiltered(true);
        v.getFilterPreference().setPurchaseChoice("any");
        builder = new PostSpecificationBuilder(v);
        Specification<Post> spcs = builder.build();
        assertNull(spcs);
    }

    @Test
    void buildWithPropertyType() {
        ViewingPreference v = getEmptyPreference();
        v.setFiltered(true);
        v.getFilterPreference().setPropertyType("apartment");
        builder = new PostSpecificationBuilder(v);
        Specification<Post> spcs = builder.build();
        assertNotNull(spcs);
    }


    @Test
    void buildWithPropertyTypeAny() {
        ViewingPreference v = getEmptyPreference();
        v.setFiltered(true);
        v.getFilterPreference().setPropertyType("any");
        builder = new PostSpecificationBuilder(v);
        Specification<Post> spcs = builder.build();
        assertNull(spcs);
    }

    @Test
    void buildWithCityAny() {
        ViewingPreference v = getEmptyPreference();
        v.setFiltered(true);
        v.getFilterPreference().setCitySearchWord("any");
        builder = new PostSpecificationBuilder(v);
        Specification<Post> spcs = builder.build();
        assertNull(spcs);
    }

    @Test
    void buildWithCity() {
        ViewingPreference v = getEmptyPreference();
        v.setFiltered(true);
        v.getFilterPreference().setCitySearchWord("cairo");
        builder = new PostSpecificationBuilder(v);
        Specification<Post> spcs = builder.build();
        assertNotNull(spcs);
    }


    @Test
    void buildWithSortingDate() {
        ViewingPreference v = getEmptyPreference();
        v.setSorted(true);
        v.getSortingPreference().setAscending(true);
        v.getSortingPreference().setSortingCriteria("publishDate");
        builder = new PostSpecificationBuilder(v);
        Specification<Post> spcs = builder.build();
        Sort sort = builder.getSort();
        assertNull(spcs);
        assertNotNull(sort);
    }

    @Test
    void buildWithSortingPrice() {
        ViewingPreference v = getEmptyPreference();
        v.setSorted(true);
        v.getSortingPreference().setAscending(true);
        v.getSortingPreference().setSortingCriteria("price");
        builder = new PostSpecificationBuilder(v);
        Specification<Post> spcs = builder.build();
        Sort sort = builder.getSort();
        assertNull(spcs);
        assertNotNull(sort);
    }

    @Test
    void buildWithSortingArea() {
        ViewingPreference v = getEmptyPreference();
        v.setSorted(true);
        v.getSortingPreference().setAscending(true);
        v.getSortingPreference().setSortingCriteria("area");
        builder = new PostSpecificationBuilder(v);
        Specification<Post> spcs = builder.build();
        Sort sort = builder.getSort();
        assertNull(spcs);
        assertNotNull(sort);
    }

    @Test
    void buildWithSortingAreaDescending() {
        ViewingPreference v = getEmptyPreference();
        v.setSorted(true);
        v.getSortingPreference().setAscending(false);
        v.getSortingPreference().setSortingCriteria("area");
        builder = new PostSpecificationBuilder(v);
        Specification<Post> spcs = builder.build();
        Sort sort = builder.getSort();
        assertNull(spcs);
        assertNotNull(sort);
    }

    @Test
    void buildWithSortingDummyValues() {
        ViewingPreference v = getEmptyPreference();
        v.setSorted(true);
        v.getSortingPreference().setAscending(true);
        v.getSortingPreference().setSortingCriteria("garbage");
        builder = new PostSpecificationBuilder(v);
        Specification<Post> spcs = builder.build();
        Sort sort = builder.getSort();
        assertNull(spcs);
        assertNull(sort);
    }







    private ViewingPreference getEmptyPreference(){
        ViewingPreference v = new ViewingPreference();
        v.setFiltered(false);
        v.setFilterPreference(new FilterPreference());
        v.setSortingPreference(new SortingPreference());
        v.setSorted(false);
        return v;
    }
}