import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SeriesTest {
    private Series series;
    private SeriesModel testSeries;

    @BeforeEach
    public void setUp() {
        series = new Series();
        testSeries = new SeriesModel("101", "Test Series", 12, 10);
        series.DeleteSeries("101");
        series.DeleteSeries("999");
        series.CaptureSeries();
    }

    @Test
    public void TestSearchSeries() {

        series.getSeriesList().add(testSeries);


        SeriesModel foundSeries = series.SearchSeries("101");


        assertNotNull(foundSeries, "Series should be found");
        assertEquals("101", foundSeries.getSeriesId(), "Series ID should match");
        assertEquals("Test Series", foundSeries.getSeriesName(), "Series name should match");
    }

    @Test
    public void TestSearchSeries_SeriesNotFound() {

        SeriesModel notFoundSeries = series.SearchSeries("999");


        assertNull(notFoundSeries, "Series should not be found");
    }

    @Test
    public void TestUpdateSeries() {

        series.getSeriesList().add(testSeries);


        boolean result = series.UpdateSeries("101", "Updated Series", 14, 15);


        assertTrue(result, "Update should be successful");
        SeriesModel updatedSeries = series.SearchSeries("101");
        assertEquals("Updated Series", updatedSeries.getSeriesName(), "Name should be updated");
        assertEquals(14, updatedSeries.getAgeRestriction(), "Age restriction should be updated");
        assertEquals(15, updatedSeries.getNumberOfEpisodes(), "Episode count should be updated");
    }

    @Test
    public void TestDeleteSeries() {

        series.getSeriesList().add(testSeries);


        boolean result = series.DeleteSeries("101");


        assertTrue(result, "Delete should be successful");
        assertNull(series.SearchSeries("101"), "Series should no longer exist");
    }

    @Test
    public void TestDeleteSeries_SeriesNotFound() {

        boolean result = series.DeleteSeries("999");


        assertFalse(result, "Delete should fail for non-existent series");
    }

    @Test
    public void TestSeriesAgeRestriction_AgeValid() {

        assertTrue(series.isValidAgeRestriction(12), "12 should be valid");
        assertTrue(series.isValidAgeRestriction(2), "2 should be valid");
        assertTrue(series.isValidAgeRestriction(18), "18 should be valid");
    }

    @Test
    public void TestSeriesAgeRestriction_SeriesAgeInValid() {
        
        assertFalse(series.isValidAgeRestriction(1), "1 should be invalid");
        assertFalse(series.isValidAgeRestriction(19), "19 should be invalid");
        assertFalse(series.isValidAgeRestriction(-5), "Negative should be invalid");
    }
}