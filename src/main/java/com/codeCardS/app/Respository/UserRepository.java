package com.codeCardS.app.Respository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.codeCardS.app.Records.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    
}
