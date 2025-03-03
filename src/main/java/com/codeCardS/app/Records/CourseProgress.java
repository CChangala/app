package com.codeCardS.app.Records;

import java.util.List;

public record CourseProgress(String courseId, List<TopicProgress> topicsCompleted) {

}
