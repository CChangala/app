package com.codeCardS.app.Records;

import org.springframework.data.annotation.Id;

public record Topic(
    String name,
    @Id
    String topicId,
    String link
) {



}
