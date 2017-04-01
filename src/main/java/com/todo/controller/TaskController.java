package com.todo.controller;

import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//  #0006.01  //== 	add controller(s) to for web pages after authentication succeeds
@Controller
public class TaskController {
	
	//  #0006.02  //== add request mapping url
	//  #0006.03  //== create class and instantiate class for template  
	//  #0007.00	//==	add configuration bean for data-source
	//  #0008.00 	//==	add service layer for model class to use
	@RequestMapping({"/", "/todo"})
	public String taskList(Model model) {
		
		// 
		 // Iterable<Task> tasks = taskService.findAll();
		
		
		return "todo";
	}

}
