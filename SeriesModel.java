public class SeriesModel {
    private String seriesId;
    private String seriesName;
    private int ageRestriction;
    private int numberOfEpisodes;

    public SeriesModel(String seriesId, String seriesName, int ageRestriction, int numberOfEpisodes) {
        this.seriesId = seriesId;
        this.seriesName = seriesName;
        this.ageRestriction = ageRestriction;
        this.numberOfEpisodes = numberOfEpisodes;
    }

    // Getters and Setters
    public String getSeriesId() { return seriesId; }
    public String getSeriesName() { return seriesName; }
    public int getAgeRestriction() { return ageRestriction; }
    public int getNumberOfEpisodes() { return numberOfEpisodes; }

    public void setSeriesName(String seriesName) { this.seriesName = seriesName; }
    public void setAgeRestriction(int ageRestriction) { this.ageRestriction = ageRestriction; }
    public void setNumberOfEpisodes(int numberOfEpisodes) { this.numberOfEpisodes = numberOfEpisodes; }

    @Override
    public String toString() {
        return "SERIES ID: " + seriesId + "\n" +
                "SERIES NAME: " + seriesName + "\n" +
                "SERIES AGE RESTRICTION: " + ageRestriction + "\n" +
                "NUMBER OF EPISODES: " + numberOfEpisodes;
    }
}