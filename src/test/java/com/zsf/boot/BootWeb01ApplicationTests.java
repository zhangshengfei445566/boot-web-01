package com.zsf.boot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zsf.boot.bean.UserBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class BootWeb01ApplicationTests {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;
    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("key1","val1");
        System.out.println(redisTemplate.opsForValue().get("key1"));
    }

    @Test
    public void test() throws JsonProcessingException {
        UserBean user = new UserBean("zsf",24);
        String jsonString = new ObjectMapper().writeValueAsString(user);
        redisTemplate.opsForValue().set("user",jsonString);
        System.out.println(redisTemplate.opsForValue().get("user"));
    }

}
