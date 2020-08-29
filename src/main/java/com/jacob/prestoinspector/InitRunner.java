package com.jacob.prestoinspector;

import com.jacob.prestoinspector.repo.DenyUsers;
import com.jacob.prestoinspector.repo.QueryInfo;
import com.jacob.prestoinspector.repo.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class InitRunner implements CommandLineRunner {

    @Autowired
    private QueryRepository queryRepository;

    @Autowired
    private DenyUsers denyUsers;

    @Override
    public void run(String... args) throws Exception {
        denyUsers.readToJson();
        queryRepository.connectionTest();
        System.out.println("Starting Runner..");
    }

    @Scheduled(fixedDelayString = "${schedule.interval}")
    public void scheduleFixedDelayTask() {
        System.out.println(
                "watching presto query... - " + System.currentTimeMillis() / 1000);

        for (QueryInfo queryInfo : queryRepository.getQueryInfo()) {
            if (denyUsers.getUsers().stream().anyMatch(s -> s.getUser().equalsIgnoreCase(queryInfo.getUser()))) {
                queryRepository.killQuery(queryInfo.getQueryId());
            }
        }
    }
}
