package com.zsf.boot.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
public class RedisController {

    @Autowired
    private Redisson redisson;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/deduct_stock")
    public String deductStock(){
        String lockKey = "product_101";

//        Boolean result = redisTemplate.opsForValue().setIfAbsent(lockKey, "jiasuo");
//        redisTemplate.expire(lockKey,10, TimeUnit.SECONDS);

        /*String clientId = UUID.randomUUID().toString();
        Boolean result = redisTemplate.opsForValue().setIfAbsent(lockKey, clientId, 10, TimeUnit.SECONDS);
        if(!result){
            return "请等待！";
        }*/
        // 获取锁对象
        RLock lock = redisson.getLock(lockKey);
        try{
            // 加锁
            lock.lock();
            Integer stock = Integer.parseInt((String) redisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                redisTemplate.opsForValue().set("srock", realStock);
                System.out.println("扣减成功，剩余库存：" + realStock);
            } else {
                System.out.println("扣减失败，库存不足！");
            }
        }finally {
            /*if(clientId.equals(redisTemplate.opsForValue().get(lockKey))){
                redisTemplate.delete(lockKey);
            }*/
            // 释放锁
            lock.unlock();
        }


        return "end";
    }

}
