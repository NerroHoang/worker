
package worker;

public class Worker {
    private int id;
    private String name;
    private int age;
    private int Salary;
    private String workLocation;
    public Worker(){
        
    }
    public Worker(int id, String name, int age, int Salary, String workLocation) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.Salary = Salary;
        this.workLocation = workLocation;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int Salary) {
        this.Salary = Salary;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }
    
}
