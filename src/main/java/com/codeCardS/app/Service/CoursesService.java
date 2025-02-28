package com.codeCardS.app.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeCardS.app.Records.Courses;
import com.codeCardS.app.Respository.CoursesRepository;

@Service
public class CoursesService {

    @Autowired
    private CoursesRepository coursesRepository;

    public List<Courses> getCourses() {
        return coursesRepository.findAll();
    }

    public List<Courses> createCourse(List<Courses> courses) {
        List<Courses> coursesList = new ArrayList<>();
        for(Courses course : courses) {
            final Courses newCourse = new Courses(UUID.randomUUID().toString(), 
            course.title(), course.description(), course.details(), course.benefits());
            coursesList.add(newCourse);
            coursesRepository.save(newCourse);
        }
        return coursesRepository.findAll();
    }

    public Courses updateCourse(Courses course) {
        if(course.courseId() != null) {
            coursesRepository.findAll().stream().filter(c ->
            c.courseId().equals(course.courseId())).
            findFirst().
            ifPresentOrElse(c -> {
                Courses updatedCourse = new Courses(
                    c.courseId(), 
                    course.title(),
                    course.description(),
                    course.details(), 
                    course.benefits());
                coursesRepository.delete(c);
                coursesRepository.save(updatedCourse);
            }, () -> {
                throw new RuntimeException("Course not found");
            });
            return coursesRepository.findAll().stream().filter(c -> c.courseId()
            .equals(course.courseId())).findFirst().get();
        }
        Courses newCourse = new Courses(UUID.randomUUID().toString(), 
        course.title(), course.description(), course.details(), course.benefits());
        coursesRepository.save(newCourse);
        return newCourse;
    }

    public void deleteCourse() {
        coursesRepository.deleteAll();
    }

}
