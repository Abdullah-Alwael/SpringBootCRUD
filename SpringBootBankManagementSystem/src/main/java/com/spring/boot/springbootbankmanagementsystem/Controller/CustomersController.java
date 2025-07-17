package com.spring.boot.springbootbankmanagementsystem.Controller;

import com.spring.boot.springbootbankmanagementsystem.Api.ApiResponse;
import com.spring.boot.springbootbankmanagementsystem.Model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomersController {
    //    Create Bank management system where you can
    ArrayList<Customer> customers = new ArrayList<>();
    int id = 100;

    //    get all the Customers
    @GetMapping("/list")
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    //  , add
    @PostMapping("/register")
    public ApiResponse addCustomer(@RequestBody Customer customer) {
        customer.setID(Integer.toString(id));
        id++;
        customers.add(customer);

        return new ApiResponse("Customer registered successfully", "200");
    }

    //  , remove
    @DeleteMapping("/delete/{iD}")
    public ApiResponse deleteCustomer(@PathVariable String iD) {
        boolean deleted = false;
        for (Customer customer : customers) {
            if (customer.getID().equals(iD)) {
                customers.remove(customer);
                deleted = true;
                break;
            }
        }
        if (deleted) {
            return new ApiResponse("Customer was deleted successfully", "200 OK");
        } else {
            return new ApiResponse("Error: the customer does not exist", "404 not found");
        }
    }

    //  , update Customers
    @PutMapping("/update/{iD}")
    public ApiResponse updateCustomer(@PathVariable String iD, @RequestBody Customer requestedCustomer) {
        boolean updated = false;
        for (Customer customer : customers) {
            if (customer.getID().equals(iD)) {
                customers.set(customers.indexOf(customer),requestedCustomer);
                updated = true;
                break;
            }
        }
        if (updated) {
            return new ApiResponse("Customer was updated successfully", "200 OK");
        } else {
            return new ApiResponse("Error: the customer does not exist", "404 not found");
        }
    }
//    Endpoints :
//    Get all the customers
//    Add new customers
//    Update customers
//    Delete customers
//    Deposit money to customer
//    Withdraw money from customers
}
