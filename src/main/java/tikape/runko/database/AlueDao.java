package tikape.runko.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Alue;
import tikape.runko.SQLQueries;

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
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Alue ORDER BY nimi ASC");

        SQLQueries sqlq = new SQLQueries(dbaddress);
        ResultSet rs = stmt.executeQuery();
        List<Alue> alueet = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");
            int maara = sqlq.viestienMaara("alue.id", id);
            String tiem = sqlq.uusinViesti("alue.id", id);
            alueet.add(new Alue(id, nimi, maara, tiem));
        }

        rs.close();
        stmt.close();
        connection.close();

        return alueet;
    }
    
    public void lisaaAlue(String nimi) throws SQLException{
        Connection connection = DriverManager.getConnection(dbaddress);
        Statement stmt = connection.createStatement();
        stmt.execute("INSERT INTO Alue(nimi) VALUES('" + nimi + "')");
        connection.close();
    }

    public void delete(Integer key) throws SQLException {
        Connection connection = DriverManager.getConnection(dbaddress);
        Statement stmt = connection.createStatement();
        stmt.execute("DELETE FROM Alue WHERE id =" + key);        
        connection.close();
    }
}
