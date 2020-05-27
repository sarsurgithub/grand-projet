package io.swagger.services;

import io.swagger.articles.api.model.*;
import io.swagger.entities.ArticleEntity;
import io.swagger.entities.CategoryEntity;
import io.swagger.entities.CommentEntity;
import io.swagger.entities.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class utils {

    public static ArticleEntity fromCreateArticleToArticleEntity(CreateArticle article) {
        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(article.getTitle());
        entity.setPhotoUrls(article.getPhotoUrls());
        entity.setContent(article.getContent());
        return entity;
    }

    public static ArticleEntity updateArticle(UpdateArticle article, ArticleEntity entity) {
        entity.setViews(article.getViews());
        entity.setTitle(article.getTitle());
        entity.setPhotoUrls(article.getPhotoUrls());
        entity.setContent(article.getContent());
        return entity;
    }

    public static GetArticle toGetArticle(ArticleEntity entity) {
        GetArticle article = new GetArticle();
        article.setId(entity.getId());
        article.setViews(entity.getViews());
        article.setTitle(entity.getTitle());
        article.setPhotoUrls(entity.getPhotoUrls());
        article.setLastUpdateAt(entity.getLastUpdateAt());
        article.setCreatedAt(entity.getCreatedAt());
        article.setContent(entity.getContent());
        article.setAuthor(toGetUser(entity.getAuthor()));

        List<CategoryEntity> categories = entity.getCategories();
        List<GetCategory> categoriesAsGets = new ArrayList<>();
        for ( CategoryEntity category : categories){
            GetCategory categoryAsAGet = toGetCategory(category);
            categoriesAsGets.add(categoryAsAGet);
        }
        article.setCategories(categoriesAsGets);

        List<CommentEntity> comments = entity.getComments();
        List<GetComment> commentsAsGet = new ArrayList<>();
        for ( CommentEntity comment : comments){
            GetComment commentAsGet = toGetComment(comment);
            commentsAsGet.add(commentAsGet);
        }
        article.setComments(commentsAsGet);

        return article;
    }

    public static GetUser toGetUser(UserEntity entity){
        GetUser user = new GetUser();
        if (entity != null) {
            user.setId(entity.getId());
            user.setUsername(entity.getUsername());
            user.setLastName(entity.getLastName());
            user.setFirstName(entity.getFirstName());
            user.setEmail(entity.getEmail());

        } else {
            user = null;
        }

        return user;

    }

    public static UserEntity fromCreateUsertoUserEntity(CreateUser user){
        UserEntity entity = new UserEntity();
        entity.setPassword(user.getPassword());
        entity.setUsername(user.getUsername());
        entity.setLastName(user.getLastName());
        entity.setFirstName(user.getFirstName());
        entity.setEmail(user.getEmail());
        return entity;
    }

    public static UserEntity fromGetUserToUserEntity(GetUser user){
        UserEntity entity = new UserEntity();
        entity.setUsername(user.getUsername());
        entity.setLastName(user.getLastName());
        entity.setFirstName(user.getFirstName());
        entity.setEmail(user.getEmail());
        return entity;
    }

    public static GetComment toGetComment(CommentEntity entity) {
        GetComment comment = new GetComment();
        comment.setId(entity.getId());
        comment.setContent(entity.getContent());
        comment.setTitle(entity.getTitle());
        comment.setAuthor(toGetUser(entity.getAuthor()));
        return comment ;
    }

    public static CommentEntity fromCreatetoCommentEntity( CreateComment comment){
        CommentEntity entity = new CommentEntity();
        entity.setContent(comment.getContent());
        entity.setTitle(comment.getTitle());
        return entity;
    }

    public static GetCategory toGetCategory (CategoryEntity entity) {
        GetCategory category = new GetCategory();
        category.setId(entity.getId());
        category.setName(entity.getName());
        return category ;
    }

    public static CategoryEntity fromCreateCategoryToCategoryEntity( CreateCategory category){
        CategoryEntity entity = new CategoryEntity();
        entity.setName(category.getName());

        return entity;
    }

    public static CategoryEntity updateCategory(CreateCategory category, CategoryEntity entity) {
        entity.setName(category.getName());
        return entity;
    }

    public static UserEntity updateUser(UpdateUser user, UserEntity entity){
        entity.setUsername(user.getUsername());
        entity.setLastName(user.getLastName());
        entity.setFirstName(user.getFirstName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        return entity;
    }

}
