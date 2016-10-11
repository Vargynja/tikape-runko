
package tikape.runko.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Keskustelu;


public class KeskusteluDao {
    private String dbaddress;

    public KeskusteluDao(String dbaddress) {
        this.dbaddress = dbaddress;
    }
    
    public Keskustelu findOne(Integer key) throws SQLException {
        Connection connection = DriverManager.getConnection(dbaddress);
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Keskustelu WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");
        Integer alue = rs.getInt("alue");
        
        Keskustelu k = new Keskustelu(id, nimi, alue);

        rs.close();
        stmt.close();
        connection.close();

        return k;
    }

    public List<Keskustelu> findAll() throws SQLException {

        Connection connection = DriverManager.getConnection(dbaddress);
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Opiskelija");

        ResultSet rs = stmt.executeQuery();
        List<Keskustelu> keskustelut = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");
            Integer alue = rs.getInt("alue");
            keskustelut.add(new Keskustelu(id, nimi, alue));
        }

        rs.close();
        stmt.close();
        connection.close();

        return keskustelut;
    }

    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }
}
