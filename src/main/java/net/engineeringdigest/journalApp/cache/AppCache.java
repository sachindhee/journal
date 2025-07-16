package net.engineeringdigest.journalApp.cache;

import net.engineeringdigest.journalApp.entity.ConfigJournalAppEntity;
import net.engineeringdigest.journalApp.repository.ConfigJournalAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }

    public Map<String , String> AppCache;

    @Autowired
   private ConfigJournalAppRepository configJournalAppRepository;

    @PostConstruct
    public void init()
    {
        AppCache=new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();

        for(ConfigJournalAppEntity configJournalAppEntity :all)
        {
            AppCache.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());

        }



    }

}
