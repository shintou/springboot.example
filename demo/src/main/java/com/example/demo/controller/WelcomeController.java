package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.form.Myform;
import com.example.demo.service.FooService;
import com.example.demo.validator.FileValidator;

@Controller
public class WelcomeController {
	
    @Autowired
    private FileValidator fileValidator;
    
    @Autowired
    private FooService fooService;
	
    @InitBinder
    protected void initBinderFileBucket(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }

	@RequestMapping("/")
	public String welcome(Model model) {
		
		User user = fooService.doSomeBusinessStuff("1");
		
		System.out.println("user is " + user.getId());
		
		model.addAttribute("Myform", new Myform()); 
		return "myform";
	}
	
    @RequestMapping(value = "/sendparam", method = RequestMethod.GET)
    public String backup(@RequestParam("firstName") String firstName,
    		@RequestParam("lastName") String lastName) {
 
    	System.out.println("firstname is " + firstName);
    	System.out.println("lastName is " + lastName);
 
        return "sendparam";
    }
    
    @RequestMapping(value = "/sendform", method = RequestMethod.GET)
    public String test(@Validated Myform myform,BindingResult result) {
    	
    	if (result.hasErrors()) {
    		return "sendform";
    	}
 
    	System.out.println("firstname is " + myform.getFirstName());
    	System.out.println("lastName is " + myform.getLastName());
 
        return "sendform";
    }
    
    @RequestMapping(value = "/myform", method = RequestMethod.GET)
    public String myform(@Validated @ModelAttribute("Myform") Myform form,
            BindingResult result) {
    	
    	if (result.hasErrors()) {
    		return "myform";
    	}
 
    	System.out.println("firstname is " + form.getFirstName());
    	System.out.println("lastName is " + form.getLastName());
 
        return "myform";
    }


}