package com.bloc.JournalApp.Service;


import com.bloc.JournalApp.API.Response.WeatherResponse;
import com.bloc.JournalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Service
@Component
@RequestMapping("/weather")
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisService redisService;



    public WeatherResponse getWeather(String city){
        System.out.println("WeatherService called");
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if(weatherResponse!=null){
            return weatherResponse;
        }else{
            System.out.println("API Key = " + apiKey);
            String finalAPI=appCache.APP_CACHE.get("weather_api").replace("CITY",city).replace("API_KEY",apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if(body!=null){
                redisService.set("weather_of_" + city,body,300l );
            }
            return body;
        }
    }



}
