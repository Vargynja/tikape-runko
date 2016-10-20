package tikape.runko;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;

public class SQLQueries {

    private String dbadress;

    public SQLQueries(String dbadress) {
        this.dbadress = dbadress;
    }

    public int viestienMaara(String mika) throws SQLException { // mika = Keskustelu.id tai Alue.id
        int maara = 0;
        Connection connection = DriverManager.getConnection(dbadress);
        PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(Viesti.id) AS viestimaara FROM Alue "
                + "INNER JOIN Keskustelu ON keskustelu.alue = alue.id "
                + "INNER JOIN Viestit ON viesti.keskustelu = keskustelu.id "
                + "GROUP BY ?");

        stmt.setString(1, mika);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            maara = rs.getInt("viestimaara");

        }
        rs.close();
        stmt.close();
        connection.close();
        return maara;
    }

    public LocalDateTime uusinViesti(String mika) throws SQLException { // mika = Keskustelu.id tai Alue.id
        LocalDateTime date = null;
        Connection connection = DriverManager.getConnection(dbadress);
        PreparedStatement stmt = connection.prepareStatement("SELECT paivamaara FROM Alue "
                + "INNER JOIN Keskustelu ON keskustelu.alue = alue.id "
                + "INNER JOIN Viestit ON viesti.keskustelu = keskustelu.id "
                + "ORDER BY paivamaara DESC "
                + "LIMIT 1"
                + "GROUP BY ?");

        stmt.setString(1, mika);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            date = rs.getTimestamp("paivamaara").toLocalDateTime();
        }

        rs.close();
        stmt.close();
        connection.close();
        return date;
    }
}
