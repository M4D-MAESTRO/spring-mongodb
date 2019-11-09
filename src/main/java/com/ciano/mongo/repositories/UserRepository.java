package com.ciano.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ciano.mongo.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
