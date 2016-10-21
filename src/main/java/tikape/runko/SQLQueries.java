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
                + "WHERE ? = ?"
                + "GROUP BY ? ");

        stmt.setString(1, mika);
        stmt.setString(3, mika);
        stmt.setInt(2, key);
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
        PreparedStatement stmt = connection.prepareStatement("SELECT paivamaara FROM Alue "
                + "INNER JOIN Keskustelu ON keskustelu.alue = alue.id "
                + "INNER JOIN Viestit ON viestit.keskustelu = keskustelu.id "
                + "WHERE ? = ? "
                + "GROUP BY ? "
                + "ORDER BY paivamaara DESC "
                + "LIMIT 1 ");

        stmt.setString(1, mika);
        stmt.setString(3, mika);
        stmt.setInt(2, key);
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
