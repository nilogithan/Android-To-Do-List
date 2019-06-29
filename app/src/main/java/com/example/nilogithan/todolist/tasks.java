package com.example.nilogithan.todolist;

public class tasks {
    private String id;
    private String name;
    private String discription;
    private String priority;
    private String duedate;

    public tasks(){

    }

    public tasks(String id, String name, String discription, String priority, String duedate) {
        this.id = id;
        this.name = name;
        this.discription = discription;
        this.priority = priority;
        this.duedate = duedate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }
}
