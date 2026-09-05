import java.sql.*;

public class Main {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/demo";
        String username = "root";
        String password = "Yash11";

        // VALUES (?,?)

        try {
            Connection connection = DriverManager.getConnection(url,username,password);

            String insertSQL = "INSERT INTO users(name, email) VALUES (?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(insertSQL);
            pstmt.setString(1, "John");
            pstmt.setString(2, "john@email.com");
            pstmt.executeUpdate();
            System.out.println("inserted!");

            String selectSQL = "SELECT * FROM users";
            PreparedStatement pstmtSelect = connection.prepareStatement(selectSQL);
            ResultSet rs = pstmtSelect.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " +
                        rs.getString("name") + " " +
                        rs.getString("email"));
            }

            String updateSQL = "UPDATE users SET name = ?, email = ? WHERE id = ?";
            PreparedStatement pstmtUpdate = connection.prepareStatement(updateSQL);
            pstmtUpdate.setString(1, "updated@email.com");
            pstmtUpdate.setInt(2, 1);
            pstmtUpdate.executeUpdate();
            System.out.println("Updated!");

            String deleteSQL = "DELETE FROM users WHERE id = ?";
            PreparedStatement pstmtDelete = connection.prepareStatement(deleteSQL);
            pstmtDelete.setInt(1, 1);
            pstmtDelete.executeUpdate();
            System.out.println("Deleted!");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}