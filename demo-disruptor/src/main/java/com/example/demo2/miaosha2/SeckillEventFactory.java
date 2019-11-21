package com.example.demo2.miaosha2;

import com.lmax.disruptor.EventFactory;

/**
 * 事件生成工厂（用来初始化预分配事件对象）
 * 创建者
 */
public class SeckillEventFactory implements EventFactory<SeckillEvent> {

	@Override
	public SeckillEvent newInstance() {
		return new SeckillEvent();
	}
}
