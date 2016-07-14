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
                String title = rs.getString("title");
                int year = rs.getInt("year");
                int rtAudienceScore = rs.getInt("rtAudienceScore");
                String rtPictureURL = rs.getString("rtPictureURL");
                String imdbPictureURL = rs.getString("imdbPictureURL");
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
                String title = rs.getString("title");
                int year = rs.getInt("year");
                int rtAudienceScore = rs.getInt("rtAudienceScore");
                String rtPictureURL = rs.getString("rtPictureURL");
                String imdbPictureURL = rs.getString("imdbPictureURL");
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
    
    public ArrayList<Movie> getMoviesByTitle(String title){
        String query = "SELECT id, title, year, rtAudienceScore, rtPictureURL, "
                + "imdbPictureURL FROM movies "
                + "WHERE title LIKE '%" + title + "%'";
        ResultSet rs = null;
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt("id");
                String movie_title = rs.getString("title");
                int year = rs.getInt("year");
                int rtAudienceScore = rs.getInt("rtAudienceScore");
                String rtPictureURL = rs.getString("rtPictureURL");
                String imdbPictureURL = rs.getString("imdbPictureURL");
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
                String tag = rs.getString("value");
                tagList.add(tag);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return tagList;
    }
}
