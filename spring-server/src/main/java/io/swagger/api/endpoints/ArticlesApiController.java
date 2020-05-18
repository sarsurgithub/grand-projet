package io.swagger.api.endpoints;

import io.swagger.articles.api.ApiUtil;
import io.swagger.articles.api.ArticlesApi;
import io.swagger.articles.api.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.entities.ArticleEntity;
import io.swagger.entities.CommentEntity;
import io.swagger.entities.UserEntity;
import io.swagger.repositories.ArticleRepository;
import io.swagger.repositories.CommentRepository;
import io.swagger.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-08T14:21:38.963Z[GMT]")
@Controller
public class ArticlesApiController implements ArticlesApi {
    @Autowired
    ArticleRepository articleRepository;
    CommentRepository commentRepository;
    UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(ArticlesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ArticlesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }


    public ResponseEntity<List<GetArticle>> findArticlesByCategories(@NotNull @ApiParam(value = "Categories to filter by", required = true) @Valid @RequestParam(value = "category", required = true) List<Category> category) {

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    public ResponseEntity<Void> updateArticleById(@ApiParam(value = "ID of article that needs to be updated",required=true) @PathVariable("articleId") Long articleId,@ApiParam(value = "updated article" ,required=true )  @Valid @RequestBody UpdateArticle updateArticle) {
        ArticleEntity articleEntity = articleRepository.findById(articleId).get();

        articleEntity = fromUpdateArticleToArticleEntity(updateArticle);
        //ici on n'update pas l'auteur puisque celui-ci ne peut pas changer, est-ce que c'est possible de le faire comme ça ?

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().build().toUri();

        articleRepository.save(articleEntity);

        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Void> createArticle(@ApiParam(value = "Article that needs to be added" ,required=true )  @Valid @RequestBody CreateArticle createArticle) {
        ArticleEntity articleEntity = new ArticleEntity();

        UserEntity author = userRepository.findById(createArticle.getAuthorId()).get();
        // y'a sûrement un moyen beaucoup plus propre de faire ça but it will do for now
        //j'ai trouvé ça c'est mieux :
        articleEntity = fromCreateArticleToArticleEntity(createArticle);
        articleEntity.setAuthor(author);

        articleRepository.save(articleEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(articleEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Void> deleteArticleById(@ApiParam(value = "Article id to delete",required=true) @PathVariable("articleId") Long articleId) {
        articleRepository.deleteById(articleId);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> deleteComment(@ApiParam(value = "ID of the comment to delete",required=true) @PathVariable("commentId") Long commentId,@ApiParam(value = "Article ID",required=true) @PathVariable("articleId") Long articleId) {
        commentRepository.deleteById(commentId);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> addCommentToAnArticle(@ApiParam(value = "",required=true) @PathVariable("commentId") Long commentId,@ApiParam(value = "ID of the article",required=true) @PathVariable("articleId") Long articleId,@ApiParam(value = "comment to add to the article"  )  @Valid @RequestBody Comment comment) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    public ResponseEntity<GetArticle> findArticleById(@ApiParam(value = "ID of the article to return",required=true) @PathVariable("articleId") Long articleId) {
        ArticleEntity articleEntity = articleRepository.findById(articleId).get();
        GetArticle article = toGetArticle(articleEntity);
        return ResponseEntity.ok(article);
    }


    public ResponseEntity<List<GetArticle>> findArticlesByDate() {
        List<GetArticle> articles = new ArrayList<>();

        for (ArticleEntity articleEntity : articleRepository.findAllOrOrderByLastUpdateAt()) {
            articles.add(toGetArticle(articleEntity));
        }

        return ResponseEntity.ok(articles);

    }


    public  ResponseEntity<Comment> findCommentbyID(@ApiParam(value = "ID of the comment",required=true) @PathVariable("commentId") Long commentId,@ApiParam(value = "ID of the article",required=true) @PathVariable("articleId") Long articleId) {
        CommentEntity commentEntity = commentRepository.findById(commentId).get();
        Comment comment = toComment(commentEntity);
        return ResponseEntity.ok(comment);

    }

    public ResponseEntity<List<Comment>> findCommentsByArticle(@ApiParam(value = "Id of the article",required=true) @PathVariable("articleId") Long articleId) {
        ArticleEntity articleEntity = articleRepository.findById(articleId).get();
        GetArticle article = toGetArticle(articleEntity);
        List<Comment> comments = article.getComments();
        return ResponseEntity.ok(comments);

    }


    public ResponseEntity<Void> updateCommentbyId(@ApiParam(value = "ID of the comment that needs to be updated",required=true) @PathVariable("commentId") Long commentId,@ApiParam(value = "ID of the article",required=true) @PathVariable("articleId") Long articleId,@ApiParam(value = "updated comment"  )  @Valid @RequestBody Comment comment) {
        CommentEntity commentEntitytoUpdate = commentRepository.findById(commentId).get();

        commentEntitytoUpdate.setTitle(comment.getTitle());
        commentEntitytoUpdate.setContent(comment.getContent());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().build().toUri();

        commentRepository.save(commentEntitytoUpdate);

        return ResponseEntity.created(location).build();

    }


    private ArticleEntity fromCreateArticleToArticleEntity(CreateArticle article) {
        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(article.getTitle());
        entity.setPhotoUrls(article.getPhotoUrls());
        entity.setCreatedAt(article.getCreatedAt());
        entity.setContent(article.getContent());
        return entity;
    }

    private ArticleEntity fromGetArticleToArticleEntity(GetArticle article) {
        ArticleEntity entity = new ArticleEntity();
        entity.setViews(article.getViews());
        entity.setTitle(article.getTitle());
        entity.setPhotoUrls(article.getPhotoUrls());
        entity.setLastUpdateAt(article.getLastUpdateAt());
        entity.setCreatedAt(article.getCreatedAt());
        entity.setContent(article.getContent());
        entity.setAuthor(fromGetUserToUserEntity(article.getAuthor()));
        return entity;
    }

    private ArticleEntity fromUpdateArticleToArticleEntity(UpdateArticle article) {
        ArticleEntity entity = new ArticleEntity();
        entity.setViews(article.getViews());
        entity.setTitle(article.getTitle());
        entity.setPhotoUrls(article.getPhotoUrls());
        entity.setLastUpdateAt(article.getLastUpdateAt());
        entity.setCreatedAt(article.getCreatedAt());
        entity.setContent(article.getContent());
        return entity;
    }



    private GetArticle toGetArticle(ArticleEntity entity) {
        GetArticle article = new GetArticle();
        article.setId(entity.getId());
        article.setViews(entity.getViews());
        article.setTitle(entity.getTitle());
        article.setPhotoUrls(entity.getPhotoUrls());
        article.setLastUpdateAt(entity.getLastUpdateAt());
        article.setCreatedAt(entity.getCreatedAt());
        article.setContent(entity.getContent());
        article.setAuthor(toGetUser(entity.getAuthor()));
        return article;
    }

    private GetUser toGetUser(UserEntity entity){
        GetUser user = new GetUser();
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setLastName(entity.getLastName());
        user.setFirstName(entity.getFirstName());
        user.setEmail(entity.getEmail());
        return user;
    }

    private UserEntity fromCreateUsertoUserEntity(CreateUser user){
        UserEntity entity = new UserEntity();
        entity.setPassword(user.getPassword());
        entity.setUsername(user.getUsername());
        entity.setLastName(user.getLastName());
        entity.setFirstName(user.getFirstName());
        entity.setEmail(user.getEmail());
        return entity;
    }

    private UserEntity fromGetUserToUserEntity(GetUser user){
        UserEntity entity = new UserEntity();
        entity.setUsername(user.getUsername());
        entity.setLastName(user.getLastName());
        entity.setFirstName(user.getFirstName());
        entity.setEmail(user.getEmail());
        return entity;
    }

    private Comment toComment(CommentEntity entity) {
        Comment comment = new Comment();
        comment.setContent(entity.getContent());
        comment.setTitle(entity.getTitle());
        comment.setAuthorId(entity.getAuthorId());
        return comment ;
    }

    private CommentEntity toCommentEntity( Comment comment){
        CommentEntity entity = new CommentEntity();
        entity.setAuthorId(comment.getAuthorId());
        entity.setContent(comment.getContent());
        entity.setTitle(comment.getTitle());
        return entity;
    }

}
