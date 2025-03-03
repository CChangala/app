package com.codeCardS.app.Records;

import java.util.List;

import org.springframework.data.annotation.Id;

public record UserProgress(@Id String userId, List<CourseProgress> progress) {}
