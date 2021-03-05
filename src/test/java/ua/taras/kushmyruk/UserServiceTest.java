package ua.taras.kushmyruk;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import ua.taras.kushmyruk.model.User;
import ua.taras.kushmyruk.model.UserRole;
import ua.taras.kushmyruk.repository.UserRepository;
import ua.taras.kushmyruk.service.impl.UserServiceImpl;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    private static User user;

    @BeforeClass
    public static void createUser(){
        user = new User();
        user.setUsername("Ricardo");
        user.setPassword("12345");
        user.setEmail("Lol@gmail.com");
        user.setActive(true);
        user.setUserRoles(Collections.singleton(UserRole.USER));
    }

    @Test
    public void loadService(){
        Assert.assertNotNull(userService);
    }

    @Test
    public void testUserRegistration(){
        boolean userAdded = userService.addUser(user.getUsername(), user.getPassword(), user.getEmail());
        Assert.assertEquals(true, userAdded);
    }

    @Test
    public void testGetUser(){
        UserDetails userFromDb = userService.loadUserByUsername(user.getUsername());
        Assert.assertEquals(user.getUsername(), userFromDb.getUsername());
    }

    @Test
    public void changePasswordTest(){
        String changePasswordMessage = userService.changePassword(user, "2244", user.getEmail());
        Assert.assertEquals("Password was  successfully changed", changePasswordMessage);
        UserDetails userFromDb = userService.loadUserByUsername(user.getUsername());
        Assert.assertEquals("2244", userFromDb.getPassword());
    }

    @Test
    public void changeEmail(){
        String changeEmailMessage = userService.changePassword(user, user.getPassword(), "lw@gmail.com");
        Assert.assertEquals("Email was successfully changed", changeEmailMessage);
        User userFromDb = userRepository.findByUsername(user.getUsername());
        Assert.assertEquals("lw@gmail.com", userFromDb.getEmail());
    }

    @Test
    public void changePasswordAndEmailTest(){
        String changePasswordAndEmailMessage = userService.changePassword(user, "54321", "kk@gmail.com");
        Assert.assertEquals("Password and email was successfully changed", changePasswordAndEmailMessage);
        User userFromDb = userRepository.findByUsername(user.getUsername());
        Assert.assertEquals("54321", userFromDb.getPassword());
        Assert.assertEquals("kk@gmail.com", userFromDb.getEmail());
    }
}
