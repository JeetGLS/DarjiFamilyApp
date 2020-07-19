package com.darji.darjifamilyapp.Model;

public class JobsData {
    private int JobId;
    private String JobTitle;
    private String JobProfile;
    private String ExpireDate;
    private String Sector;
    private int SalaryFrom;
    private int SalaryTo;
    private String Skills;
    private String OrganizationName;
    private String JobLocation;
    private String Referencselink;
    private String ContactNumber;
    private String PostedDate;
    private int IsActive;

    public int getJobId() {
        return JobId;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public String getJobProfile() {
        return JobProfile;
    }

    public String getExpireDate() {
        return ExpireDate;
    }

    public String getSector() {
        return Sector;
    }

    public int getSalaryFrom() {
        return SalaryFrom;
    }

    public int getSalaryTo() {
        return SalaryTo;
    }

    public String getSkills() {
        return Skills;
    }

    public String getOrganizationName() {
        return OrganizationName;
    }

    public String getJobLocation() {
        return JobLocation;
    }

    public String getReferencselink() {
        return Referencselink;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public String getPostedDate() {
        return PostedDate;
    }

    public int getIsActive() {
        return IsActive;
    }
}
