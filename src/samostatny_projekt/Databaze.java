package samostatny_projekt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class Databaze {
	public Databaze() {};
	
	public void DBstartupload(SpravceStudentu spravce) {
		Connection conn = DBConnection.getDBConnection();
		
		String loadQuery = "SELECT * FROM student";
		try (PreparedStatement prStmt = conn.prepareStatement(loadQuery);
			ResultSet rs = prStmt.executeQuery()) {
			while (rs.next()) {
				int id = spravce.pridatStudenta(rs.getString("jmeno"), rs.getString("prijmeni"), rs.getInt("rokNarozeni"), rs.getInt("obor"));
				String znamky = rs.getString("znamky");
				for (String s: znamky.split(", "))
				spravce.pridatZnamku(id, Integer.valueOf(s));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
	    }
	}
	
	public void DBexitsave(SpravceStudentu spravce) {
		Connection conn = DBConnection.getDBConnection();
		int obor;
		String delete = "DELETE FROM student";
		try {
			conn.prepareStatement(delete).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (Student st: spravce.getAllStudenti().values()) {
			int id = st.getID();
			if (st instanceof StudentTelekomunikaci) {
				obor = 1;
			}
			else {
				obor = 2;
			}
			String jmeno = st.getJmeno();
			String prijmeni = st.getPrijmeni();
			int rokNarozeni = st.getRokNarozeni();
			List<Integer> znamky = st.getZnamky();
			String znamkyStr = znamky.stream().map(String::valueOf).collect(Collectors.joining(", "));
			
			String insertQuery = "INSERT INTO student (id, obor, jmeno, prijmeni, rokNarozeni, znamky) VALUES (?, ?, ?, ?, ?, ?)";
			try (PreparedStatement prStmt = conn.prepareStatement(insertQuery)) {	
			    prStmt.setInt(1,  id);
			    prStmt.setInt(2, obor);
			    prStmt.setString(3, jmeno);
			    prStmt.setString(4, prijmeni);
			    prStmt.setInt(5, rokNarozeni);
			    prStmt.setString(6, znamkyStr);
			
			    prStmt.executeUpdate();
			    System.out.println("Nový uživatel byl vložen do databáze!");
			} catch (SQLException e) {
			    System.out.println("Uživatel už byl vložen nebo jste zadali špatně SQL příkaz INSERT");
		    }

		}
		DBConnection.closeConnection();
	}
}

