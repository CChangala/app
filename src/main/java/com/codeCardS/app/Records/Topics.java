package com.codeCardS.app.Records;

import java.util.List;

public record Topics(
    String courseId,
    String title,
    List<Topic> topic
) {

}
