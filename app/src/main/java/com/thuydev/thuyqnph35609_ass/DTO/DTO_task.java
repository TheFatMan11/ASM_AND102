package com.thuydev.thuyqnph35609_ass.DTO;

public class DTO_task {
    private int id ;
    private String name;
    private int status;
    private String start;
    private String end ;
     private int id_user;

    public DTO_task() {
    }

    public DTO_task(int id, String name, int status, String start, String end, int id_user) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.start = start;
        this.end = end;
        this.id_user = id_user;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
