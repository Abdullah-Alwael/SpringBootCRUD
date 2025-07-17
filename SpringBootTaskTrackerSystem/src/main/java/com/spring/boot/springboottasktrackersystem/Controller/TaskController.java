package com.spring.boot.springboottasktrackersystem.Controller;

import com.spring.boot.springboottasktrackersystem.Api.ApiResponse;
import com.spring.boot.springboottasktrackersystem.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    ArrayList<Task> tasks = new ArrayList<>();

    @GetMapping("/list")
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    @PostMapping("/add")
    public ApiResponse addTask(@RequestBody Task task) {
        tasks.add(task);

        return new ApiResponse("Task was added successfully", "200 OK");
    }

    @DeleteMapping("/delete/{iD}")
    public ApiResponse removeTask(@PathVariable String iD) {
        boolean deleted = false;
        for (Task task : tasks) {
            if (task.getID().equals(iD)) {
                tasks.remove(task);
                deleted = true;
                break;
            }
        }
        if (deleted) {
            return new ApiResponse("Task was removed successfully", "200 OK");
        } else {
            return new ApiResponse("Error: the task does not exist", "404 not found");
        }
    }

    @PutMapping("/update/{iD}")
    public ApiResponse updateTask(@PathVariable String iD, @RequestBody Task requestedTask) {
        boolean updated = false;
        for (Task task : tasks) {
            if (task.getID().equals(iD)) {
                tasks.set(tasks.indexOf(task), requestedTask);
                updated = true;
                break;
            }
        }
        if (updated) {
            return new ApiResponse("Task was updated successfully", "200 OK");
        } else {
            return new ApiResponse("Error: the task does not exist", "404 not found");
        }
    }


    @PutMapping("status/{iD}/{status}")
    public ApiResponse changeTaskStatus(@PathVariable String iD, @PathVariable String status) {
        boolean statusChanged = false;
        for (Task task : tasks) {
            if (task.getID().equals(iD)) {
                tasks.get(tasks.indexOf(task)).setStatus(status);
                statusChanged = true;
                break;
            }
        }
        if (statusChanged) {
            return new ApiResponse("Task status changed successfully", "200 OK");
        } else {
            return new ApiResponse("Error: the task does not exist", "404 not found");
        }

    }

    @GetMapping("/search/{title}")
    public Task searchTask(@PathVariable String title) {

        for (Task task : tasks) {
            if (task.getTitle().contains(title)) {
                return task;
            }
        }
        return new Task(null, null, null, null); // not found
    }

}
