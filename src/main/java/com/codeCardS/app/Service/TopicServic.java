package com.codeCardS.app.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeCardS.app.Records.Topic;
import com.codeCardS.app.Records.Topics;
import com.codeCardS.app.Respository.TopicsRepository;

@Service
public class TopicServic {

    @Autowired
    private TopicsRepository topicsRepository;

    public TopicServic(TopicsRepository topicsRepository) {
        this.topicsRepository = topicsRepository;
    }

    public String createTopics(Topics topics){
        List<Topic> topicsWithIds = topics.topic().stream().map(
            topic -> new Topic(topic.name(),UUID.randomUUID().toString(),topic.link())
        ).collect(Collectors.toList());
        Topics newTopic = new Topics(topics.courseId(),topicsWithIds);
        topicsRepository.save(newTopic);
        return "Topic created Successfully";

    }

    public List<Topics> getTopics(String courseId){
        return topicsRepository.findAll().stream()
        .filter(topics -> topics.courseId().equals(courseId))
        .collect(Collectors.toList());
    }

}
