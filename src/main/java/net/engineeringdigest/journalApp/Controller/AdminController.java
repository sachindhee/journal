package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
   private UserService userService;

    @Autowired
   private AppCache appCache;

    @GetMapping("/all-user")
    public ResponseEntity<?> getAllUser(){
        List<User> all = userService.getAll();
        if(all != null && !all.isEmpty())
        {
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }
    @PostMapping("/create-user")
    public void createUser(@RequestBody User user)
    {
        userService.saveAdmin(user);
    }

    @GetMapping("/create-clearCache")
    public void clearCache()
    {
        appCache.init();
    }

}
