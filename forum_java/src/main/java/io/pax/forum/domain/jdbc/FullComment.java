package io.pax.forum.domain.jdbc;

/**
 * Created by AELION on 19/02/2018.
 */
public class FullComment extends SimpleComment {
    SimpleUser user;
    SimpleTopic topic;

    public FullComment() {
    }

    public FullComment(int id, String content, SimpleUser user, SimpleTopic topic) {
        super(id, content);
        this.user = user;
        this.topic = topic;
    }

    public SimpleUser getUser() {
        return user;
    }

    public void setUser(SimpleUser user) {
        this.user = user;
    }

    public SimpleTopic getTopic() {
        return topic;
    }

    public void setTopic(SimpleTopic topic) {
        this.topic = topic;
    }
}
