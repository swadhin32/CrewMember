package com.example.crewmembers.ViewModel;

public class CourseModal {

    // variables for our course
    // name and description.
    private String name;
    private String image;
    private String agency;
    private String wiki;
    private String status;

    public CourseModal(String name, String image, String agency, String wiki, String status) {
        this.name = name;
        this.image = image;
        this.agency = agency;
        this.wiki = wiki;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getWiki() {
        return wiki;
    }

    public void setWiki(String wiki) {
        this.wiki = wiki;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
