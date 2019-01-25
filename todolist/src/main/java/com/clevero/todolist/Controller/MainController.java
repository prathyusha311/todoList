package com.clevero.todolist.Controller;

import com.clevero.todolist.Entity.Customer;
import com.clevero.todolist.Entity.Tasktable;
import com.clevero.todolist.Repository.TaskRepository;
import com.clevero.todolist.Service.CustomerService;
import com.clevero.todolist.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class MainController {


    @Autowired
    CustomerService customerService;

    @Autowired
    TaskService taskService;

    @Autowired
    TaskRepository taskRepository;

    @RequestMapping(path = "/startpage")
    public String startup() {
        return "index";
    }


    @GetMapping(path = "/add") // Map ONLY GET Requests
    public @ResponseBody
    String addNewUser(@RequestParam String emailId
            , @RequestParam String fullName, @RequestParam String username, @RequestParam String password) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        if (customerService.checkUsernameExists(username))
            return "username already exists";


        //registering the customer
        Customer n = new Customer();
        n.setEmailId(emailId);
        n.setFullName(fullName);
        n.setUsername(username);
        n.setPassword(password);
        customerService.registerCustomer(n);


        //creating the taskTable
        Tasktable t = new Tasktable();
        t.setUsername(username);
        t.setcomplete();
        t.setincomplete();
        taskService.createTodolist(t);
        return "Registration completed successfully";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String customerLogin(@RequestParam String username, @RequestParam String password) {
        String s;
        s = customerService.loginCustomer(username, password);
        return s;
    }

    @RequestMapping(path = "/addTask", method = RequestMethod.POST)
    @ResponseBody
    public void incompletetaskAddition(@RequestParam String username, @RequestParam String task) {
        taskService.addincompletetask(username, task);
    }

    @RequestMapping(path = "/movetodone", method = RequestMethod.POST)
    @ResponseBody
    public void completetask(@RequestParam String username, @RequestParam String task) {
        taskService.addcompletetask(username, task);
    }

    @RequestMapping(path = "/home", method = RequestMethod.POST)
    @ResponseBody
    public Tasktable showlist(@RequestParam String username) {
        return taskService.showtodolist(username);
    }

    @RequestMapping(path = "/deletetask", method = RequestMethod.POST)
    @ResponseBody
    public void taskremove(@RequestParam String username, @RequestParam String task) {
        taskService.removeTask(username, task);
    }

    @RequestMapping(path = "/show", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Tasktable> showall() {
        return taskRepository.findAll();
    }


}