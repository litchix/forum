package io.pax.forum.domain.jdbc;

import io.pax.forum.domain.User;

/**
 * Created by AELION on 15/02/2018.
 */
public class SimpleUser implements User {
    int id;
    String name;

    public SimpleUser() {
    }

    public SimpleUser(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
