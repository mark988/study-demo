package com.example.demo2.miaosha;

import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class Consumer implements EventHandler<UserVO> {



    @Override
    public void onEvent(UserVO event, long sequence, boolean endOfBatch) throws Exception {

        log.info(" name :{} ,age:{} ",event.getName(),event.getAge());


      //  User  u = new User();
     //   BeanUtils.copyProperties(event,u);
//        event.getUserMapper().insert(u);

        log.info("insert db success time:{}",new Date());
    }

}
