package com.jacob.prestoinspector;


import com.jacob.prestoinspector.repo.QueryInfo;
import com.jacob.prestoinspector.repo.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@SpringBootApplication
@EnableScheduling
public class PrestoInspectorApplication {
    public static void main(String[] args) {
        SpringApplication.run(PrestoInspectorApplication.class, args);
    }
}


