package com.codeCardS.app.Respository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.codeCardS.app.Records.Courses;

@Repository
public interface CoursesRepository extends MongoRepository<Courses, String> {

    
}
