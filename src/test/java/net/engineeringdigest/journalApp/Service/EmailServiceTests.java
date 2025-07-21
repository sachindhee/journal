package net.engineeringdigest.journalApp.Service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
   private EmailService emailService;

    @Test
    @Disabled
    void testSendMail()
    {
        emailService.sendMailSender("harsyadav826@gmail.com",
                "TextIng java main Sender",
                " Hi , aap Kese ho mamata");
    }

}
