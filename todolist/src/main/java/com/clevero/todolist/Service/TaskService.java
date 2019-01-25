package com.clevero.todolist.Service;

import com.clevero.todolist.Entity.Tasktable;
import com.clevero.todolist.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Vector;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public void createTodolist(Tasktable t) {
        taskRepository.save(t);
    }

    public void addincompletetask(String username, String task) {
        Tasktable t = taskRepository.findOne(username);
        Vector<String> v = t.getIncompleted();
        v.add(task);
        t.setIncompleted(v);

        taskRepository.save(t);
    }

    public void addcompletetask(String username, String task) {
        //first,  remove the task from incompleted
        //second,  add it to completed
        Tasktable t = taskRepository.findOne(username);

        Vector<String> ic = t.getIncompleted();
        ic.remove(task);
        t.setIncompleted(ic);

        Vector<String> v = t.getCompleted();
        v.add(task);
        t.setCompleted(v);

        taskRepository.save(t);
    }

    public Tasktable showtodolist(String username) {
        return taskRepository.findOne(username);
    }


    public void removeTask(String username, String task) {
        Tasktable t = taskRepository.findOne(username);
        Vector<String> v = t.getCompleted();
        v.remove(task);
        t.setCompleted(v);

        taskRepository.save(t);
    }
}
