package com.gopal.task.two.common;

import com.gopal.task.two.model.User;
import org.springframework.batch.item.ItemProcessor;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class UserItemProcessor implements ItemProcessor<User, User> {

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
