package com.jacob.prestoinspector.repo;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Repository
public class DenyUsers {

    private final String CONF = "denyUsers.json";
    public List<DenyUser> denyUsers;
    public DenyUsers(){
        denyUsers = new CopyOnWriteArrayList<>();
    }

    public List<DenyUser> getUsers(){
        return denyUsers;
    }

    public void add(DenyUser user){
        if(this.denyUsers.stream().noneMatch(s -> s.getUser().equalsIgnoreCase(user.getUser())))
            this.denyUsers.add(user);
    }

    public void writeToJson() throws IOException {
        String json = JSON.toJSONString(this.getUsers());
        Files.write(Paths.get(CONF), json.getBytes());
    }

    public void readToJson() throws IOException {
        readToJson(CONF);
    }

    public void readToJson(String fileName) throws IOException {
        if(!Files.exists(Paths.get(CONF))){
            Files.createFile(Paths.get(CONF));
        }
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            String content = lines.collect(Collectors.joining());
            if(!content.equals(""))
                this.denyUsers = JSON.parseArray(content, DenyUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
