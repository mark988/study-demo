package com.example.demo2.miaosha2;

import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 消费者(秒杀处理器)
 * 创建者
 */
@Slf4j
public class SeckillEventConsumer implements EventHandler<SeckillEvent> {
	
	private ISeckillService seckillService = (ISeckillService) SpringUtil.getBean("seckillService");

	@Override
	public void onEvent(SeckillEvent seckillEvent, long seq, boolean bool) throws Exception {

		log.info("userid ：{} ",seckillEvent.getUserId());
		seckillService.test();
	}
}
