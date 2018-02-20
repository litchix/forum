package io.pax.forum.dao;

import io.pax.forum.domain.Topic;
import io.pax.forum.domain.User;
import io.pax.forum.domain.jdbc.FullUser;
import io.pax.forum.domain.jdbc.SimpleTopic;
import io.pax.forum.domain.jdbc.SimpleUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 15/02/2018.
 */
public class UserDao {

    JdbcConnector connector = new JdbcConnector();

    public List<User> listUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        Connection conn = this.connector.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM user");


        while (rs.next()) {
            String name = rs.getString("nom");
            int id = rs.getInt("id");

            users.add(new SimpleUser(id, name));
        }

        rs.close();
        stmt.close();
        conn.close();

        return users;
    }

    public int createUser(String name) throws SQLException {
        String query = "INSERT INTO user (nom) VALUES (?)";
        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, name);

        statement.executeUpdate();

        ResultSet keys = statement.getGeneratedKeys();
        keys.next();

        int id = keys.getInt(1);

        statement.close();
        conn.close();

        return id;
    }

    public User findUserWithTopics(int userId) throws SQLException {

        Connection connection = connector.getConnection();
        String query = "SELECT * FROM topic t JOIN user u ON t.user_id = u.id WHERE u.id like ?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userId);
        ResultSet set = statement.executeQuery();

        User user = null;
        List<Topic> topics = new ArrayList<>();

        while (set.next()) {
            String userName = set.getString("u.nom");
            System.out.println("userName : " + userName);
            user = new FullUser(userId, userName, topics);

            int topicId = set.getInt("t.id");
            String  topicName = set.getString("t.name");

            if (topicId > 0) {
                Topic topic = new SimpleTopic(topicId, topicName);
                topics.add(topic);
            }

        }

        statement.close();
        connection.close();

        return user;
    }



    public static void main(String[] args) throws SQLException {

        UserDao dao = new UserDao();
        System.out.println(dao.listUsers());
         dao.createUser("juju");
    }
}
