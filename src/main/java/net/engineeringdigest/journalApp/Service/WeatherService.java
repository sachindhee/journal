package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.apiResponce.WeatherResponce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Autowired
   private  RestTemplate restTemplate;

    private static final String apikey= "0a05d1982f2af049f28a75d1112afaf0";

    private static final String API = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    public  WeatherResponce getWeather(String city)
    {

        String finalapi = API.replace("CITY",city).replace("API_KEY",apikey);
        ResponseEntity<WeatherResponce> responce =restTemplate.exchange(finalapi, HttpMethod.GET, null, WeatherResponce.class);
        WeatherResponce body = responce.getBody();
        return body;

    }


}
