package com.codeCardS.app.Respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.codeCardS.app.Records.UserProgress;

@Repository
public interface ProgressRepository extends MongoRepository<UserProgress,String>{
    Optional<UserProgress> findByUserId(String userId);
}
