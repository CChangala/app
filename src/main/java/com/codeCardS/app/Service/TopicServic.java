package com.codeCardS.app.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeCardS.app.Records.Topic;
import com.codeCardS.app.Records.Topics;
import com.codeCardS.app.Respository.CoursesRepository;
import com.codeCardS.app.Respository.TopicsRepository;

@Service
public class TopicServic {

    @Autowired
    private TopicsRepository topicsRepository;

    @Autowired
    private CoursesRepository coursesRepository;

    public TopicServic(TopicsRepository topicsRepository) {
        this.topicsRepository = topicsRepository;
    }

    public List<Topics> getTopics(){
        return topicsRepository.findAll();
    }

    public String createAllTopics(List<Topics> topics){
        for (Topics eachtopic : topics) {
            String courseId = coursesRepository.findAll().stream()
                .filter(course -> course.title().equals(eachtopic.title()))
                .map(course -> course.courseId()).findFirst()
                .orElseThrow(() -> new RuntimeException("Course not found"));

            List<Topic> topicsWithIds = eachtopic.topic().stream()
                .map(topic -> new Topic(topic.name(), UUID.randomUUID().toString(),topic.link(),topic.flashcards()))
                .collect(Collectors.toList());

            Topics newTopic = new Topics(courseId, eachtopic.title(), topicsWithIds);
            topicsRepository.save(newTopic);
        }
        return "Topics created Successfully";
    }
    public String createTopics(Topics topics){
        String courseId = coursesRepository.findAll().stream()
        .filter(course -> course.title().equals(topics.title()))
        .map(course -> course.courseId()).findFirst()
        .orElseThrow(()->new RuntimeException("Course not found"));
        List<Topic> topicsWithIds = topics.topic().stream().map(
            topic -> new Topic(topic.name(),UUID.randomUUID().toString(),topic.link(),topic.flashcards())
        ).collect(Collectors.toList());
        Topics newTopic = new Topics(courseId,topics.title(),topicsWithIds);
        topicsRepository.save(newTopic);
        return "Topic created Successfully";

    }

    public Topics getTopicsByID(String courseId){
        return topicsRepository.findAll().stream()
        .filter(topics -> topics.courseId().equals(courseId))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Topic not found"));
    }

    public void deleteTopics(){
        topicsRepository.deleteAll();
    }

}
