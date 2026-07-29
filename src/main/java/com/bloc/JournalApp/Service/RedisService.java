package com.bloc.JournalApp.Service;


import com.bloc.JournalApp.API.Response.WeatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(String key, Class<T> entityClass){
        try {
            Object o = redisTemplate.opsForValue().get(key);
            if(o==null){
                return null;
            }
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(o.toString(), entityClass);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public void set(String key,Object o,Long ttl) {
        try {
            System.out.println("Saving key: " + key);
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonValue=objectMapper.writeValueAsString(o);
            System.out.println(jsonValue);
            redisTemplate.opsForValue().set(key, jsonValue, ttl, TimeUnit.SECONDS);
            System.out.println("Saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
