package com.example.gtihms.applicant;

public class BookingData {
    String name, programme, location, startdate, enddate, status, alloted_room;

    public BookingData(String name, String programme, String location, String startdate, String enddate, String status, String alloted_room) {
        this.name = name;
        this.programme = programme;
        this.location = location;
        this.startdate = startdate;
        this.enddate = enddate;
        this.status = status;
        this.alloted_room = alloted_room;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAlloted_room() {
        return alloted_room;
    }

    public void setAlloted_room(String alloted_room) {
        this.alloted_room = alloted_room;
    }
}
