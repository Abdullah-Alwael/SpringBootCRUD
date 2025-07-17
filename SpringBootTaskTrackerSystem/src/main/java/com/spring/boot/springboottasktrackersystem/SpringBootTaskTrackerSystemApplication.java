package com.spring.boot.springboottasktrackersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootTaskTrackerSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTaskTrackerSystemApplication.class, args);
	}

	/*
	* Create Task tracker system , Where you can:
	*  get all the tasks
	*  , add
	* , remove
	* , update all tasks.
	*
		Task Class : ID ,  title , description , status

		* Create a new task (title , description , status)
		* Display all tasks.
		* Update a task
		* Delete a task
		* Change the task status as done or not done
		* Search for a task by given title
	*
	* */
}
