package org.example.database.repository;

import org.example.database.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final ConnectionPool connectionPool;

    /**
     * Здесь аннотация @Qualifier("pool2") указывает какой именно ConnectionPool необходимо использовать. В данном случае используется
     * бин из ApplicationConfiguration.class
     */
    public UserRepository(@Qualifier("pool2") ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
}
