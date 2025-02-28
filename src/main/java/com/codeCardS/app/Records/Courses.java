package com.codeCardS.app.Records;

import org.springframework.data.annotation.Id;

public record Courses(
    @Id
    String courseId,
    String title,
    String description,
    String details,
    String benefits
) {

}
