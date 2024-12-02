package models;

import java.sql.Timestamp;

public class Post {
    private int postId;
    private int userId;
    private String content;
    private Timestamp created;

    public Post(int postId, int userId, String content, Timestamp created) {
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.created = created;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", created=" + created +
                '}';
    }
}
