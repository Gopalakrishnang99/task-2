package com.gopal.task.two.jdbc;

import com.gopal.task.two.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

/**
 * A RowMapper implementation for the User domain entity
 */
public class UserRowMapper implements RowMapper<User> {

    /**
     *
     * @param rs The result set
     * @param rowNum The current row number
     * @return Mapped User object
     * @throws SQLException When result set throws exception
     */
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getLong("user_id"));
        user.setEmail(rs.getString("email"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setStatus(rs.getString("status"));
        user.setLoggedInAt(rs.getObject("logged_in_at", OffsetDateTime.class));
        return user;
    }
}