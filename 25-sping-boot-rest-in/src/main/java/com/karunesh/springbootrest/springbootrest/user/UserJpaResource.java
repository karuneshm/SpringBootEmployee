package com.karunesh.springbootrest.springbootrest.user;

import java.util.List;
import java.util.Optional;

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
public class UserJpaResource {
	

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/jpa/users")
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	@GetMapping("/jpa/users/{userId}")
	public EntityModel<User> findById(@PathVariable int userId) {
		Optional<User> user = userRepository.findById(userId);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("user with id "+userId+ " not found");
		}
		
		
	
		EntityModel<User> resource = new EntityModel<User>(user.get());
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
	
	@PostMapping("/jpa/users")
	public ResponseEntity<User> save(@Valid @RequestBody User user) {
		User theUser = userRepository.save(user);	
		
		ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(theUser.getId()).toUri();
		
		return new ResponseEntity<User>(theUser, HttpStatus.CREATED);
		
		
		
	}
	
	@DeleteMapping("/jpa/users/{userId}")
	public void deleteByID(@PathVariable int userId) {
		
		userRepository.deleteById(userId);
	
	}
	
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> findAllPost(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("user with id "+id+ " not found");
		}
		
		List<Post> posts = user.get().getPosts();
		
		return posts;
	}
	
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Post> save(@PathVariable int id, @RequestBody Post post) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent())
		{
			throw new UserNotFoundException("user with id "+id+ " not found");
		}
		
		User theUser = user.get();
		post.setUser(theUser);
		postRepository.save(post);
		return new ResponseEntity<Post>(post, HttpStatus.CREATED);
	}

}
