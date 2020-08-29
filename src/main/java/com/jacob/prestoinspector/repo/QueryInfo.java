package com.jacob.prestoinspector.repo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QueryInfo {
    private String user;
    private String queryId;
    private String state;
    private String queryText;
}
