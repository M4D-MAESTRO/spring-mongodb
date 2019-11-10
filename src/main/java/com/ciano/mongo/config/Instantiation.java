package com.ciano.mongo.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.ciano.mongo.model.Post;
import com.ciano.mongo.model.User;
import com.ciano.mongo.model.dto.AuthorDTO;
import com.ciano.mongo.repositories.PostRepository;
import com.ciano.mongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PostRepository postRepo;

	@Override
	public void run(String... args) throws Exception {
		userRepo.deleteAll();
		postRepo.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		userRepo.saveAll(Arrays.asList(maria, alex, bob));

		Post post1 = new Post(null, LocalDate.of(2018, 2, 21), "Partiu viagem", "Vou viajar para São Paulo, abraços!",
				new AuthorDTO(maria));
		Post post2 = new Post(null, LocalDate.of(2018, 2, 23), "Bom dia", "Acordei feliz hoje :D",
				new AuthorDTO(maria));
		postRepo.saveAll(Arrays.asList(post1, post2));

		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepo.save(maria);

	}

}
