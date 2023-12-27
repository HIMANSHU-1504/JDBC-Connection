import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class empByDept {
    public static void main(String[] args){
        String jdbcurl = "jdbc:mysql://localhost/Employee";
        String username = "root";
        String password = "root";

        try{
            Connection connection = DriverManager.getConnection(jdbcurl, username, password);

            if(connection != null){
                System.out.println("Connected");

                String departmentName = "Sales";
                String query = "SELECT emp.emp_id, emp.emp_name, emp.emp_sal, dept.dept_name " +
                               "FROM employee_details emp " +
                               "JOIN department dept ON emp.dept_id = dept.dept_id " +
                               "WHERE dept.dept_name = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, departmentName);

                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    int empId = resultSet.getInt("emp_id");
                    String empName = resultSet.getString("emp_name");
                    double empSalary = resultSet.getDouble("emp_sal");
                    String deptName = resultSet.getString("dept_name");

                    System.out.println("Employee Id: " + empId +
                                ", Name :" + empName + 
                                ", Salary: " + empSalary + 
                                ", Department: " + deptName);

                }

                resultSet.close();
                preparedStatement.close();
                connection.close();
            }
        }
        catch(SQLException e){
            System.out.println("Connection Failed " + e.getMessage());
            e.printStackTrace();
        }
    }
}
