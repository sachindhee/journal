package net.engineeringdigest.journalApp.Scheduler;

import net.engineeringdigest.journalApp.Service.EmailService;
import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.entity.journalEntity;
import net.engineeringdigest.journalApp.enums.Sentiment;
import net.engineeringdigest.journalApp.repository.UserRepositoryimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryimpl userRepositoryimpl;




    @Autowired
    private AppCache appCache;



    //@Scheduled (cron = "0 * * ? * *")
   // @Scheduled (cron = "0 0 9 * * SUN")
    public void fatchUserAndSendSaMail()
    {
        List<User> users = userRepositoryimpl.getUserForSA();

        for(User user : users)
        {

            List<journalEntity> journalEntityList = user.getJournalEntityList();
            List<Sentiment> filleredEntry = journalEntityList.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x ->x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment, Integer> sentimentCount = new HashMap<>();
            for (Sentiment sentiment : filleredEntry)
            {
                if(sentiment != null)
                {
                    sentimentCount.put(sentiment, sentimentCount.getOrDefault(sentiment, 0)+1);
                }
                Sentiment mostFrequentSentiment = null;
                int maxCount  = 0;
                for(Map.Entry<Sentiment,Integer> entry : sentimentCount.entrySet())
                {
                        if(entry.getValue() > maxCount)
                        {
                            maxCount = entry.getValue();
                            mostFrequentSentiment = entry.getKey();

                        }
                }

                if (mostFrequentSentiment != null)
                {
                    emailService.sendMailSender(user.getEmail(),"Senntiment for last 7 day", mostFrequentSentiment.toString());
                }
            }
        }
    }
    @Scheduled (cron = "0 * * ? * *")
    public void clearApp()
    {
        appCache.init();

    }

}
