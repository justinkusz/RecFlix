/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs4721;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author justin
 */
public class MoviesDAO {
    private Connection con;
    private Statement stmt;
    
    /**
     * Returns an ArrayList of the top N movies in descending order of
     * Rotten Tomatoes audience score.
     * @param   limit number of top movies to return
     * @return  ArrayList of Movie objects
     */
    public ArrayList<Movie> getTopMovies(int limit){
        String query = "SELECT DISTINCT id, title, year, rtAudienceScore, "
                + "rtPictureURL, imdbPictureURL FROM movies "
                + "ORDER BY rtAudienceScore DESC "
                + "LIMIT " + limit;
        ResultSet rs = null;
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt("id");
                String title = rs.getString("title").trim();
                int year = rs.getInt("year");
                int rtAudienceScore = rs.getInt("rtAudienceScore");
                String rtPictureURL = rs.getString("rtPictureURL").trim();
                String imdbPictureURL = rs.getString("imdbPictureURL").trim();
                movieList.add(new Movie(id, title, year, imdbPictureURL, 
                        rtPictureURL, rtAudienceScore));
            }
            con.close();
            stmt.close();
            rs.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return movieList;
    }
    
    /**
     * Returns an ArrayList of the top N movies in the given genre, in
     * descending order of Rotten Tomatoes audience score
     * @param genre movie genre
     * @param limit number of top movies to return
     * @return ArrayList of Movie objects
     */
    public ArrayList<Movie> getTopMovies(String genre, int limit){
        String query = "SELECT DISTINCT id, title, year, rtAudienceScore, "
                + "rtPictureURL, imdbPictureURL FROM movies, movie_genres "
                + "WHERE movieID=id AND genre LIKE '%" + genre + "%' "
                + "ORDER BY rtAudienceScore DESC "
                + "LIMIT " + limit;
        ResultSet rs = null;
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt("id");
                String title = rs.getString("title").trim();
                int year = rs.getInt("year");
                int rtAudienceScore = rs.getInt("rtAudienceScore");
                String rtPictureURL = rs.getString("rtPictureURL").trim();
                String imdbPictureURL = rs.getString("imdbPictureURL").trim();
                movieList.add(new Movie(id, title, year, imdbPictureURL, 
                        rtPictureURL, rtAudienceScore));
            }
            con.close();
            stmt.close();
            rs.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return movieList;
    }
    
    /**
     * Returns ArrayList of movies with title containing specified string,
     * ordered by year and title.
     * @param title string to search for in movie titles
     * @return ArrayList of Movie objects
     */
    public ArrayList<Movie> getMoviesByTitle(String title){
        String query = "SELECT DISTINCT id, title, year, rtAudienceScore, rtPictureURL, "
                + "imdbPictureURL FROM movies "
                + "WHERE title LIKE '%" + title + "%' "
                + "ORDER BY year, title ";
        ResultSet rs = null;
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt("id");
                String movie_title = rs.getString("title").trim();
                int year = rs.getInt("year");
                int rtAudienceScore = rs.getInt("rtAudienceScore");
                String rtPictureURL = rs.getString("rtPictureURL").trim();
                String imdbPictureURL = rs.getString("imdbPictureURL").trim();
                movieList.add(new Movie(id, movie_title,year,imdbPictureURL,
                        rtPictureURL,rtAudienceScore));
            }
            con.close();
            stmt.close();
            rs.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return movieList;
    }
    
    /**
     * Returns ArrayList of movies with director names containing the
     * specified string, ordered by year and movie title.
     * @param director  director name to search for
     * @return          ArrayList of Movie objects
     */
    public ArrayList<Movie> getMoviesByDirector(String director){
        String query = "SELECT id, title, year, rtAudienceScore, rtPictureURL, "
                + "imdbPictureURL FROM movies, movie_directors "
                + "WHERE id=movieID AND directorName LIKE '%" + director + "%' "
                + "GROUP BY title "
                + "ORDER BY year, title";
        ResultSet rs = null;
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt("id");
                String movie_title = rs.getString("title").trim();
                int year = rs.getInt("year");
                int rtAudienceScore = rs.getInt("rtAudienceScore");
                String rtPictureURL = rs.getString("rtPictureURL").trim();
                String imdbPictureURL = rs.getString("imdbPictureURL").trim();
                movieList.add(new Movie(id, movie_title,year,imdbPictureURL,
                        rtPictureURL,rtAudienceScore));
            }
            con.close();
            stmt.close();
            rs.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return movieList;
    }
    
    /**
     * Returns ArrayList of movies with actor names containing the
     * specified string, ordered by year and movie title.
     * @param actor name of actor to search for
     * @return      ArrayList of Movie objects
     */
    public ArrayList<Movie> getMoviesByActor(String actor){
        String query = "SELECT id, title, year, rtAudienceScore, rtPictureURL, "
                + "imdbPictureURL FROM movies, movie_actors "
                + "WHERE id=movieID AND actorName LIKE '%" + actor + "%' "
                + "GROUP BY title "
                + "ORDER BY year, title";
        ResultSet rs = null;
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt("id");
                String movie_title = rs.getString("title").trim();
                int year = rs.getInt("year");
                int rtAudienceScore = rs.getInt("rtAudienceScore");
                String rtPictureURL = rs.getString("rtPictureURL").trim();
                String imdbPictureURL = rs.getString("imdbPictureURL").trim();
                movieList.add(new Movie(id, movie_title,year,imdbPictureURL,
                        rtPictureURL,rtAudienceScore));
            }
            con.close();
            stmt.close();
            rs.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return movieList;
    }
    
    /**
     * Returns ArrayList of movies with tags containing the specified
     * string, ordered by Rotten Tomatoes audience score.
     * @param tag   tag to search for
     * @return      ArrayList of Movie objects
     */
    public ArrayList<Movie> getMoviesByTag(String tag){
        String query = "SELECT id, title, year, rtAudienceScore, rtPictureURL, "
                + "imdbPictureURL FROM movies, movie_tags "
                + "WHERE id=movieID AND tagID IN (SELECT id FROM tags "
                + "WHERE value LIKE '%" + tag + "%') "
                + "GROUP BY title "
                + "ORDER BY rtAudienceScore DESC";
        ResultSet rs = null;
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt("id");
                String movie_title = rs.getString("title").trim();
                int year = rs.getInt("year");
                int rtAudienceScore = rs.getInt("rtAudienceScore");
                String rtPictureURL = rs.getString("rtPictureURL").trim();
                String imdbPictureURL = rs.getString("imdbPictureURL").trim();
                movieList.add(new Movie(id, movie_title,year,imdbPictureURL,
                        rtPictureURL,rtAudienceScore));
            }
            con.close();
            stmt.close();
            rs.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return movieList;
    }
    
    /**
     * Returns ArrayList of top 10 scored directors with a minimum number
     * of movies specified by user, ordered by averageRotten Tomatoes 
     * audience score.
     * @param minMovies minimum number of movies directed
     * @return          ArrayList of Strings
     */
    public ArrayList<String> getTopDirectors(int minMovies){
        String query = "SELECT directorName, AVG(rtAudienceScore) "
                + "FROM movie_directors, movies "
                + "WHERE id=movieID "
                + "GROUP BY directorName "
                + "HAVING COUNT(id) >= " + minMovies + " "
                + "ORDER BY AVG(rtAudienceScore) DESC "
                + "LIMIT 10";
        ResultSet rs = null;
        ArrayList<String> directorList = new ArrayList<String>();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                String dirName = rs.getString("directorName").trim();
                double avgScore = rs.getDouble("AVG(rtAudienceScore)");
                directorList.add(dirName + "\t" + avgScore);
            }
            con.close();
            stmt.close();
            rs.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return directorList;
    }
    
    /**
     * Returns ArrayList of top 10 scored actors with a minimum number
     * of movies specified by user, ordered by average Rotten Tomatoes
     * audience score.
     * @param minMovies minimum number of movies acted in
     * @return          ArrayList of Strings
     */
    public ArrayList<String> getTopActors(int minMovies){
        String query = "SELECT actorName, AVG(rtAudienceScore) "
                + "FROM movie_actors, movies "
                + "WHERE id=movieID "
                + "GROUP BY actorName "
                + "HAVING COUNT(id) >= " + minMovies + " "
                + "ORDER BY AVG(rtAudienceScore) DESC "
                + "LIMIT 10";
        ResultSet rs = null;
        ArrayList<String> actorList = new ArrayList<String>();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                String actorName = rs.getString("actorName").trim();
                double avgScore = rs.getDouble("AVG(rtAudienceScore)");
                actorList.add(actorName + "\t" + avgScore);
            }
            con.close();
            stmt.close();
            rs.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return actorList;
    }
    
    /**
     * Returns ArrayList of Strings containing user tags for given movie
     * @param movieID   movieID of movie
     * @return          ArrayList of Strings containing user tags for given movie
     */
    public ArrayList<String> getUserTags(int movieID){
        ArrayList<String> tagList = new ArrayList<String>();
        String query = "SELECT value FROM tags, movie_tags "
                + "WHERE movieID = " + movieID + " "
                + "AND id=tagID";
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                String tag = rs.getString("value").trim();
                tagList.add(tag);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return tagList;
    }
    
    /**
     * Returns an ArrayList of Strings containing movie title and 
     * rating, ordered by date/time of rating, in ascending order.
     * @param userID    userID of user
     * @return          ArrayList of Strings containing the movie title and 
     *                      rating, separated by a tab(\t).
     */
    public ArrayList<String> getTimeLine(int userID){
        ArrayList<String> timeLine = new ArrayList<String>();
        ResultSet rs = null;
        String query = "SELECT title, rating FROM movies, user_ratedmovies as r "
                + "WHERE id=movieID AND userID=" + userID + " "
                + "ORDER BY r.date_year, r.date_month, r.date_day, r.date_hour,"
                + " r.date_minute, r.date_second ASC";
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                String title = rs.getString("title").trim();
                double rating = rs.getDouble("rating");
                timeLine.add(title + "\t" + rating);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return timeLine;
    }
    
    /**
     * Returns an ArrayList of Strings containing each genre of movie
     * for which the user has rated, followed by the percentage of movies
     * in that genre out of the total number of movies rated by the user.
     * Genre and percentage separated by tab (\t), genres listed in descending
     * order by percentage.
     * @param userID    userID of user
     * @return          ArrayList of Strings containing genre and corresponding
     *                   percentage, separated by a tab(\t).
     */
    public ArrayList<String> getGenreBreakdown(int userID){
        ArrayList<String> breakdown = new ArrayList<String>();
        String query = "SELECT genre, round(count(*)/(SELECT count(*) "
                + "FROM user_ratedmovies "
                + "WHERE userID=" + userID + ")*100) as percentage "
                + "FROM user_ratedmovies, movie_genres "
                + "WHERE userID=" + userID + " "
                + "AND user_ratedmovies.movieID=movie_genres.movieID "
                + "GROUP BY genre "
                + "ORDER BY percentage DESC";
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                String tag = rs.getString("genre").trim();
                int percentage = rs.getInt("percentage");
                breakdown.add(tag + "\t" + percentage + "%");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return breakdown;
    }
}
