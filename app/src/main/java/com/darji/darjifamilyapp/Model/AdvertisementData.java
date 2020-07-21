package com.darji.darjifamilyapp.Model;

public class AdvertisementData {

    private int AdvertisementId;
    private String OrganizationName;
    private String Description;
    private String Website;
    private String StartDate;
    private String EndDate;
    private String BannerImage;
    private int AmountPaid;
    private int IsFixed;
    private int IsActive;

    public int getAdvertisementId() {
        return AdvertisementId;
    }

    public String getOrganizationName() {
        return OrganizationName;
    }

    public String getDescription() {
        return Description;
    }

    public String getWebsite() {
        return Website;
    }

    public String getStartDate() {
        return StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public String getBannerImage() {
        return BannerImage;
    }

    public int getAmountPaid() {
        return AmountPaid;
    }

    public int getIsFixed() {
        return IsFixed;
    }

    public int getIsActive() {
        return IsActive;
    }
}
