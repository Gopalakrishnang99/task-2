package com.gopal.task.two.common;

import com.gopal.task.two.model.User;
import org.springframework.batch.item.ItemProcessor;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * ItemProcessor implementation of User domain entity.
 * Will only process inactive users, i.e, filters out the
 * inactive users to be sent to the next stage
 */
public class UserItemProcessor implements ItemProcessor<User, User> {

    /**
     * Checks if the provided User object has become inactive
     * based on the loggedInAt timestamp
     * @param item The User object
     * @return Null if the user is active else the input User object
     * @throws Exception
     */
    @Override
    public User process(User item) throws Exception {
        if (item.getLoggedInAt()
                .isBefore(OffsetDateTime.now(ZoneOffset.UTC).minusMinutes(30))
        ) {
            return item;
        }
        return null;
    }
}
