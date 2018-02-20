package io.pax.forum.domain.jdbc;

/**
 * Created by AELION on 16/02/2018.
 */
public class FullTopic extends SimpleTopic {
    SimpleUser user;


    public FullTopic() {
    }

    public FullTopic(SimpleUser user) {
        this.user = user;
    }

    public FullTopic(int id, String name, SimpleUser user) {
        super(id, name);
        this.user = user;
    }

    public SimpleUser getUser() {
        return user;
    }

    public void setUser(SimpleUser user) {
        this.user = user;
    }
}
