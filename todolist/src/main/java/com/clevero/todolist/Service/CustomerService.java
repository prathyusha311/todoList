package com.clevero.todolist.Service;

import com.clevero.todolist.Entity.Customer;
import com.clevero.todolist.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public void registerCustomer(Customer c) {
        customerRepository.save(c);
    }

    public boolean checkUsernameExists(String username) {
        Customer c = customerRepository.findBYusername(username);

        if (c == null)
            return false;
        else
            return true;


    }

    public String loginCustomer(String username, String pswd) {
        Customer c = customerRepository.findBYusername(username);
        if (c == null) {
            return "Please enter a valid username";
        } else {
            if (pswd.equals(c.getPassword()))
                return "success";
            else
                return "Incorrect Password";

        }

    }
}
