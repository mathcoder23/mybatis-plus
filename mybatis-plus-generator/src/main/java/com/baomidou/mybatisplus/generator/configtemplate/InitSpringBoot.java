package com.baomidou.mybatisplus.generator.configtemplate;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Order(1) // 通过order值的大小来决定启动的顺序
public class InitSpringBoot implements CommandLineRunner {

	@Resource
	SqlSessionFactory sqlSessionFactory;
	@Override
	public void run(String... args) throws Exception {
		sqlSessionFactory.getConfiguration().getTypeHandlerRegistry().setDefaultEnumTypeHandler(BaseEnumTypeHandler.class);
	}

}
