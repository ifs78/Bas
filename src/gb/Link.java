package gb;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Link {

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private static String url = "jdbc:mysql://localhost:3306/fintube?characterEncoding=utf8&autoReconnect=true&useSSL=false";
    public static String m, d;

       public static void main(String[] args) {
           TubeForm tb = new TubeForm();

           tb.setContentPane(tb.panel1);
           tb.setBounds(0,0,900,600);
           tb.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
           tb.setVisible(true);


           tb.button1.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   m = (String) tb.comboMarkTube.getSelectedItem();
                   d = (String) tb.comboMarkSheet.getSelectedItem();

                   try {
                       queryData("insert into material (mark, density) VALUES ('" + m  + "', '" + d + "')");
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
           });

          System.out.println(m + d);



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
