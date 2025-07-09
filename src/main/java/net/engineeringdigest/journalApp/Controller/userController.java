package net.engineeringdigest.journalApp.Controller;


import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> GtAllUser() {
        return userService.getAll();

    }


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user)

    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName  = authentication.getName();
        User user1 = userService.findByUserName(userName);
        if(user1 != null)
        {
            user1.setUserName(user.getUserName());
            user1.setPassword(user.getPassword());
            userService.savenewEntry(user1);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

   @DeleteMapping
    public ResponseEntity<?> DeleteById()

    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());


        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }




}
