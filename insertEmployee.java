import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insertEmployee{
    public static void main(String[] args){
        String jdbcurl = "jdbc:mysql://localhost/Employee";
        String username = "root";
        String password = "root";

        try{
            Connection connection = DriverManager.getConnection(jdbcurl, username, password);

            if(connection != null){
                System.out.println("Connected");

                String insertQuery = "Insert INTO employee_details(emp_name, dept_id, emp_sal) values(?, ?, ?)";

                String[] empName = {"Alice", "Bob", "Charlie"};
                int[] deptIds = {1, 2, 1};
                double[] empSalaries = {55000.0, 60000.0, 52000.0};

                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

                for(int i=0;i<empName.length;i++){
                    preparedStatement.setString(1, empName[i]);
                    preparedStatement.setInt(2, deptIds[i]);
                    preparedStatement.setDouble(3, empSalaries[i]);

                    preparedStatement.addBatch();
                }

                int[] rowsAffected = preparedStatement.executeBatch();
                System.out.println("Number of rows affected " + rowsAffected.length);

                preparedStatement.close();
                connection.close();
            }
        }
        catch(SQLException e){
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }
}