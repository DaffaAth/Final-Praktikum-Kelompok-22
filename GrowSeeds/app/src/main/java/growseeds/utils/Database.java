package growseeds.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String URL = "jdbc:sqlite:database_baru.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initDatabase() {
        String sqlTabelUser = "CREATE TABLE IF NOT EXISTS user ("
                            + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + " username TEXT UNIQUE NOT NULL,"
                            + " password TEXT NOT NULL"
                            + ");";
                   
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sqlTabelUser);
            System.out.println("Database Sukses: Tabel User siap!");
        } catch (SQLException e) {
            System.err.println("Gagal inisialisasi database: " + e.getMessage());
            e.printStackTrace();
        }
    }
}