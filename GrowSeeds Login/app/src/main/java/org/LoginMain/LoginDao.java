package org.LoginMain;

import org.growseeds.models.Login; 
import org.growseeds.utils.Database; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    private String pesanError = "";

    public String getPesanError() {
        return pesanError;
    }

    public boolean daftarAkun(Login user) {
        String username = user.getUsername();
        String password = user.getPassword();

        if (password.length() < 8) {
            pesanError = "Password minimal harus 8 huruf!";
            return false;
        }

        String sql = "INSERT INTO user(username, password) VALUES(?, ?)";
        
        try (Connection conn = Database.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            int rows = pstmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            if (e.getMessage().contains("UNIQUE")) {
                pesanError = "Username '" + username + "' sudah dipakai orang lain!";
            } else {
                pesanError = "Terjadi kesalahan database: " + e.getMessage();
            }
            return false;
        }
    }

    public boolean cekLogin(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        try (Connection conn = Database.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("Error Login: " + e.getMessage());
            return false;
        }
    }

    // ==========================================
    // TAMBAHKAN METHOD MAIN DI BAWAH INI UNTUK TEST
    // ==========================================
    public static void main(String[] args) {
        System.out.println("Menjalankan program dari LoginDao...");
        
        LoginDao dao = new LoginDao();
        // Kamu bisa melakukan test method cekLogin atau daftarAkun di sini
    }
}