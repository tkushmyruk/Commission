package ua.taras.kushmyruk.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.taras.kushmyruk.controller.FacultyController;
import ua.taras.kushmyruk.model.User;
import ua.taras.kushmyruk.model.UserRole;
import ua.taras.kushmyruk.repository.UserRepository;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username) ;
    }

    public List<User> findAll() { return userRepository.findAll(); }

    public boolean addUser(String username, String password, String email) {
       User userFromDb = userRepository.findByUsername(username);
         if(userFromDb != null && username.isEmpty()){
             logger.warn(":{} already exists", username);
             return false;
         }
         User user  = new User();
         user.setUsername(username);
         user.setPassword(password);
         user.setEmail(email);
         user.setActive(true);
         user.setUserRoles(Collections.singleton(UserRole.ADMIN));
         userRepository.save(user);
        logger.info(":{} was successfully saved", user.getUsername());
        return true;
    }

    public String changePassword(User user, String newPassword, String newEmail){
        if(user.getPassword().equals(newPassword) && user.getEmail().equals(newEmail)){
            logger.warn("Nothing was changed to :{}", user.getUsername());
            return "Nothing was changed";
        }
        if(!user.getPassword().equals(newPassword) && user.getEmail().equals(newEmail)){
            user.setPassword(newPassword);
            userRepository.save(user);
            logger.info("Password was successfully changed to :{}", newPassword);
            return "Password was  successfully changed";
        }
        if(user.getPassword().equals(newPassword) && !user.getEmail().equals(newEmail)){
            user.setEmail(newEmail);
            userRepository.save(user);
            logger.info("Email was successfully changed to :{}", newEmail);
            return "Email was successfully changed";
        }
        if(!user.getPassword().equals(newPassword) && !user.getEmail().equals(newEmail)){
            user.setPassword(newPassword);
            user.setEmail(newEmail);
            userRepository.save(user);
            logger.info("Password was successfully changed to :{}" +
                    " and Email was successfully changed to :{}", newPassword, newEmail);
            return "Password and email was successfully changed";

        }
        return "Error";
    }


}

