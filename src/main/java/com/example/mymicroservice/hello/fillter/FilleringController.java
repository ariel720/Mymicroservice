package com.example.mymicroservice.hello.fillter;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilleringController {

	@GetMapping("/fillter")
	public SomeBean retrieveSomeBean() {
		return new SomeBean("v1","v2","v3");
	}
	
	@GetMapping("/fillter-list")
	public List<SomeBean> retrieveListSomeBean() {
		
		return Arrays.asList(new SomeBean("v1","v2","v3"),new SomeBean("v11","v12","v13"));
	}
}
