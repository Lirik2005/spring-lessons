package com.spring.database.repository;

import com.spring.database.pool.ConnectionPool;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    /**
     * Здесь аннотация @Qualifier("pool2") указывает какой именно ConnectionPool необходимо использовать. В данном случае используется
     * бин из ApplicationConfiguration.class
     */

    @Qualifier("pool2")
    private final ConnectionPool connectionPool;
}
