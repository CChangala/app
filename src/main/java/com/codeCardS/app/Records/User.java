package com.codeCardS.app.Records;

public record User(
    String email,
    String password,
    String userId,
    Topics topics
) {

}
