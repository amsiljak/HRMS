package ba.unsa.etf.rpr.projekat;

import ba.unsa.etf.rpr.projekat.Login.User;
import ba.unsa.etf.rpr.projekat.Odjel.Department;
import ba.unsa.etf.rpr.projekat.Posao.Job;
import ba.unsa.etf.rpr.projekat.Zaposlenik.Employee;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class HrmsDAO {
    private static HrmsDAO instance;
    private PreparedStatement usersQuery, employeesQuery, departmentsQuery, jobsQuery, deleteEmployeeQuery,
            deleteDepartmentQuery, deleteJobQuery, updateEmployeeQuery, updateDepartmentQuery, updateJobQuery;
    private Connection conn;
    private Employee currentEmployee;
    private Department currentDepartment;
    private Job currentJob;

    public Employee getCurrentEmployee() {
        return currentEmployee;
    }

    public void setCurrentEmployee(Employee currentEmployee) {
        this.currentEmployee = currentEmployee;
    }

    public Department getCurrentDepartment() {
        return currentDepartment;
    }

    public void setCurrentDepartment(Department currentDepartment) {
        this.currentDepartment = currentDepartment;
    }

    public Job getCurrentJob() {
        return currentJob;
    }

    public void setCurrentJob(Job currentJob) {
        this.currentJob = currentJob;
    }

    public static HrmsDAO getInstance() {
        if (instance == null) {
            try {
                instance = new HrmsDAO();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
    public static void removeInstance() {
        if(instance == null) return;
        instance.close();
        instance = null;
    }
    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private HrmsDAO() throws SQLException {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:korisnici.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            usersQuery = conn.prepareStatement("SELECT * FROM users");
        } catch (SQLException e) {
            regenerateDatabase();
            try {
                usersQuery = conn.prepareStatement("SELECT * FROM users" );
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        try {
            employeesQuery = conn.prepareStatement("SELECT * FROM employees");
            departmentsQuery = conn.prepareStatement("SELECT * FROM departments");
            jobsQuery = conn.prepareStatement("SELECT * FROM jobs");
            deleteEmployeeQuery = conn.prepareStatement("DELETE FROM employees WHERE id = ?");
            deleteDepartmentQuery = conn.prepareStatement("DELETE FROM departments WHERE id = ?");
            deleteJobQuery = conn.prepareStatement("DELETE FROM jobs WHERE id = ?");
            updateEmployeeQuery = conn.prepareStatement("UPDATE employees SET first_name = ?, last_name = ?, email = ?, " +
                    "phone_number = ?, hire_date = ?, job_id = ?, salary = ?, commission_pct = ?, department_id = ? WHERE id = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void regenerateDatabase(){
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("korisnici.db.sql")); //cita iz ove datoteke
            String sqlUpit = "";
            while (ulaz.hasNext()) {
                sqlUpit += ulaz.nextLine();
                if (sqlUpit.charAt(sqlUpit.length() - 1) == ';') {

                    try {
                        Statement stmt = conn.createStatement();
                        stmt.execute(sqlUpit);
                        sqlUpit = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            }
            ulaz.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private User getResultSetUser(ResultSet rs) throws SQLException {
        return new User(rs.getString(1), rs.getString(2));
    }

    public ArrayList<User> users() {
        ArrayList<User> rezultat = new ArrayList<>();
        try {
            ResultSet rs = usersQuery.executeQuery();
            while (rs.next()) {
                User user = getResultSetUser(rs);
                rezultat.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rezultat;
    }

    private Employee getResultSetEmployee(ResultSet rs) throws SQLException {
        return new Employee(rs.getInt(1), rs.getString(2),rs.getString(3),
                rs.getString(4),rs.getString(5),rs.getString(6), rs.getString(7),
                rs.getFloat(8),rs.getFloat(9),rs.getInt(10));
    }

    public ArrayList<Employee> employees() {
        ArrayList<Employee> rezultat = new ArrayList<>();
        try {
            ResultSet rs = employeesQuery.executeQuery();
            while (rs.next()) {
                Employee zaposleni = getResultSetEmployee(rs);
                rezultat.add(zaposleni);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rezultat;
    }

    private Department getResultSetDepartment(ResultSet rs) throws SQLException {
        return new Department(rs.getInt(1), rs.getString(2),rs.getInt(3),
                rs.getString(4),rs.getInt(5),rs.getString(6));
    }

    public ArrayList<Department> departments() {
        ArrayList<Department> rezultat = new ArrayList<>();
        try {
            ResultSet rs = departmentsQuery.executeQuery();
            while (rs.next()) {
                Department department = getResultSetDepartment(rs);
                rezultat.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rezultat;
    }

    private Job getResultSetJob(ResultSet rs) throws SQLException {
        return new Job(rs.getString(1), rs.getString(2),rs.getFloat(3),
                rs.getFloat(4));
    }

    public ArrayList<Job> jobs() {
        ArrayList<Job> rezultat = new ArrayList<>();
        try {
            ResultSet rs = jobsQuery.executeQuery();
            while (rs.next()) {
                Job job = getResultSetJob(rs);
                rezultat.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rezultat;
    }

    public void deleteEmployee(Integer id) {
        try {
            deleteEmployeeQuery.setInt(1, id);
            deleteEmployeeQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDepartment(Integer id) {
        try {
            deleteDepartmentQuery.setInt(1, id);
            deleteDepartmentQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteJob(String id) {
        try {
            deleteJobQuery.setString(1, id);
            deleteJobQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(Employee employee) {
        try {
            updateEmployeeQuery.setString(1, employee.getFirstName());
            updateEmployeeQuery.setString(2, employee.getLastName());
            updateEmployeeQuery.setString(3, employee.getEmail());
            updateEmployeeQuery.setString(4, employee.getPhoneNumber());
            updateEmployeeQuery.setString(5, employee.getHireDate());
            updateEmployeeQuery.setString(6, employee.getJobId());
            updateEmployeeQuery.setFloat(7, employee.getSalary());
            updateEmployeeQuery.setFloat(8, employee.getCommissionPct());
            updateEmployeeQuery.setInt(9, employee.getDepartmentId());
            updateEmployeeQuery.setInt(10, employee.getId());
            updateEmployeeQuery.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }
}
