package com.example.demo.model;

public class Todo {
    private int id;
    private String text;
    private boolean status;

    public Todo(int id, String text, boolean status) {
        this.id = id;
        this.text = text;
        this.status = status;
    }

    public  Todo(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
