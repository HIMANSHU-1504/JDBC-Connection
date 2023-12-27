import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class fetchEmployee {
    public static void main(String[] args){
        String jdbcurl = "jdbc:mysql://localhost/Employee";
        String username = "root";
        String password = "root";

        try{
            Connection connection = DriverManager.getConnection(jdbcurl, username, password);
            if(connection != null){
                System.out.println("Connected");

                String query = "select * from employee_details";

                PreparedStatement preparedStatement = connection.prepareStatement(query);

                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    int empId = resultSet.getInt("emp_id");
                    String empName = resultSet.getString("emp_name");
                    int departmentId = resultSet.getInt("dept_id");
                    double empSalary = resultSet.getDouble("emp_sal");

                    System.out.println("Employee Id: " + empId + 
                                        ", Employee Name: " + empName + 
                                        ", Department Id: " + departmentId +
                                        ", Employee Salary: " + empSalary
                    );
                }

                resultSet.close();
                preparedStatement.close();
                connection.close();
            }
        }catch(SQLException e){
            System.out.println("Connection Failed " + e.getMessage());
            e.printStackTrace();
        }
    }
}
