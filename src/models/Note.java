package models;

import dbUtil.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Note {
    final private String id;
    final private String notableID;
    final private String notableType;
    final private String title;
    final private String content;
    final private String createdAt;

    private DBConnection DBConn;

    public Note(String id, String notableID, String notableType, String title, String content, String createdAt) {
        this.id = id;
        this.notableID = notableID;
        this.notableType = notableType;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static Note find(String id) throws SQLException {
        String notableID;
        String notableType;
        String title;
        String content;
        String createdAt;

        Note note = null;

        String q = "SELECT * FROM Notes WHERE id=?";
        Connection conn = DBConnection.getConnection();

        PreparedStatement stmt = conn.prepareStatement(q);
        stmt.setInt(1, Integer.parseInt(id));

        ResultSet rs = conn.createStatement().executeQuery(q);

        if (rs.next()) {
            notableID = rs.getString(1);
            notableType = rs.getString(2);
            title = rs.getString(3);
            content = rs.getString(4);
            createdAt = rs.getString(5);
            note = new Note(id, title, notableID, notableType, content, createdAt);
        }

        return note;
    }

    protected static ArrayList<Note> find(String notableID, String notableType) throws SQLException {
        String id;
        String title;
        String content;
        String createdAt;

        ArrayList<Note> notes = new ArrayList<>();

        String q = "SELECT id, title, content, createdAt FROM Notes WHERE notableID=? AND notableType=?";
        Connection conn = DBConnection.getConnection();

        PreparedStatement stmt = conn.prepareStatement(q);
        stmt.setInt(1, Integer.parseInt(notableID));
        stmt.setString(2, notableType);

        ResultSet rs = conn.createStatement().executeQuery(q);

        while(rs.next()) {
            id = rs.getString(1);
            title = rs.getString(2);
            content = rs.getString(3);
            createdAt = rs.getString(4);
            notes.add(new Note(id, title, notableID, notableType, content, createdAt));
        }

        return notes;
    }
}
