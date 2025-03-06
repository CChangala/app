package com.codeCardS.app.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeCardS.app.Records.CourseProgress;
import com.codeCardS.app.Records.TopicProgress;
import com.codeCardS.app.Records.UserProgress;
import com.codeCardS.app.Respository.ProgressRepository;
import com.codeCardS.app.Respository.TopicsRepository;
import com.codeCardS.app.Records.Topics;

@Service
public class ProgressCalculationService {

    @Autowired
    private ProgressRepository progressRepository;

    @Autowired
    private TopicsRepository topicsRepository;

    public List<Response> calculateAllCoursesProgress(String userId) {

        Optional<UserProgress> userProgressOpt = progressRepository.findByUserId(userId);
        if (!userProgressOpt.isPresent()) {
            return new ArrayList<>();  // Return empty list if user not found
        }
        
        UserProgress userProgress = progressRepository.findByUserId(userId)
            .orElseThrow(() -> new IllegalStateException("User not found"));

        // Fetch all topics once to reduce database calls
        List<Topics> allTopics = topicsRepository.findAll();

        // Convert list of Topics to a Map of courseId to count of topics
        Map<String, Integer> courseIdToTotalTopics = allTopics.stream()
            .collect(Collectors.groupingBy(Topics::courseId, Collectors.summingInt(topics -> topics.topic().size())));

        // Calculate progress for each course
        return userProgress.progress().stream()
            .map(cp -> calculatePercentage(cp, courseIdToTotalTopics.getOrDefault(cp.courseId(), 0)))
            .collect(Collectors.toList());
    }

    public List<Response> calculateProgress(String userId, String courseId) {
        UserProgress userProgress = progressRepository.findByUserId(userId)
            .orElseThrow(() -> new IllegalStateException("User not found"));

        Optional<Topics> courseTopics = topicsRepository.findByCourseId(courseId);
        int totalTopics = courseTopics.map(ct -> ct.topic().size()).orElse(0);

        return userProgress.progress().stream()
            .filter(cp -> cp.courseId().equals(courseId))  // Filter only the specific course
            .map(cp -> calculatePercentage(cp, totalTopics))
            .collect(Collectors.toList());
    }

    private Response calculatePercentage(CourseProgress courseProgress, int totalTopics) {
        int completedTopics = (int) courseProgress.topicsCompleted().stream()
            .filter(TopicProgress::isCompleted)
            .count();

        double percentage = totalTopics > 0 ? (double) completedTopics / totalTopics * 100 : 0.0;
        return new Response(courseProgress.courseId(), percentage);
    }

    

    public record Response(String courseId, double percentage) {}
}