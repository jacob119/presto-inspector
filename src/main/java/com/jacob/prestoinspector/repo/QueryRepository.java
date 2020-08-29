package com.jacob.prestoinspector.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class QueryRepository  {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void connectionTest(){
        jdbcTemplate.execute("select 1");
    }

    public List<QueryInfo> getQueryInfo(){
        return  jdbcTemplate.query("select * from system.runtime.queries where state = 'RUNNING'", (resultSet, i)
                -> new QueryInfo(resultSet.getString("user"),
                resultSet.getString("query_id"),
                resultSet.getString("state"),
                resultSet.getString("query")));
    }


    public void killQuery(String queryId){

        /*
         testing query
         select year, count(*) from airline_delay_buckets group by year
         union
         select year, count(*) from airline_delay_buckets group by year
         union
         select year, count(*) from airline_delay_buckets group by year
         union
         select year, count(*) from airline_delay_buckets group by year;
         */
        System.out.println("kill query - " + queryId );
        String callProcedure = "CALL system.runtime.kill_query(query_id => '" + queryId + "' , message => 'Please, check your privilege from administrator. ')";
        jdbcTemplate.execute(callProcedure);
    }
}
