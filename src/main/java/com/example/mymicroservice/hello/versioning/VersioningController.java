package com.example.mymicroservice.hello.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {
	
	@GetMapping("v2/person")
	public Person2 person2() {
		 return new Person2("shinhee kim");
	 }
	@GetMapping("v1/person")
	public Person1 person1() {
		 return new Person1(new Name("shinhee kim","kim"));
	 }
	
	//-------Control with parameter
	
	@GetMapping(value="/person/param", params = "version=2")
	public Person2 personParam2() {
		 return new Person2("Ryna Kwon");
	 }
	@GetMapping(value="/person/param", params = "version=1")
	public Person1 personParam1() {
		 return new Person1(new Name("Ryna","kwon"));
	 }

	//-------Control with hearder
	
	@GetMapping(value="/person/header", headers = "X-API-VERSION=2")
	public Person2 header2() {
		 return new Person2("Ryna Kwon");
	 }
	@GetMapping(value="/person/header", headers = "X-API-VERSION=1")
	public Person1 header1() {
		 return new Person1(new Name("Ryna","kwon"));
	 }
	
	//-------Control with produces
	
	@GetMapping(value="/person/produces", produces = "application/vnd.company.app-v2+json")
	public Person2 produces2() {
		 return new Person2("Ryna Kwon");
	 }
	@GetMapping(value="/person/produces", produces = "application/vnd.company.app-v1+json")
	public Person1 produces1() {
		 return new Person1(new Name("Ryna","kwon"));
	 }
	 
	
	/*factors need to be considered
	
	*URI pollution  : no for Header & Produces (params are hidden)
	*Misuse of HTTP headers
	*Caching : no for Header & Produces
	*Can we execute the request on the browser?  : no for Header & Produces
	*API documentation
	*/
	
}
