package tikape.runko.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Viesti;

public class ViestiDao {

    private String dbaddress;

    public ViestiDao(String dbaddress) {
        this.dbaddress = dbaddress;
    }

    public Viesti findOne(Integer key) throws SQLException {
        Connection connection = DriverManager.getConnection(dbaddress);
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Opiskelija WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String viesti = rs.getString("viesti");
        String nimimerkki = rs.getString("nimimerkki");
        Date date = rs.getDate("päivämäärä");
        Integer keskustelu = rs.getInt("keskustelu");
        Viesti v = new Viesti(id, viesti, nimimerkki, date, keskustelu);

        rs.close();
        stmt.close();
        connection.close();

        return v;
    }

    public List<Viesti> findAll() throws SQLException {

        Connection connection = DriverManager.getConnection(dbaddress);
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Viestit");

        ResultSet rs = stmt.executeQuery();
        List<Viesti> viestit = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String viesti = rs.getString("viesti");
            String nimimerkki = rs.getString("nimimerkki");
            Date date = rs.getDate("päivämäärä");
            Integer keskustelu = rs.getInt("keskustelu");
            viestit.add(new Viesti(id, viesti, nimimerkki, date, keskustelu));
        }

        rs.close();
        stmt.close();
        connection.close();

        return viestit;
    }

    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }
}
