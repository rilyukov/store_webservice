package com.griddynamics.serviceshop;

import com.griddynamics.serviceshop.model.User;
import com.griddynamics.serviceshop.repository.ProductJdbcRepository;
import com.griddynamics.serviceshop.repository.UserJdbcRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
   /* @TestConfiguration
    static class UserRepositoryTestImpl {

        @Bean
        public UserJdbcRepository userJdbcRepository() {
            return new UserJdbcRepository();
        }
    }*/

    @Autowired
    UserJdbcRepository userJdbcRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void tuser_should_be_added() {
        //given
        User user = new User(1, "asd@qwe.com", "Secret");

        //when
        userJdbcRepository.addUser(user);

        //then
        Assert.assertNotNull(jdbcTemplate.queryForObject("select * from user", new BeanPropertyRowMapper<>(User.class)));


    }

    @Test
    public void user_should_be_founded_by_email() {
        //given
        User user = new User(2, "zxc@qwe.com", "Secret");

        //when
        userJdbcRepository.addUser(user);

        //then
        Assert.assertNotNull(userJdbcRepository.findByEmail(user));
        Assert.assertEquals(user.getEmail(), userJdbcRepository.findByEmail(user).getEmail());
    }

}
