package models;

import dbUtil.DBConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Note {
    final private StringProperty id;
    final private StringProperty notableID;
    final private StringProperty notableType;
    final private StringProperty title;
    final private StringProperty content;
    final private StringProperty createdAt;

    private DBConnection DBConn;

    public Note(String id, String notableID, String notableType, String title, String content, String createdAt) {
        this.id = new SimpleStringProperty(id);
        this.notableID = new SimpleStringProperty(notableID);
        this.notableType = new SimpleStringProperty(notableType);
        this.title = new SimpleStringProperty(title);
        this.content = new SimpleStringProperty(content);
        this.createdAt = new SimpleStringProperty(createdAt);
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
            note = new Note(id, notableID, notableType, title, content, createdAt);
        }

        conn.close();
        return note;
    }

    public static ArrayList<Note> find(String notableID, String notableType) throws SQLException {
        String id;
        String title;
        String content;
        String createdAt;

        ArrayList<Note> notes = new ArrayList<>();

        String q = "SELECT id, title, content, createdAt FROM Notes WHERE notableID=" + notableID + " AND notableType=\"" + notableType + "\"";
        Connection conn = DBConnection.getConnection();

        ResultSet rs = conn.createStatement().executeQuery(q);

        while(rs.next()) {
            id = rs.getString(1);
            title = rs.getString(2);
            content = rs.getString(3);
            createdAt = rs.getString(4);
            notes.add(new Note(id, notableID, notableType, title, content, createdAt));
        }
        conn.close();

        return notes;
    }

    public StringProperty idProperty() { return id; }
    public StringProperty notableIDProperty() { return notableID; }
    public StringProperty notableTypeProperty() { return notableType; }
    public StringProperty titleProperty() { return title; }
    public StringProperty contentProperty() { return content; }
    public StringProperty createdAtProperty() { return createdAt; }

    public void setId(String id) { this.id.set(id); }
    public void setNotableID(String notableID) { this.notableID.set(notableID); }
    public void setNotableType(String notableType) { this.notableType.set(notableType); }
    public void setTitle(String title) { this.title.set(title); }
    public void setContent(String content) { this.content.set(content); }
    public void setCreatedAt(String createdAt) { this.createdAt.set(createdAt); }
}
