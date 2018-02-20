package io.pax.forum.domain.jdbc;

import io.pax.forum.domain.Topic;

/**
 * Created by AELION on 16/02/2018.
 */
public class SimpleTopic implements Topic{
    int id;
    String name;

    public SimpleTopic() {
    }

    public SimpleTopic(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
