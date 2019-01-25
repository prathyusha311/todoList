package com.clevero.todolist.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Vector;

@Entity
public class Tasktable {

    @Id
    private String username;

    private Vector<String> incompleted;

    private Vector<String> completed;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setincomplete() {
        incompleted = new Vector<String>();
    }

    public void setcomplete() {
        completed = new Vector<String>();
    }


    public Vector<String> getIncompleted() {
        return incompleted;
    }

    public Vector<String> getCompleted() {
        return completed;
    }

    public void setIncompleted(Vector<String> incompleted) {
        this.incompleted = incompleted;
    }

    public void setCompleted(Vector<String> completed) {
        this.completed = completed;
    }
}
