package cs4721;

/**
 *
 * @author justin
 */
public class Movie {
    private int id;
    private String title;
    private String imdbPictureURL;
    private int year;
    private int rtAudienceScore;
    private String rtPictureURL;
    
    public Movie(int id, String title, int year, String imdbPictureURL,
            String rtPictureURL, int rtAudienceScore){
        this.id = id;
        this.title = title;
        this.year = year;
        this.imdbPictureURL = imdbPictureURL;
        this.rtPictureURL = rtPictureURL;
        this.rtAudienceScore = rtAudienceScore;
    }
    
    public int getId(){
        return this.id;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public String getImdbPictureURL(){
        return this.imdbPictureURL;
    }
    
    public int getYear(){
        return this.year;
    }
    
    public int getRtAudienceScore(){
        return this.rtAudienceScore;
    }
    
    public String getRtPictureURL(){
        return this.rtPictureURL;
    }
    
    public String toString(){
        return this.title + " (" + this.year + "), " + this.rtAudienceScore;
    }
}
