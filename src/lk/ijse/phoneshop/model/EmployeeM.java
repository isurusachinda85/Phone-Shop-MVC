package lk.ijse.phoneshop.model;

import lk.ijse.phoneshop.to.Customer;
import lk.ijse.phoneshop.to.Employee;
import lk.ijse.phoneshop.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeM {
    public static boolean saveEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        String sql = "INSERT Into employee values (?,?,?,?,?,?,?,?,?)";
        return CrudUtil.execute(sql,employee.getId(),employee.getName(),employee.getAddress(),employee.getEmail(),
                employee.getPhoneNo(),employee.getDateOfBirth(),employee.getJobRole(),employee.getUserName(),employee.getPassword());
    }
    public static ResultSet getAllEmployee() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * From employee";
        return CrudUtil.execute(sql);
    }
    public static String getNextEmployeeId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT eId FROM employee ORDER BY eId DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            return getNextEmployeeId(resultSet.getString(1));
        }
        return getNextEmployeeId(resultSet.getString(null));
    }
    public static String getNextEmployeeId(String cusId){
        if (cusId!=null){
            String[]split = cusId.split("E0");
            int id = Integer.parseInt(split[1]);
            id+=1;
            return "E00"+id;

        }
            return "E001";

    }
    public static boolean deleteEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        String sql = "Delete From employee where eId=?";
        return CrudUtil.execute(sql,employee.getId());
    }
    public static Employee searchEmployee(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM employee WHERE eId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, id);
        while (resultSet.next()){
            return new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9));
        }
        return null;
    }
}
