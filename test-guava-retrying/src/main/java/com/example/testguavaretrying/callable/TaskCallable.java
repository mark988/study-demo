package com.example.testguavaretrying.callable;

import com.example.testguavaretrying.service.CacheService;
import com.example.testguavaretrying.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

/***
 * @author : 马晓光
 * @date   : 2019/7/18
 * @description :
 **/
@Slf4j
public class TaskCallable implements Callable<Boolean> {

    @Autowired
    private TestService testService;

    @Autowired
    private CacheService cacheService;

    @Override
    public Boolean call() throws Exception {
       /*
       log.info(" ====================task call able ====================");
        List<String> list= testService.query();
        if(list == null){
            log.info("----发生了异常----");
        }
        if(! Optional.empty().equals(list)){
            cacheService.save("list",list);
            return true;
        }
        */
        return false;
    }
}
