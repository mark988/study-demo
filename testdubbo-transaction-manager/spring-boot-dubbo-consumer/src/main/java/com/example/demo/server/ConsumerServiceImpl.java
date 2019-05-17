package com.example.demo.server;


import com.alibaba.dubbo.config.annotation.Reference;
import com.example.demo.common.enums.ResponseCodeEnum;
import com.example.demo.common.exception.DefaultException;
import com.example.demo.common.response.ObjectResponse;
import com.example.demo.entity.TAccount;
import com.example.demo.mapper.TAccountMapper;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class ConsumerServiceImpl{

    //设置重试参数3,如果重试3次仍然失败则换另外的服务
    @Reference(timeout=5000)
    private  RemoteServiceI  remoteServiceI;

    @Autowired
    private TAccountMapper tAccountMapper;

    @GlobalTransactional(timeoutMills = 300000, name = "dubbo-gts-fescar-example")
    public ObjectResponse testTrans() {
        ObjectResponse objectResponse = new ObjectResponse();
        log.info("开启全局事务 xid:"+ RootContext.getXID());
        TAccount account = new TAccount();
        account.setUserId("12");
        account.setAmount(20.0);
        try {
            tAccountMapper.addAccount(account);
        }catch (Exception e){
            throw new DefaultException(ResponseCodeEnum.FAIL);
        }

        log.info("本地执行完毕");
        ObjectResponse remoteObjectResponse = remoteServiceI.saveStorage();
        if (remoteObjectResponse.getStatus()!= HttpStatus.OK.value()){
            throw new DefaultException(ResponseCodeEnum.FAIL);
        }
        objectResponse.setStatus(ResponseCodeEnum.SUCCESS.getCode());
        objectResponse.setMessage(ResponseCodeEnum.SUCCESS.getMessage());

        return  objectResponse;
    }

}
