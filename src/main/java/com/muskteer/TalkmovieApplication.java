package com.muskteer;

import com.muskteer.tm.ai.ImportToMysql;
import com.muskteer.tm.common.util.ArticleFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class TalkmovieApplication {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext c = SpringApplication.run(TalkmovieApplication.class, args);
        c.getBean(ImportToMysql.class).insert();
    }
}
