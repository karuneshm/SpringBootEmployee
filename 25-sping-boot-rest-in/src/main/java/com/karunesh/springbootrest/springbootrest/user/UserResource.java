package com.karunesh.springbootrest.springbootrest.user;




import java.util.List;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.karunesh.springbootrest.springbootrest.exception.UserNotFoundException;

@RestController
@RequestMapping("/")
public class UserResource {
	
	@Autowired
	private UserDaoService daoService;

	
	@GetMapping("users")
	public List<User> findAll() {
		return daoService.findAll();
	}
	
	@GetMapping("users/{userId}")
	public EntityModel<User> findById(@PathVariable int userId) {
		User user = daoService.findById(userId);
		
		if(user == null) {
			throw new UserNotFoundException("user with id "+userId+ " not found");
		}
	
		EntityModel<User> resource = new EntityModel<User>(user);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findAll());
		ControllerLinkBuilder linkTo2 = linkTo(methodOn(this.getClass()).findById(userId));
        resource.add(linkTo.withRel("all-users"));
        resource.add(linkTo2.withRel("self"));
				
		
		return resource;
	}
	
//	@PostMapping("users")
//	public ResponseEntity<User> save(@RequestBody User user) {
//		User theUser = daoService.save(user);	
//		return new ResponseEntity<User>(theUser, HttpStatus.CREATED);
//	}
	
	@PostMapping("users")
	public ResponseEntity<User> save(@Valid @RequestBody User user) {
		User theUser = daoService.save(user);	
		
		ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(theUser.getId()).toUri();
		
		return new ResponseEntity<User>(theUser, HttpStatus.CREATED);
		
		
		
	}
	
	@DeleteMapping("users/{userId}")
	public void deleteByID(@PathVariable int userId) {
		User user = daoService.DeleteById(userId);
		
		if(user == null) {
			throw new UserNotFoundException("user with id "+userId+ " not found");
		}
	}

}
