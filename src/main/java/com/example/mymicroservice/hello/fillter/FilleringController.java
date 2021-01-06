package com.example.mymicroservice.hello.fillter;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilleringController {

	@GetMapping("/filter")
	public SomeBean retrieveSomeBean() {
		return new SomeBean("v1","v2","v3","v4","v5");
	}
	
	@GetMapping("/filter-list")
	public List<SomeBean> retrieveListSomeBean() {
		
		return Arrays.asList(new SomeBean("v1","v2","v3","v4","v5"),new SomeBean("v11","v12","v13","v4","v5"));
	}
	
	@GetMapping("/dynamic-filter")
	public MappingJacksonValue retrieveDynamicSomeBean() {
		SomeBean someBean =  new SomeBean("v1","v2","v3","v4","v5");
		
		//3 filter everything but field4
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.
				filterOutAllExcept("field4");
		
		//2
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		//1
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	@GetMapping("/dynamic-filter-list")
	public List<SomeBean> retrieveDynamicListSomeBean() {
		
		return Arrays.asList(new SomeBean("v1","v2","v3","v4","v5"),new SomeBean("v11","v12","v13","v4","v5"));
	}
}
