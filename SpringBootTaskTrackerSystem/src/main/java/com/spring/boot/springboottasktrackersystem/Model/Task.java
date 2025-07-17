package com.spring.boot.springboottasktrackersystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Task {
    String iD;
    String title;
    String description;
    String status;

}
