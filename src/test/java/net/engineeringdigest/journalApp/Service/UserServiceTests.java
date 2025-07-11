package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
   private UserService userService;


    @ParameterizedTest
    @ArgumentsSource(UserArqumentsSourse.class)
    @Disabled
    public void addUserTrue(User name)
    {
        assertTrue(!userService.savenewEntry(name));

    }


    @ParameterizedTest
    @CsvSource(
            {
                    "1,1,2",
                    "2,10,12"
            }
    )
    @Disabled
    public void addUserName(int a,int b,int Expected)
    {
        assertEquals(Expected,a,b);


    }






}
