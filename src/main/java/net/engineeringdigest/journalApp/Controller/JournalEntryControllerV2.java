package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Service.JournalEntryService;
import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.entity.journalEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;




    @GetMapping
    public ResponseEntity<?> getAllJournalEntryOfAll()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User user = userService.findByUserName(userName);

        List<journalEntity> all = user.getJournalEntityList();
        if (all !=null && !all.isEmpty())
        {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }





    @PostMapping
    public ResponseEntity<journalEntity> createEntry(@RequestBody journalEntity myEntry )
    {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();

            journalEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<journalEntity> getjournalgetbyid(@PathVariable ObjectId myId)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
      User user=  userService.findByUserName(userName);
     List<journalEntity> collect= user.getJournalEntityList().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());

     if (!collect.isEmpty())
     {
         Optional<journalEntity> JournalEntity = journalEntryService.findById(myId);


        if (JournalEntity.isPresent())
        {
            return new ResponseEntity<>(JournalEntity.get(), HttpStatus.OK);
        }
     }

        return new ResponseEntity<>( HttpStatus.NOT_FOUND);

    }




    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> etjournalgetbydelet(@PathVariable ObjectId myId )
    {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
     boolean a = journalEntryService.DelectId(myId , userName);
     if (a)
     {
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }
     else
     {

         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }


    }






    @PutMapping("/id/{userName}/{myId}")
    public ResponseEntity<journalEntity> updateJournalById
            (@PathVariable ObjectId myId,
             @RequestBody journalEntity newEntry

            )
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user=  userService.findByUserName(userName);
        List<journalEntity> collect= user.getJournalEntityList().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());

        if (!collect.isEmpty())
        {
            Optional<journalEntity> JournalEntity = journalEntryService.findById(myId);


            if (JournalEntity.isPresent())
            {
                journalEntity old = JournalEntity.get();
                old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
                old.setTital(newEntry.getTital() != null && !newEntry.getTital().equals("") ? newEntry.getTital() : old.getTital());
                journalEntryService.saveEntry(old);

                return new ResponseEntity<>(old ,HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
