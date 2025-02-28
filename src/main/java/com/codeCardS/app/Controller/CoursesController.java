package com.codeCardS.app.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.codeCardS.app.Records.Courses;
import com.codeCardS.app.Service.CoursesService;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class CoursesController {

    private final CoursesService coursesService;

    public CoursesController(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/courses")
    public List<Courses> getCourses() {
        return coursesService.getCourses();
    }

    @PostMapping("/courses/create")
    public List<Courses> createCourse(@RequestBody List<Courses> courses) {
        return coursesService.createCourse(courses);
    }

    @PutMapping("/courses/add")
    public Courses addCourses(@RequestBody Courses course){
            return coursesService.updateCourse(course);
    }

    @DeleteMapping("/crouses/delete")
    public void deleteCourse() {
        coursesService.deleteCourse();
    }
    
}
