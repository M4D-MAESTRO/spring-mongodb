package com.ciano.mongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ciano.mongo.model.User;
import com.ciano.mongo.model.dto.UserDTO;
import com.ciano.mongo.servicies.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<?> findAll() {
		List<User> users = userService.findAll();
		return ResponseEntity.ok(users.stream().map(u -> new UserDTO(u)).collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable String id) {
		User user = userService.findById(id);
		return ResponseEntity.ok(new UserDTO(user));
	}
	
	@GetMapping("/{id}/posts")
	public ResponseEntity<?> findPosts(@PathVariable String id) {
		User user = userService.findById(id);
		return ResponseEntity.ok(user.getPosts());
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody UserDTO dto) {
		User user = userService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody UserDTO dto) {
		return ResponseEntity.ok(userService.update(dto));
	}

}
