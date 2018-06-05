package com.griddynamics.serviceshop.repository;

import com.griddynamics.serviceshop.exception.NotFoundException;
import com.griddynamics.serviceshop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("userRepository")
public class UserJdbcRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User findByEmail(User user) {
        User foundedUser = null;
        try {
            foundedUser = jdbcTemplate.queryForObject("Select * from user where email = ?",
                    new Object[]{user.getEmail()}, new UserRowMapper());
        } catch (DataAccessException ex) {
            throw new NotFoundException("Can not find user!");
        }
        return foundedUser;
    }

    public void addUser(User user) {
        jdbcTemplate.update("insert into user (id, email, password)"
                        + "values (?,?,?)",
                user.getId(), user.getEmail(), user.getPassword()
        );
    }

    class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            return user;
        }
    }

}
