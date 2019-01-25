package com.clevero.todolist.Repository;

import com.clevero.todolist.Entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends CrudRepository<Customer, String> {

    @Query("select c from Customer c where c.username=:username")
    public Customer findBYusername(@Param("username") String username);
}
