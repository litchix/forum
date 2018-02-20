package io.pax.forum.dao;

import io.pax.forum.domain.Topic;
import io.pax.forum.domain.jdbc.SimpleTopic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 16/02/2018.
 */
public class TopicDao {

    JdbcConnector connector = new JdbcConnector();

    public List<Topic> listTopic() throws SQLException {
        List<Topic> users = new ArrayList<>();
        Connection conn = this.connector.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM topic");


        while (rs.next()) {
            String name = rs.getString("name");
            int id = rs.getInt("id");

            users.add(new SimpleTopic(id, name));
        }

        rs.close();
        stmt.close();
        conn.close();

        return users;
    }

    public int createTopic(int userId, String name) throws SQLException {
        String query = "INSERT INTO topic (name, user_id) VALUES (?, ?)";
        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, name);
        statement.setInt(2, userId);

        statement.executeUpdate();

        ResultSet keys = statement.getGeneratedKeys();
        keys.next();

        int id = keys.getInt(1);

        statement.close();
        conn.close();

        return id;
    }


    public static void main(String[] args) throws SQLException {

        TopicDao dao = new TopicDao();
        System.out.println(dao.listTopic());
        dao.createTopic(2,"spider man");
    }
}
