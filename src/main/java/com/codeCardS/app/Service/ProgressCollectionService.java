package com.codeCardS.app.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeCardS.app.Records.CourseProgress;
import com.codeCardS.app.Records.TopicProgress;
import com.codeCardS.app.Records.UserProgress;
import com.codeCardS.app.Respository.ProgressRepository;

@Service
public class ProgressCollectionService {

    @Autowired
    private ProgressRepository progressRepository;

    public void startCourse(String userId, String courseId){
        UserProgress userProgress = progressRepository.findById(userId)
            .orElse(new UserProgress(userId, new ArrayList<>()));

        boolean courseExists = userProgress.progress().stream()
        .anyMatch(cp -> cp.courseId().equals(courseId));

        if (!courseExists) {
            CourseProgress newCourseProgress = new CourseProgress(courseId, new ArrayList<>());
            List<CourseProgress> updatedCourseProgress = new ArrayList<>(userProgress.progress());
            updatedCourseProgress.add(newCourseProgress);
            UserProgress updatedUserProgress = new UserProgress(userProgress.userId(), updatedCourseProgress);
            progressRepository.save(updatedUserProgress);
        }
    }

    // Update topic progress
    public void updateTopicProgress(String userId, String courseId, String topicId, boolean isCompleted) {
        UserProgress userProgress = progressRepository.findByUserId(userId)
        .orElseThrow(() -> new IllegalStateException("User not found"));

    // This will handle creating a new course progress if it does not exist
    boolean courseExists = userProgress.progress().stream()
        .anyMatch(cp -> cp.courseId().equals(courseId));

    if (!courseExists) {
        userProgress.progress().add(new CourseProgress(courseId, new ArrayList<>()));
    }

    List<CourseProgress> updatedCourseProgress = userProgress.progress().stream()
        .map(cp -> {
            if (cp.courseId().equals(courseId)) {
                List<TopicProgress> updatedTopics = new ArrayList<>(cp.topicsCompleted());
                updatedTopics.removeIf(tp -> tp.topicId().equals(topicId)); // Remove the topic if it exists
                if (isCompleted) { // Add or update the topic progress
                    updatedTopics.add(new TopicProgress(topicId, true, Instant.now()));
                }
                return new CourseProgress(cp.courseId(), updatedTopics);
            } else {
                return cp;
            }
        }).collect(Collectors.toList());

        UserProgress updatedUserProgress = new UserProgress(userProgress.userId(), updatedCourseProgress);
        progressRepository.delete(userProgress);
        progressRepository.save(updatedUserProgress);
    }

    public UserProgress getAllCoursesStartedByUser(String userId) {
        return progressRepository.findByUserId(userId)
        .orElse(new UserProgress(userId, Collections.emptyList()));  // Return an empty UserProgress if no progress found
    }

    public void deleteAll(){
        progressRepository.deleteAll();
    }


}
