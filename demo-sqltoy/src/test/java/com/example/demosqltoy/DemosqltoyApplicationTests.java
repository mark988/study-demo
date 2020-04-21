package com.example.demosqltoy;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.sagacity.sqltoy.dao.SqlToyLazyDao;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DemosqltoyApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource(name = "sqlToyLazyDao")
    private SqlToyLazyDao sqlToyLazyDao;

    @Test
    public void queryStaffInfo() {
        String[] paramNames = { "staffName", "status" };
        Object[] paramValue = { "陈", 1 };
        //最后一个参数是返回类型 null 则返回普通数组(可以传VO对象、Map.class)
        List<User> staffInfo = sqlToyLazyDao.findBySql("select * from t_user", null, null, null);

        User user = new User();
       // List userList=sqlToyLazyDao.findBySql("select * from t_user ", Map.class);
        System.out.println(JSON.toJSONString(staffInfo));
    }

}
