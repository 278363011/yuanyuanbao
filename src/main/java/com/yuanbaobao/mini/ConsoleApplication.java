package com.codebaobao;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Slf4j
@EnableWebMvc
@EnableAsync
@EnableTransactionManagement
@EnableScheduling
@ServletComponentScan
@MapperScan("com.codebaobao.mapper")
@SpringBootApplication(scanBasePackages = {"com.codebaobao"}, exclude = {})
public class ConsoleApplication {

    public static void main(final String[] args) {
        final SpringApplication app = new SpringApplication(ConsoleApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        log.info("================================心里预约系统启动完成================================");
    }

}
