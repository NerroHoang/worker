package worker;

public class SalaryHistory extends Worker implements Comparable<SalaryHistory>{

    private String status;
    private String date;

    public SalaryHistory() {
    }

    public SalaryHistory(int id, String name, int age, int Salary, String workLocation, String status, String date) {
        super(id, name, age, Salary, workLocation);
        this.status = status;
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int compareTo(SalaryHistory sh) {
        int compareId = ((SalaryHistory)sh).getId();
        return this.getId()-compareId;
    }

}
