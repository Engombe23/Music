import java.sql.*;

public class Database {
    private Connection con;

    //This method executes a query and returns the number of albums for the artist with name artistName
    public int getTitles(String artistName) {
       int titleNum = 0;
        //Implement this method
        //Prevent SQL injections!
        try {
            String sql = "SELECT COUNT (album.albumid) FROM album INNER JOIN artist ON album.artistid = artist.artistid WHERE name = ? GROUP BY name";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, artistName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                titleNum = rs.getByte("count");
            }
            rs.close();
            stmt.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return titleNum;
    }

    // This method establishes a DB connection & returns a boolean status
    public boolean establishDBConnection() {
        //Implement this method
        //5 sec timeout
        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Music", "postgres", "cs2002");
            return con.isValid(5);
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }
}