package cn.kingcity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//自动扫描包注解
@MapperScan("cn.kingcity.main.mapper")
@MapperScan("cn.kingcity.order.mapper")
public class FushuaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FushuaiApplication.class, args);
    }

}
