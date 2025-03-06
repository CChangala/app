package com.codeCardS.app.Controller;

import com.codeCardS.app.Records.CourseProgress;
import com.codeCardS.app.Records.UserProgress;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeCardS.app.Service.ProgressCollectionService;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class UserProgressController {

    private ProgressCollectionService progressCollectionService;

    public UserProgressController(ProgressCollectionService progressCollectionService){
        this.progressCollectionService= progressCollectionService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/{courseId}/start")
    public ResponseEntity<?> startCourse(@RequestParam String userId, @PathVariable String courseId) {
        try {
            progressCollectionService.startCourse(userId, courseId);
            return ResponseEntity.ok().body("Course started successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error starting course: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/{courseId}/topics/{topicId}/progress")
    public ResponseEntity<?> updateTopicProgress(
        @RequestParam String userId,
        @PathVariable String courseId,
        @PathVariable String topicId,
        @RequestParam boolean isCompleted) {
        try {
            progressCollectionService.updateTopicProgress(userId, courseId, topicId, isCompleted);
            return ResponseEntity.ok().body("Topic progress updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating topic progress: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{userId}/started")
    public UserProgress getUserProgress(@PathVariable String userId) {
        return progressCollectionService.getAllCoursesStartedByUser(userId);
    }

    @CrossOrigin(origins ="*")
    @GetMapping("/{userId}/started/{courseId}")
    public CourseProgress getUserProgressByCourse(@PathVariable String userId, @PathVariable String courseId) {
        return progressCollectionService.getCourseProgressStartedByUser(userId,courseId);
    }

    @DeleteMapping("/delete")
    public void deleteAll(){
        progressCollectionService.deleteAll();
    }
    
    


}
