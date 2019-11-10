package com.ciano.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ciano.mongo.model.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

}
