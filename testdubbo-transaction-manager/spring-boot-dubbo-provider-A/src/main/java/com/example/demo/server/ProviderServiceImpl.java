package com.example.demo.server;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.demo.common.enums.ResponseCodeEnum;
import com.example.demo.common.response.ObjectResponse;
import com.example.demo.entity.TStorage;
import com.example.demo.mapper.TStorageMapper;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
@Service  //(loadbalance = "consistenthash")
public class ProviderServiceImpl implements RemoteServiceI {



    @Value("${server.port}")
    private String port ;

    @Autowired
    private TStorageMapper tStorageMapper;

    @Override
    public ObjectResponse saveStorage() {
        ObjectResponse response = new ObjectResponse();
        log.info("开始执行A服务 :"+ RootContext.getXID());
        TStorage tStorage = new TStorage();
        tStorage.setName("iphone");
        tStorage.setCount(20);
        tStorage.setCommodityCode("1001");
        try {
            tStorageMapper.addStorage(tStorage);
        }catch (Exception e){
            response.setStatus(ResponseCodeEnum.FAIL.getCode());
            response.setMessage(ResponseCodeEnum.FAIL.getMessage()+","+e.getMessage());
            return response;
        }
        response.setStatus(ResponseCodeEnum.SUCCESS.getCode());
        response.setMessage(ResponseCodeEnum.SUCCESS.getMessage());
        log.info("当前状态:"+response.getStatus());
        return response;
    }

}
