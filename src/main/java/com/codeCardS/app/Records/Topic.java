package com.codeCardS.app.Records;

import org.springframework.data.annotation.Id;
import java.util.List;

public record Topic(
    String name,
    @Id
    String topicId,
    String link,
    List<String> flashcards
) {



}
