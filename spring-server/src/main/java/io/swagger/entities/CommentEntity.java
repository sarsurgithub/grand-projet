package io.swagger.entities;


import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class CommentEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String content;
    @ManyToOne
    private UserEntity author;

    private DateTime createdAt;
    @PrePersist
    protected void onCreate() {
        createdAt = new DateTime();
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
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

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity user) {
        this.author = user;
    }

}
