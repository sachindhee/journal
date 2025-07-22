package net.engineeringdigest.journalApp.Service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test

    public void getRedis()
    {
        redisTemplate.opsForValue().set("email", "90228537Prasuram@gmail.com");
        Object email = redisTemplate.opsForValue().get("email");
        int i =9;

    }
}
