package com.example.mymicroservice.hello.user;


import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;



@RestController
public class UserResource {
	//connect UserDAO to Controller
	@Autowired
	private UserDAO service;
	
//==============GET URI
	//retrieve All Users : * mapping!!
	@GetMapping("/users")
	public List<User> retrieveAll(){
		return service.findAll();
	}
	

	//Retrieve one User : *add PathVariable
	@GetMapping("/user/{id}")
	public EntityModel<User> retrieveOne(@PathVariable int id){
		User user = service.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("Id "+ id +" is not existed");
		}
		
		EntityModel<User> resource = EntityModel.of(user);
		
		WebMvcLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retrieveAll());
		
		resource.add(linkTo.withRel("all-users"));
		
		//HATEOAS
		
		return resource;
	}
	
//=============POST URI
	
	/*Create : *@RequestBody
	@PostMapping("/user")
	public void create(@RequestBody User user) {
		User savedUser = service.save(user);
		
	}*/
	
	//Create & return the created URI
	@PostMapping("/user")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
		User savedUser = service.save(user);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
	
	//Delete
	@DeleteMapping("/user/{id}")
	public void delete(@PathVariable int id) {
		User user = service.delete(id);
		if(user == null) {
			throw new UserNotFoundException("Id "+ id +" is not existed");
		}
	}
	

}
