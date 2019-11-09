package com.ciano.mongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
