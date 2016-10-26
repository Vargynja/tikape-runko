package tikape.runko;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLQueries {

    private String dbadress;

    public SQLQueries(String dbadress) {
        this.dbadress = dbadress;
    }

    public int viestienMaara(String mika, int key) throws SQLException { // mika = Keskustelu.id tai Alue.id
        int maara = 0;
        Connection connection = DriverManager.getConnection(dbadress);
        PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(Viestit.id) AS viestimaara FROM Alue "
                + "INNER JOIN Keskustelu ON keskustelu.alue = alue.id "
                + "INNER JOIN Viestit ON viestit.keskustelu = keskustelu.id "
                + "WHERE " + mika + " = " + key + " "
                + "GROUP BY " + mika);


        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            maara = rs.getInt("viestimaara");

        }
        rs.close();
        stmt.close();
        connection.close();
        return maara;
    }
     public int viestienMaaraKesk(int key) throws SQLException { // mika = Keskustelu.id tai Alue.id
        int maara = 0;
        Connection connection = DriverManager.getConnection(dbadress);
        PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(Viestit.id) AS viestimaara FROM Viestit "
                + "WHERE keskustelu = " + key + " ");


        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            maara = rs.getInt("viestimaara");

        }
        rs.close();
        stmt.close();
        connection.close();
        return maara;
    }

    public String uusinViesti(String mika, int key) throws SQLException { // mika = Keskustelu.id tai Alue.id
        String date = null;
        Connection connection = DriverManager.getConnection(dbadress);
        PreparedStatement stmt = connection.prepareStatement("SELECT paivamaara FROM Viestit "
                + "INNER JOIN Keskustelu ON viestit.keskustelu = keskustelu.id "
                + "INNER JOIN Alue ON keskustelu.alue = alue.id "                
                + "WHERE " + mika + " = " + key + " "
                + "GROUP BY " + mika + " "
                + "ORDER BY paivamaara DESC "
                + "LIMIT 1 ");

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            date = rs.getString("paivamaara");
        }

        rs.close();
        stmt.close();
        connection.close();
        return date;
    }
    
    public String uusinViestiKesk(int key) throws SQLException { // mika = Keskustelu.id tai Alue.id
        String date = null;
        Connection connection = DriverManager.getConnection(dbadress);
        PreparedStatement stmt = connection.prepareStatement("SELECT paivamaara FROM Viestit "           
                + "WHERE keskustelu = " + key + " "
                + "ORDER BY paivamaara DESC "
                + "LIMIT 1 ");

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            date = rs.getString("paivamaara");
        }

        rs.close();
        stmt.close();
        connection.close();
        return date;
    }
}
