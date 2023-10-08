package com.gopal.task.two.util;

import com.gopal.task.two.model.User;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class TestUtil {

    public static User getUser(boolean active) {
        User user = new User();
        user.setUserId(1L);
        user.setEmail("email@email.com");
        user.setFirstName("first_name");
        user.setLastName("last_name");
        user.setStatus(active ? "ACTIVE" : "INACTIVE");
        user.setLoggedInAt(
                active ? OffsetDateTime.now(ZoneOffset.UTC) : OffsetDateTime.now(ZoneOffset.UTC).minusHours(1)
        );
        return user;
    }
}
