package com.codeCardS.app.Respository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.codeCardS.app.Records.Topics;

@Repository
public interface TopicsRepository extends MongoRepository<Topics,String>{

}
