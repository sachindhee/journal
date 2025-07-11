package net.engineeringdigest.journalApp.Service;


import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.entity.journalEntity;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;


    @Transactional
    public void saveEntry(journalEntity JournalEntity, String userName)
    {
        try {
            User user1 = userService.findByUserName(userName);

            JournalEntity.setDate(LocalDateTime.now());
            journalEntity saved= journalEntryRepository.save(JournalEntity);
            user1.getJournalEntityList().add(saved);
            userService.saveEntry(user1);

        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error Occurred While Saving the Entry " , e);
        }

    }



    public void saveEntry(journalEntity JournalEntity)
    {
        journalEntryRepository.save(JournalEntity);

    }



   public List<journalEntity> getAll()
   {
       return journalEntryRepository.findAll();
   }




   public Optional<journalEntity> findById(ObjectId id)
   {
       return journalEntryRepository.findById(id);
   }


    @Transactional
   public boolean DelectId(ObjectId Id, String userName)
   {
       boolean removed=false;
       try{

           User byUserName = userService.findByUserName(userName);
           removed= byUserName.getJournalEntityList().removeIf(x-> x.getId().equals(Id));

           if(removed)
           {
               userService.saveEntry(byUserName);
               journalEntryRepository.deleteById(Id);
           }
       } catch (Exception e) {
           System.out.print(e);
           throw new RuntimeException("An erroe occured while deleteing the entry "+e);

       }

return removed;

   }



}
