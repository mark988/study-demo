package com.example.deferredresult.v2;


import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 处理订单接口的任务，每个任务类处理一种接口
 *
 * @author
 */
@Component
public class OrderTask extends Thread {

    @Autowired
    private RequestQueue queue;

    private boolean running = true;

    @Override
    public void run() {
        while (running) {
            try {
                AsyncVo<String, Object> vo = queue.getOrderQueue().take();
                System.out.println("[ OrderTask ]开始处理订单 当前大小:"+queue.getOrderQueue().size());

                String params = vo.getParams();
                Thread.sleep(600);
                Map<String, Object> map = new HashMap<>();
                map.put("params", params+":ok");
                map.put("time", System.currentTimeMillis());

                vo.getResult().setResult(map);
                System.out.println("[ OrderTask ]订单处理完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
                running = false;
            }

        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

}