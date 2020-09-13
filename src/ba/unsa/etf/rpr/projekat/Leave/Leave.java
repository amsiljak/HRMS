package ba.unsa.etf.rpr.projekat.Leave;

import ba.unsa.etf.rpr.projekat.Employee.Employee;

public class Leave {
    private Integer id;
    private Employee employee;
    private String fromDate;
    private String toDate;
    private String reason;
    private String state;

    public Leave() {
    }

    public Leave(Integer id, Employee employee, String fromDate, String toDate, String reason, String state) {
        this.id = id;
        this.employee = employee;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.reason = reason;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
