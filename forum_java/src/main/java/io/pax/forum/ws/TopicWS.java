package io.pax.forum.ws;

import io.pax.forum.dao.TopicDao;
import io.pax.forum.domain.Topic;
import io.pax.forum.domain.User;
import io.pax.forum.domain.jdbc.FullTopic;
import io.pax.forum.domain.jdbc.SimpleUser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by AELION on 16/02/2018.
 */
@Path("topics")//chemin relatif pour avoir "/cryptos/api/users"
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TopicWS {

    @GET
    public List<Topic> getTopics() throws SQLException {
        TopicDao dao = new TopicDao();
        return dao.listTopic();
    }

    @POST
    //return future topic with an id
    public Topic createTopic(FullTopic topic) { /*sent topic, has no id*/

        User user = topic.getUser();
        if (user == null) {
            //400x: navigator sent wrong informations
            throw new NotAcceptableException("No user id sent");

        }
        if (topic.getName().length() < 2) {
            throw new NotAcceptableException("406: Wallet name must have at least 2 letters");
        }

        try {
            int id = new TopicDao().createTopic(user.getId(), topic.getName());

            User boundUser = topic.getUser();
            SimpleUser simpleUser = new SimpleUser(boundUser.getId(), boundUser.getName());
            return new FullTopic(id, topic.getName(), simpleUser);

        } catch (SQLException e) {
            throw new ServerErrorException("Database error, sorry", 500);
        }

    }

}
