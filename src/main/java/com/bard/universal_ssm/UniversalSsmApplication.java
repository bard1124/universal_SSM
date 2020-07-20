package com.bard.universal_ssm;

import com.bard.universal_ssm.framework.bean.ResponseBodyWrapFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//扫描dao包
@MapperScan("com.bard.universal_ssm.dao")
//开启定时任务支持
@EnableScheduling
//开启缓存支持
@EnableCaching
//开启事务支持
@EnableTransactionManagement
//读取事务的配置文件
@ImportResource("classpath:transaction.xml")
public class UniversalSsmApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversalSsmApplication.class, args);
    }
    @Bean
    public ResponseBodyWrapFactoryBean getResponseBodyWrap() {
        return new ResponseBodyWrapFactoryBean();
    }
}
