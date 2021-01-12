package com.example.mymicroservice.hello.user;


import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class UserJPAResource {
	
	@Autowired
	private UserRepository repo;
	
//==============GET URI
	//retrieve All Users : * mapping!!
	@GetMapping("/jpa/users")
	public List<User> retrieveAll(){
		return repo.findAll();
	}
	

	//Retrieve one User : *add PathVariable
	@GetMapping("/jpa/user/{id}")
	public EntityModel<User> retrieveOne(@PathVariable int id){
		Optional<User> user = repo.findById(id);
		
		if (!user.isPresent())
			throw new UserNotFoundException("id-" + id);
		
		EntityModel<User> resource = EntityModel.of(user.get());
		
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
	@PostMapping("/jpa/user")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
		User savedUser = repo.save(user);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
	
	//Delete
	@DeleteMapping("/jpa/user/{id}")
	public void delete(@PathVariable int id) {
		repo.deleteById(id);
	}
	

}
