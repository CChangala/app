package com.codeCardS.app.Records;

import java.time.Instant;

public record TopicProgress(String topicId, Boolean isCompleted, Instant completedAt) {

}
