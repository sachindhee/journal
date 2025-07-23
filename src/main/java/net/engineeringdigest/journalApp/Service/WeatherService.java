package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.apiResponce.WeatherResponce;
import net.engineeringdigest.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Autowired
   private  RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Value("${Weather.api.key}")
    private  String apikey;

    @Autowired
    private RedisService redisService;


    public  WeatherResponce getWeather(String city)
    {
        WeatherResponce weatherResponce = redisService.get("weather_of_ " + city, WeatherResponce.class);
        if (weatherResponce != null)
        {
            return weatherResponce;
        }
        else {
            String finalapi =appCache.AppCache.get(AppCache.keys.WEATHER_API.toString()).replace("<city>",city).replace("<apikey>",apikey);
            ResponseEntity<WeatherResponce> responce =restTemplate.exchange(finalapi, HttpMethod.GET, null, WeatherResponce.class);
            WeatherResponce body = responce.getBody();
            if (body !=null)
            {
                redisService.set("weather_of_ "+city,body,300l);
            }
            return body;

        }


    }



}
