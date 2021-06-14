package com.example.gtihms.authority;

public class ModelRoom {
    String RoomNo, Status;

    ModelRoom() {
    }

    public ModelRoom(String roomNo, String status) {
        this.RoomNo = roomNo;
        this.Status = status;
    }

    public String getRoomNo() {
        return RoomNo;
    }

    public String getStatus() {
        return Status;
    }

}
