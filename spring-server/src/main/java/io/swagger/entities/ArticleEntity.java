package io.swagger.entities;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ArticleEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    @Lob
    private String content;
    @OneToOne
    private UserEntity author;
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<CommentEntity> comments = new ArrayList<>();
    @ManyToMany
    private List<CategoryEntity> categories = new ArrayList<>() ;

    private Integer views;

    private DateTime createdAt;
    private DateTime lastUpdateAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new DateTime();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdateAt = new DateTime();
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public DateTime getLastUpdateAt() {
        return lastUpdateAt;
    }

    public void setLastUpdateAt(DateTime lastUpdatedAt) {
        this.lastUpdateAt = lastUpdatedAt;
    }

    public long getId() {
        return id ;
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

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }

    public void addComment ( CommentEntity comment){
        this.comments.add(comment);
    }

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }

    public void addCategory ( CategoryEntity category) {
        this.categories.add(category);
    }

}
