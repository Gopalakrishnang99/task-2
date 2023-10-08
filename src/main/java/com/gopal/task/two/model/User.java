package com.gopal.task.two.model;

import lombok.Data;

import java.time.OffsetDateTime;

/**
 * Entity object representing a user
 */
@Data
public class User {

    private Long userId;

    private String email;

    private String firstName;

    private String lastName;

    private String status;

    private OffsetDateTime loggedInAt;
}
