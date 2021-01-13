package cn.kingcity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class FushuaiApplicationTests {

    @Test
    void contextLoads() {

        int g=3;
        Double a= Double.valueOf(g);
        int b=2;
        System.out.println((int)Math.ceil(a/b));

    }

}
