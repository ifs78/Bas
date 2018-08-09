package gb;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Link {

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private static String url = "jdbc:mysql://localhost:3306/fintube?autoReconnect=true&useSSL=false";

       public static void main(String[] args) {
           new TubeForm();
          try {
             queryData("insert into material (mark, density) VALUES ('X0808', '1.78')");
             queryData("select id, mark, density from material");
              while (rs.next()) getDataDB();
             System.out.println("Данные из базы получены");
       } catch (SQLException sqlEx){
           sqlEx.printStackTrace();
       } finally {
          try { con.close(); } catch(SQLException se) { se.getMessage();}
          try { stmt.close(); } catch(SQLException se) { se.getMessage();}
          try { rs.close(); } catch(SQLException se) { se.getMessage();}
       }

    }
    private static void  queryData(String query) throws SQLException  {
        con = DriverManager.getConnection(url, "root", "12345");
        stmt = con.createStatement();
        if (query.contains("select")) {
            rs = stmt.executeQuery(query);
           }
        else if (query.contains("insert")) {
            stmt.executeUpdate(query);
        }

    }
    private static void getDataDB() throws SQLException {
            int id = rs.getInt("id");
            String mark = rs.getString("mark");
            String density = rs.getString("density");
            System.out.printf("id : %d, Марка : %s, Плотность : %s%n", id, mark, density);
         }

}
