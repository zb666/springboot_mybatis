package com.itheima;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.domain.UserBean;
import com.itheima.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootMybatisApplication.class)
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    UserRepository userRepository;

    @Test
    public void test() throws JsonProcessingException {
        String userListJson = redisTemplate.boundValueOps("user.findAll").get();
        if (userListJson == null) {
            List<UserBean> userBeans = userRepository.findAll();
            ObjectMapper objectMapper = new ObjectMapper();
            String userJson = objectMapper.writeValueAsString(userBeans);
            redisTemplate.boundValueOps("user.findAll").set(userJson);
            userListJson = userJson;
        } else {
            System.out.println("--- 从Redis缓存中获取到了User的数据");
        }
        System.out.println(userListJson);
    }

}
