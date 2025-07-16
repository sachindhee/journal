package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static  org.mockito.Mockito.*;


public class UserDetailServiceImplTests {

    @InjectMocks
    private UserDetailServiceImpl userDetailServiceImpl;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void One()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Disabled
    void loadUserByUsernameTests()
    {
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("ram").password("randawa").roles(new ArrayList<>()).build());
      UserDetails user = userDetailServiceImpl.loadUserByUsername("ram");
    }



}
