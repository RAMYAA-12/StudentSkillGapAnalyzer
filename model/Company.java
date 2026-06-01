package model;

public class Company {
    private int id;
    private String companyName;

    public Company(int id, String companyName) {
        this.id = id;
        this.companyName = companyName;
    }

    public int getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }
}
