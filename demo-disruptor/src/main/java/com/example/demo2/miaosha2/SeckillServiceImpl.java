package com.example.demo2.miaosha2;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("seckillService")
public class SeckillServiceImpl implements ISeckillService {


	@Override
	public void test() {
		log.info(" test is success...");
	}
}
