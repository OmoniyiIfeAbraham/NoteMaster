package com.ife.todolist;

public class Note {

    private int id;
    private String title;
    private String descrition;

    public Note(String title, String descrition) {
        this.title = title;
        this.descrition = descrition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }
}
