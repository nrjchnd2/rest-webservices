package com.neeraj.rest.webservices.restwebservices.User;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.neeraj.rest.webservices.restwebservices.post.Post;
import com.neeraj.rest.webservices.restwebservices.post.PostRepository;

@RestController
public class UserJpaController {
	@Autowired
	private PostRepository postrepo;

	@Autowired
	UserRespository repo;

	@GetMapping("/jpa/users")
	public List<User> getAllUsers() {
		return repo.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public Resource<User> getUserById(@PathVariable int id) {
		Optional<User> user = repo.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException(String.format("id %s not found", id));
		}
		Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		User addedUser = repo.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/jpa/users/{id}")
	public void deleteUserById(@PathVariable int id) {
		repo.deleteById(id);

	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> getAllUsersPosts(@PathVariable int id){
		Optional<User> findById = repo.findById(id);
		if(!findById.isPresent()) {
			throw new UserNotFoundException(String.format("id %s not found", id));
		}
		return findById.get().getPost();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<User> addUserPost(@PathVariable int id,@RequestBody Post post) {
		Optional<User> findById = repo.findById(id);
		if(!findById.isPresent()) {
			throw new UserNotFoundException(String.format("id %s not found", id));
		}
		post.setUser(findById.get());
		postrepo.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}
