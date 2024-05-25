package tugas3.model;

import java.sql.*;

import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class Connector {
    private String username;
    private String password;
    Connection koneksi;
    Statement kalimat;

    private final String dbUrl = "jdbc:mysql://localhost/tugasjdbc";
    private final String user = "root";
    private final String pass = "";

    public Connector(String username, String password) {
        this.username = username;
        this.password = password;
        try {
            koneksi = DriverManager.getConnection(dbUrl, user, pass);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean checkLogin() {
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        try (PreparedStatement preparedStatement = koneksi.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet hasil = preparedStatement.executeQuery();

            if (hasil.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    // nambah data
    public boolean registerData() {
    try {
        // cek apakah usernamenya sudah ada
        String query1 = "SELECT * FROM user WHERE username = ?";
        try (PreparedStatement preparedStatement1 = koneksi.prepareStatement(query1)) {
            preparedStatement1.setString(1, username);
            ResultSet rs = preparedStatement1.executeQuery();

            if (rs.next()) {
                // Jika username sudah ada, kembalikan false
                return false;
            } else {
                // Jika username belum ada, lakukan registrasi
                String query2 = "INSERT INTO user (username, password) VALUES (?, ?)";
                try (PreparedStatement preparedStatement2 = koneksi.prepareStatement(query2)) {
                    preparedStatement2.setString(1, username);
                    preparedStatement2.setString(2, password);
                    int affectedRows = preparedStatement2.executeUpdate();
                    // Registrasi berhasil jika ada baris yang terpengaruh
                    return affectedRows > 0;
                }
            }
        }
    } catch (SQLException e) {
        // Tangkap dan tampilkan pesan kesalahan kepada pengguna
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat registrasi: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    return false;
}
}
