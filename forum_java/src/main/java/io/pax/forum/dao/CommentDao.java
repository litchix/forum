package io.pax.forum.dao;

import io.pax.forum.domain.Comment;
import io.pax.forum.domain.jdbc.SimpleComment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 19/02/2018.
 */
public class CommentDao {

    JdbcConnector connector = new JdbcConnector();

    public List<Comment> listComment() throws SQLException {
        List<Comment> comments = new ArrayList<>();
        Connection conn = this.connector.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM comment");


        while (rs.next()) {
            String content = rs.getString("content");
            int id = rs.getInt("id");

            comments.add(new SimpleComment(id, content));
        }

        rs.close();
        stmt.close();
        conn.close();

        return comments;
    }

    public int createComment(int userId, int topicId, String content) throws SQLException {
        String query = "INSERT INTO comment (content, user_id, topic_id) VALUES (?, ?, ?)";
        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, content);
        statement.setInt(2, userId);
        statement.setInt(3, topicId);

        statement.executeUpdate();

        ResultSet keys = statement.getGeneratedKeys();
        keys.next();

        int id = keys.getInt(1);

        statement.close();
        conn.close();

        return id;
    }


    public static void main(String[] args) throws SQLException {

        CommentDao dao = new CommentDao();
        //System.out.println(dao.listComment());
        //dao.createComment(11,2, "blablabala");


    }
}
