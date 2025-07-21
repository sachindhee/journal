package net.engineeringdigest.journalApp.Repository;


import net.engineeringdigest.journalApp.repository.UserRepositoryimpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryImplTest
{
    @Autowired
   private UserRepositoryimpl userRepositoryimpl;

    @Test
    @Disabled
    public void testUserRepositoryImpl()
    {
       Assertions.assertNotNull(userRepositoryimpl.getUserForSA());
    }

}