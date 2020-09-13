package ba.unsa.etf.rpr.projekat.Job;

public class Job {
    private Integer id;
    private String jobTitle;
    private float minSalary;
    private float maxSalary;

    public Job() {
    }

    public Job(Integer posaoId, String jobTitle, float minSalary, float maxSalary) {
        this.id = posaoId;
        this.jobTitle = jobTitle;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public float getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(float minSalary) {
        this.minSalary = minSalary;
    }

    public float getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(float maxSalary) {
        this.maxSalary = maxSalary;
    }

    @Override
    public String toString() {
        return jobTitle;
    }
}
