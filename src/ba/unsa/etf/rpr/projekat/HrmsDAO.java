package ba.unsa.etf.rpr.projekat;

import ba.unsa.etf.rpr.projekat.Leave.Leave;
import ba.unsa.etf.rpr.projekat.Login.User;
import ba.unsa.etf.rpr.projekat.Department.Department;
import ba.unsa.etf.rpr.projekat.Job.Job;
import ba.unsa.etf.rpr.projekat.Employee.Employee;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class HrmsDAO {
    private static HrmsDAO instance;

    private PreparedStatement usersQuery, employeesQuery, departmentsQuery, jobsQuery, leavesQuery,
            deleteEmployeeQuery, deleteDepartmentQuery, deleteJobQuery,
            updateEmployeeQuery, updateDepartmentQuery, updateJobQuery,
            employeeIdQuery, departmentIdQuery, jobIdQuery, leaveIdQuery,
            addEmployeeQuery, addDepartmentQuery, addJobQuery, addLeaveQuery,
            changeApplicationStateQuery, getEmployeeQuery;

    private Connection conn;

    private Employee currentEmployee;
    private Department currentDepartment;
    private Job currentJob;
    private Leave currentLeave;

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

    public Leave getCurrentLeave() {
        return currentLeave;
    }

    public void setCurrentLeave(Leave currentLeave) {
        this.currentLeave = currentLeave;
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
            leavesQuery = conn.prepareStatement("SELECT * FROM leaves");
            deleteEmployeeQuery = conn.prepareStatement("DELETE FROM employees WHERE id = ?");
            deleteDepartmentQuery = conn.prepareStatement("DELETE FROM departments WHERE id = ?");
            deleteJobQuery = conn.prepareStatement("DELETE FROM jobs WHERE id = ?");
            updateEmployeeQuery = conn.prepareStatement("UPDATE employees SET first_name = ?, last_name = ?, email = ?, " +
                    "phone_number = ?, hire_date = ?, job_id = ?, salary = ?, commission_pct = ?, department_id = ? WHERE id = ?");
            updateDepartmentQuery = conn.prepareStatement("UPDATE departments SET department_name = ?, manager_id = ?, " +
                    "street_adress = ?, postal_code = ?, city = ? WHERE id = ?");
            updateJobQuery = conn.prepareStatement("UPDATE jobs SET job_title = ?, min_salary = ?, max_salary = ? WHERE id = ?");
            employeeIdQuery = conn.prepareStatement("SELECT MAX(id)+1 FROM employees");
            departmentIdQuery = conn.prepareStatement("SELECT MAX(id)+1 FROM departments");
            jobIdQuery = conn.prepareStatement("SELECT MAX(id)+1 FROM jobs");
            leaveIdQuery = conn.prepareStatement("SELECT MAX(id)+1 FROM leaves");
            addEmployeeQuery = conn.prepareStatement("INSERT INTO employees VALUES (?,?,?,?,?,?,?,?,?,?)");
            addDepartmentQuery = conn.prepareStatement("INSERT INTO departments VALUES (?,?,?,?,?,?)");
            addJobQuery = conn.prepareStatement("INSERT INTO jobs VALUES (?,?,?,?)");
            addLeaveQuery = conn.prepareStatement("INSERT INTO leaves VALUES (?,?,?,?,?,?)");
            changeApplicationStateQuery = conn.prepareStatement("UPDATE leaves SET state = ? WHERE id = ?");
            getEmployeeQuery = conn.prepareStatement("SELECT * FROM employees WHERE id = ?");
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
        return new User(rs.getString(1), rs.getString(2), rs.getString(3));
    }

    public Set<User> users() {
        Set<User> rezultat = new HashSet<>();
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
                rs.getString(4),rs.getString(5),rs.getString(6), rs.getInt(7),
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
        return new Job(rs.getInt(1), rs.getString(2),rs.getFloat(3),
                rs.getFloat(4));
    }

    private Leave getResultSetLeave(ResultSet rs) throws SQLException {
        Leave leave = new Leave(rs.getInt(1), null, rs.getString(3),
                rs.getString(4), rs.getString(5), rs.getString(6));
        leave.setEmployee(getEmployee(rs.getInt(2)));
        return leave;
    }

    private Employee getEmployee(Integer id) {
        try {
            getEmployeeQuery.setInt(1, id);
            ResultSet rs = getEmployeeQuery.executeQuery();
            if (!rs.next()) return null;
            return new Employee(rs.getInt(1), rs.getString(2),rs.getString(3),
                    rs.getString(4),rs.getString(5),rs.getString(6), rs.getInt(7),
                    rs.getFloat(8),rs.getFloat(9),rs.getInt(10));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
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

    public ArrayList<Leave> leaves() {
        ArrayList<Leave> rezultat = new ArrayList<>();
        try {
            ResultSet rs = leavesQuery.executeQuery();
            while (rs.next()) {
                Leave leave = getResultSetLeave(rs);
                rezultat.add(leave);
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

    public void deleteJob(Integer id) {
        try {
            deleteJobQuery.setInt(1, id);
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
            updateEmployeeQuery.setInt(6, employee.getJobId());
            updateEmployeeQuery.setFloat(7, employee.getSalary());
            updateEmployeeQuery.setFloat(8, employee.getCommissionPct());
            updateEmployeeQuery.setInt(9, employee.getDepartmentId());
            updateEmployeeQuery.setInt(10, employee.getId());
            updateEmployeeQuery.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateDepartment(Department department) {
        try {
            updateDepartmentQuery.setString(1, department.getDepartmentName());
            updateDepartmentQuery.setInt(2, department.getManagerId());
            updateDepartmentQuery.setString(3, department.getAdress());
            updateDepartmentQuery.setInt(4, department.getPostalCode());
            updateDepartmentQuery.setString(5, department.getCity());
            updateDepartmentQuery.setInt(6, department.getId());
            updateDepartmentQuery.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateJob(Job job) {
        try {
            updateJobQuery.setString(1, job.getJobTitle());
            updateJobQuery.setFloat(2, job.getMinSalary());
            updateJobQuery.setFloat(3, job.getMaxSalary());
            updateJobQuery.setInt(4, job.getId());
            updateJobQuery.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addEmployee(Employee employee) {
        try {
            ResultSet resultSet = employeeIdQuery.executeQuery();
            int id = 1;
            if(resultSet.next()) {
                id = resultSet.getInt(1);
            }
            addEmployeeQuery.setInt(1, id);
            addEmployeeQuery.setString(2, employee.getFirstName());
            addEmployeeQuery.setString(3, employee.getLastName());
            addEmployeeQuery.setString(4, employee.getEmail());
            addEmployeeQuery.setString(5, employee.getPhoneNumber());
            addEmployeeQuery.setString(6, employee.getHireDate());
            addEmployeeQuery.setInt(7, employee.getJobId());
            addEmployeeQuery.setFloat(8, employee.getSalary());
            addEmployeeQuery.setFloat(9, employee.getCommissionPct());
            addEmployeeQuery.setInt(10, employee.getDepartmentId());
            addEmployeeQuery.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addDepartment(Department department) {
        try {
            ResultSet resultSet = departmentIdQuery.executeQuery();
            int id = 1;
            if(resultSet.next()) {
                id = resultSet.getInt(1);
            }
            addDepartmentQuery.setInt(1, id);
            addDepartmentQuery.setString(2, department.getDepartmentName());
            addDepartmentQuery.setInt(3, department.getManagerId());
            addDepartmentQuery.setString(4, department.getAdress());
            addDepartmentQuery.setInt(5, department.getPostalCode());
            addDepartmentQuery.setString(6, department.getCity());
            addDepartmentQuery.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addJob(Job job) {
        try {
            ResultSet resultSet = jobIdQuery.executeQuery();
            int id = 1;
            if(resultSet.next()) {
                id = resultSet.getInt(1);
            }
            addJobQuery.setInt(1, id);
            addJobQuery.setString(2, job.getJobTitle());
            addJobQuery.setFloat(3, job.getMinSalary());
            addJobQuery.setFloat(4, job.getMaxSalary());
            addJobQuery.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addLeave(Leave leave) {
        try {
            ResultSet resultSet = leaveIdQuery.executeQuery();
            int id = 1;
            if(resultSet.next()) {
                id = resultSet.getInt(1);
            }
            addLeaveQuery.setInt(1, id);
            addLeaveQuery.setInt(2, leave.getEmployee().getId());
            addLeaveQuery.setString(3, leave.getFromDate());
            addLeaveQuery.setString(4, leave.getToDate());
            addLeaveQuery.setString(5, leave.getReason());
            addLeaveQuery.setString(6, leave.getState());
            addLeaveQuery.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void changeApplicationState (String state) {
        try {
            changeApplicationStateQuery.setString(1, state);
            changeApplicationStateQuery.setInt(2, getCurrentLeave().getId());
            changeApplicationStateQuery.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }
}
