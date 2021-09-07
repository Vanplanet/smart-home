package com.saferide.serve;

import com.saferide.serve.event.EventHandle;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@MapperScan("com.saferide.serve.mapper")
@SpringBootApplication
public class ServeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServeApplication.class, args);
    }

}
