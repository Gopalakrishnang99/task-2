package com.gopal.task.two.common;

import com.gopal.task.two.model.User;
import com.gopal.task.two.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserItemProcessorTest {

    UserItemProcessor userItemProcessor = new UserItemProcessor();

    @Test
    @DisplayName("Test to process a user item - filter active users")
    public void testToFilterActiveUsers() throws Exception {
        User user = TestUtil.getUser(true);
        User result = userItemProcessor.process(user);
        Assertions.assertNull(result);
    }

    @Test
    @DisplayName("Test to process a user item - allow inactive users")
    public void testToAllowInActiveUsers() throws Exception {
        User user = TestUtil.getUser(false);
        User result = userItemProcessor.process(user);
        Assertions.assertEquals(user,result);
    }

}