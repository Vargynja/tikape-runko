package tikape.runko.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Alue;
import java.time.*;

public class AlueDao {

    private final String dbaddress;

    public AlueDao(String dbaddress) {
        this.dbaddress = dbaddress;
    }

    public Alue findOne(Integer key) throws SQLException {
        Connection connection = DriverManager.getConnection(dbaddress);
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Alue WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");

        Alue a = new Alue(id, nimi);

        rs.close();
        stmt.close();
        connection.close();

        return a;
    }

    public List<Alue> findAll() throws SQLException {

        Connection connection = DriverManager.getConnection(dbaddress);
        PreparedStatement stmt = connection.prepareStatement("SELECT Alue.id, Alue.nimi, COUNT(Viestit.id) AS viestimaara, MAX(Viestit.paivamara) FROM Alue "
                + "INNER JOIN Keskustelu ON keskustelu.alue = alue.id "
                + "INNER JOIN Viestit ON viestit.keskustelu = keskustelu.id "
                + "GROUP BY Alue.id");

        ResultSet rs = stmt.executeQuery();
        List<Alue> alueet = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");
            Integer maara = rs.getInt("viestimaara");
            LocalDateTime tiem = rs.getTimestamp("paivamaara").toLocalDateTime();
            alueet.add(new Alue(id, nimi, maara, tiem));
        }

        rs.close();
        stmt.close();
        connection.close();

        return alueet;
    }

    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }
}
