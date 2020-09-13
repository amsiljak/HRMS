package ba.unsa.etf.rpr.projekat.Department;

public class Department {
    private Integer id;
    private String departmentName;
    private Integer managerId;
    private String adress;
    private Integer postalCode;
    private String city;

    public Department() {
    }

    public Department(Integer id, String departmentName, Integer managerId, String adress, Integer postalCode, String city) {
        this.id = id;
        this.departmentName = departmentName;
        this.managerId = managerId;
        this.adress = adress;
        this.postalCode = postalCode;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return departmentName;
    }
}
