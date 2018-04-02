package tests.filter;

import livelib.pages.GenresPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.BaseTest;

import static livelib.utils.PropertiesReader.getPropertyValue;

public class FilteringTests extends BaseTest {

    private String genreName;

    @BeforeClass
    public void setGenreNameFromProperties() {
        genreName = getPropertyValue("GENRE_FOR_FILTERING");
    }

    @Test
    public void correctFilteringByGenreViaMainPageTest() {
        GenresPage genresPage = baseLiveLibPage
                .clickGenres()
                .clickGenreLink(genreName);
        Assert.assertTrue(genresPage.isBookFromNeededGenreExists(genreName));
    }

    @Test
    public void correctFilteringByGenreViaGenresPageTest() {
        GenresPage genresPage = baseLiveLibPage
                .navigateToGenresPage()
                .clickGenreByName(genreName);
        Assert.assertTrue(genresPage.isBookFromNeededGenreExists(genreName));
    }

}
