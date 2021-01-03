package com.example.mymicroservice.hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mymicroservice.hello.user.User;
import com.example.mymicroservice.hello.user.UserDAO;



@RestController
public class HelloWorldController {
	
    @RequestMapping("/")
    public String home(){
        return "Hello Home!";
    }
    
    //convert the data to Json
    @GetMapping("/bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("test1");
    }

    //Get variables
    //http://localhost:8080/bean/variable/shinnhee
    @GetMapping("/bean/variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Your name is %s", name));
    }

}
