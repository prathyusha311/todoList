package com.clevero.todolist.Repository;

import com.clevero.todolist.Entity.Tasktable;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Tasktable, String> {
}
