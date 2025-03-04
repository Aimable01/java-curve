package org.aimable.nestfitmvc.model;

public class Post {
    private int id;
    private String title;
    private String content;
    private String author;
    private String date;
    private int userId;

    public Post(int id, String title, String content, String author, String date, int userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = date;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                ", userId=" + userId +
                '}';
    }
}
