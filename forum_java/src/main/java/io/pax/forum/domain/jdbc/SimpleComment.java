package io.pax.forum.domain.jdbc;

import io.pax.forum.domain.Comment;

/**
 * Created by AELION on 19/02/2018.
 */
public class SimpleComment implements Comment {
    int id;
    String content;

    public SimpleComment() {}

    public SimpleComment(int id, String content) {
        this.id = id;
        this.content = content;
    }


    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public String toString() {
        return this.content;
    }
}
