package com.example.crewmembers;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "crews")
public class CrewMember {
    @PrimaryKey@NonNull
    private String name;

    private String image;
    private String status;
    private String agency;
    private String wiki;

    public CrewMember(String name, String image, String status, String agency, String wiki) {
        this.name = name;
        this.image = image;
        this.status = status;
        this.agency = agency;
        this.wiki = wiki;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
