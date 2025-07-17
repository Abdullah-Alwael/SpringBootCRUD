package com.spring.boot.springboottasktrackersystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Task {
    private String iD;
    private String title;
    private String description;
    private String status;

}
