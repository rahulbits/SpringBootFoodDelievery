package org.example.YTVideo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.persistence.Entity;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableJpaRepositories
@Configuration
public class YTVideo {
    public static void main(String[] args) {
        SpringApplication.run(YTVideo.class);
    }
}