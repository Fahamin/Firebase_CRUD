package fff.phot.seeker.firebasecrudtest.model;

public class Employee {

    int id;
    String name,dept,joinDate,user_id;
    String salary;

    public Employee() {
    }

    public Employee(String name, String dept, String joinDate,String salary,String user_id) {
        this.name = name;
        this.dept = dept;
        this.joinDate = joinDate;
        this.salary = salary;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
