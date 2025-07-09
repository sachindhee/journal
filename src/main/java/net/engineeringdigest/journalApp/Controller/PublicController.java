package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;

    @GetMapping("/Halth-Check")
    public String Halth()
    {
        return "ok";
    }

    @PostMapping("/create-user")
    public void create(@RequestBody User user)
    {
        userService.savenewEntry(user);
    }
}
