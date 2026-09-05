import java.sql.*;

public class Main {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/demo";
        String username = "root";
        String password = "Yash11";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement stmt = connection.createStatement();

            String insertQuery = "INSERT INTO users(name, email) VALUES ('John','john@email.com')";
            stmt.executeUpdate(insertQuery);

            System.out.println("Data inserted successfully!");

            String selectQuery = "SELECT * FROM users";
            ResultSet rs = stmt.executeQuery(selectQuery);

            boolean hayFilas = false;

            while(rs.next()) {
                hayFilas = true;

                System.out.println(
                        rs.getInt("id") + " " +
                                rs.getString("name") + " " +
                                rs.getString("email")
                );
            }

            if(hayFilas) {
                System.out.println("Data read successfully!");
            } else {
                System.out.println("No data found!");
            }

            String updateQuery = "UPDATE users SET email='john_new@gmail.com' WHERE id = 1";
            stmt.executeUpdate(updateQuery);

            System.out.println("Data updated successfully!");

            String deleteQuery = "DELETE FROM users WHERE id = 1";
            stmt.executeUpdate(deleteQuery);

            System.out.println("Data deleted successfully!");

        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }

    }
}