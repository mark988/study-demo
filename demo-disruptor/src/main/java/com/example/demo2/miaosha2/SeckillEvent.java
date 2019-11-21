package com.example.demo2.miaosha2;

import java.io.Serializable;

/**
 * 事件对象（秒杀事件）
 * 创建者
 */
public class SeckillEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	private long seckillId;
	private long userId;
	
	public SeckillEvent(){
		
	}

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
}