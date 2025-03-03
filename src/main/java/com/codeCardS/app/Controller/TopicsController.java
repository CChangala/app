package com.codeCardS.app.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.codeCardS.app.Records.Topics;
import com.codeCardS.app.Service.TopicServic;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class TopicsController {

    private TopicServic topicServic;

     public TopicsController(TopicServic topicServic){
        this.topicServic = topicServic;
     }

     
     //to add all topics to the course
    @CrossOrigin(origins = "*")
    @PostMapping("/topics/add")
    public String createTopics(@RequestBody Topics topics) {
        return topicServic.createTopics(topics);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/topic/{courseId}")
    public List<Topics> getTopics(@PathVariable String courseId) {
        return topicServic.getTopics(courseId);
    }

}
