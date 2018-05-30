package com.griddynamics.serviceshop;

import com.griddynamics.serviceshop.model.User;
import com.griddynamics.serviceshop.repository.UserJdbcRepository;
import com.griddynamics.serviceshop.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserJdbcRepository userJdbcRepository;

    @Test
    public void addUserTest(){
       //given
        User user = new User();
        user.setEmail("qwe@asd.com");
        user.setPassword("secret");

        //when
        userService.addUser(user);
        User storedUser = userJdbcRepository.findByEmail(user);

        //then
        Assert.assertNotNull(storedUser);
        Assert.assertEquals(user.getEmail(),storedUser.getEmail());
        Assert.assertEquals(1L, storedUser.getId());

    }

    @Test
    public void loginUserTest(){
        //given
        User user = new User();
        user.setEmail("zxc@asd.com");
        user.setPassword("secret");

        //when
        userService.addUser(user);
        User userToLogin = new User(1,"zxc@asd.com", "secret");

        //then
        assertTrue(userService.logIn(userToLogin));

    }
}
