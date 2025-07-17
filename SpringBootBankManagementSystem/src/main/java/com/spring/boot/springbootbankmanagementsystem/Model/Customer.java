package com.spring.boot.springbootbankmanagementsystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {
    private String iD, username;
    private double balance;
}
