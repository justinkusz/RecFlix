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
    
    public ArrayList<Movie> topMovies(int k){
        String query = "SELECT DISTINCT title, year, rtAudienceScore, rtPictureURL,"
                + "imdbPictureURL FROM movies "
                + "ORDER BY rtAudienceScore DESC "
                + "LIMIT " + k;
        ResultSet rs = null;
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                String title = rs.getString("title");
                int year = rs.getInt("year");
                int rtAudienceScore = rs.getInt("rtAudienceScore");
                String rtPictureURL = rs.getString("rtPictureURL");
                String imdbPictureURL = rs.getString("imdbPictureURL");
                movieList.add(new Movie(title, year, imdbPictureURL, rtPictureURL, rtAudienceScore));
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
}
