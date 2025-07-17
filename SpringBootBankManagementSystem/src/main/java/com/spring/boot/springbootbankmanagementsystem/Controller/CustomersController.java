package com.spring.boot.springbootbankmanagementsystem.Controller;

import com.spring.boot.springbootbankmanagementsystem.Api.ApiResponse;
import com.spring.boot.springbootbankmanagementsystem.Model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomersController {
    ArrayList<Customer> customers = new ArrayList<>();
    int id = 100;

    @GetMapping("/list")
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    @PostMapping("/register")
    public ApiResponse addCustomer(@RequestBody Customer customer) {
        customer.setID(Integer.toString(id));
        id++;
        customers.add(customer);

        return new ApiResponse("Customer registered successfully", "200");
    }

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

    @PutMapping("/update/{iD}")
    public ApiResponse updateCustomer(@PathVariable String iD, @RequestBody Customer requestedCustomer) {
        boolean updated = false;
        for (Customer customer : customers) {
            if (customer.getID().equals(iD)) {
                customers.set(customers.indexOf(customer), requestedCustomer);
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

    @PutMapping("/deposit/{iD}/{amount}")
    public ApiResponse deposit(@PathVariable double amount, @PathVariable String iD) {
        if (amount <= 0) {
            return new ApiResponse("Error: the amount can not be zero or negative", "400 Bad Request");
        }
        boolean deposited = false;
        for (Customer customer : customers) {
            if (customer.getID().equals(iD)) {
                customers.get(customers.indexOf(customer)).setBalance(
                        customers.get(customers.indexOf(customer)).getBalance() + amount
                );
                deposited = true;
                break;
            }
        }
        if (deposited) {
            return new ApiResponse("Customer deposited " + amount + " successfully", "200 OK");
        } else {
            return new ApiResponse("Error: the customer does not exist", "404 not found");
        }
    }

    @PutMapping("/withdraw/{iD}/{amount}")
    public ApiResponse withdraw(@PathVariable double amount, @PathVariable String iD) {
        if (amount <= 0) {
            return new ApiResponse("Error: the amount can not be zero or negative", "400 Bad Request");
        }
        boolean withdrawn = false;
        for (Customer customer : customers) {
            if (customer.getID().equals(iD)) {
                if (customers.get(customers.indexOf(customer)).getBalance() < amount) {
                    return new ApiResponse("Error: Not enough balance", "400 Bad Request");
                } else {
                    customers.get(customers.indexOf(customer)).setBalance(
                            customers.get(customers.indexOf(customer)).getBalance() - amount
                    );
                    withdrawn = true;
                }
                break;
            }
        }
        if (withdrawn) {
            return new ApiResponse("Customer withdrawn " + amount + " successfully", "200 OK");
        } else {
            return new ApiResponse("Error: the customer does not exist", "404 not found");
        }

    }
}
