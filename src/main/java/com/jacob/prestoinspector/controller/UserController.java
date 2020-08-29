package com.jacob.prestoinspector.controller;

import com.jacob.prestoinspector.repo.DenyUser;
import com.jacob.prestoinspector.repo.DenyUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private DenyUsers denyUsers;

    /*
       curl -X GET http://localhost:8080/users
     */
    @RequestMapping("/users")
    public ResponseEntity getUsers(){
        return ResponseEntity.ok(denyUsers.getUsers());
    }

    /*
       curl -d '{"user":"jacob"}' -H "Content-Type:application/json" -X POST  http://localhost:8080/user
    */

    @PostMapping("/user")
    public ResponseEntity saveUser(@Valid  @RequestBody DenyUser denyUser) throws IOException {
        denyUsers.add(denyUser);
        denyUsers.writeToJson();
        denyUsers.readToJson("denyUsers.json");
        return ResponseEntity.ok(denyUser);
    }
}
