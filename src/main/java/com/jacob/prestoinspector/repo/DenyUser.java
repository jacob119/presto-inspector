package com.jacob.prestoinspector.repo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DenyUser {

    @NotEmpty(message = "check your user field.")
    private String user;
}
