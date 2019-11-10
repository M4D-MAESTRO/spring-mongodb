package com.ciano.mongo.servicies;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciano.mongo.model.User;
import com.ciano.mongo.model.dto.UserDTO;
import com.ciano.mongo.repositories.UserRepository;
import com.ciano.mongo.servicies.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	public List<User> findAll() {
		return userRepo.findAll();
	}

	public User findById(String id) {
		Optional<User> user = userRepo.findById(id);

		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User insert(UserDTO obj) {
		return userRepo.save(fromDTO(obj));
	}

	public User fromDTO(UserDTO dto) {
		return new User(dto.getId(), dto.getName(), dto.getEmail());
	}
	
	public void delete(String id) {
		findById(id);
		userRepo.deleteById(id);
	}
	
	public User update(UserDTO dto) {
		return userRepo.save(updateData(dto));
	}

	private User updateData(UserDTO dto) {
		User user = findById(dto.getId());
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		
		return user;
	}

}
