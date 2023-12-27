import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.PreparableStatement;

public class insertDepartment {
    public static void main(String[] args){
        String jdbcurl = "jdbc:mysql://localhost/Employee";
        String username = "root";
        String password = "root";

        try{

            Connection connection = DriverManager.getConnection(jdbcurl, username, password);
            if(connection != null){
                System.out.println("Connected");

                String inserQuery = "insert into department(dept_name) values(?)";

                String departmentName = "Sales";

                PreparedStatement preparedStatement = connection.prepareStatement(inserQuery);

                preparedStatement.setString(1, departmentName);

                int rowsAffected = preparedStatement.executeUpdate();

                if(rowsAffected > 0){
                    System.out.println("Successfully inserted");
                }
                else{
                    System.out.println("Failed");
                }

                preparedStatement.close();
                connection.close();
            }

        }catch(SQLException e){
            System.out.println("Connection Failed " +  e.getMessage());
            e.printStackTrace();
        }
    }
}
