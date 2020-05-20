package io.swagger.api.endpoints;

import io.swagger.articles.api.ArticlesApi;
import io.swagger.articles.api.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.entities.ArticleEntity;
import io.swagger.entities.CategoryEntity;
import io.swagger.entities.CommentEntity;
import io.swagger.entities.UserEntity;
import io.swagger.repositories.ArticleRepository;
import io.swagger.repositories.CategoryRepository;
import io.swagger.repositories.CommentRepository;
import io.swagger.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
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
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;

    private static final Logger log = LoggerFactory.getLogger(ArticlesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ArticlesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }


    // ARTICLES
    // CREATE

    public ResponseEntity<Void> createArticle(@ApiParam(value = "Article that needs to be added" ,required=true )  @Valid @RequestBody CreateArticle createArticle) {
        ArticleEntity articleEntity = new ArticleEntity();

        UserEntity author = userRepository.findById(createArticle.getAuthorId()).orElseThrow(() -> new EntityNotFoundException("This user does not exist"));
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

    //FIND

    public ResponseEntity<List<GetArticle>> findArticlesByCategories(@NotNull @ApiParam(value = "Categories to filter by", required = true) @Valid @RequestParam(value = "categories", required = true) List<Long> categories) {
        //trouver tous les articles qui ont une catégorie en commun avec l'array de catégories passée en param
        List<GetArticle> correspondingArticles = null;
        List<GetArticle> potentialArticles;

        for (Long category : categories){
            potentialArticles = findArticlesByCategory(category);
            for ( GetArticle article : potentialArticles){
                if ( !correspondingArticles.contains(article) ){
                    correspondingArticles.add(article);
                }
            }
        }

        return  ResponseEntity.ok(correspondingArticles);

    }

    public ResponseEntity<GetArticle> findArticleById(@ApiParam(value = "ID of the article to return",required=true) @PathVariable("articleId") Long articleId) {
        ArticleEntity articleEntity = articleRepository.findById(articleId).orElseThrow(() -> new EntityNotFoundException("This article does not exist"));;
        GetArticle article = toGetArticle(articleEntity);
        return ResponseEntity.ok(article);
    }


    public ResponseEntity<List<GetArticle>> findArticlesByDate() {
        List<GetArticle> articles = new ArrayList<>();

        for (ArticleEntity articleEntity : articleRepository.findAllByOrderByLastUpdateAt()) {
            articles.add(toGetArticle(articleEntity));
        }

        return ResponseEntity.ok(articles);

    }

    // UPDATE-DELETE

    public ResponseEntity<Void> updateArticleById(@ApiParam(value = "ID of article that needs to be updated",required=true) @PathVariable("articleId") Long articleId,@ApiParam(value = "updated article" ,required=true )  @Valid @RequestBody UpdateArticle updateArticle) {
        ArticleEntity articleEntity = articleRepository.findById(articleId).orElseThrow(() -> new EntityNotFoundException("This article does not exist"));

        articleEntity = updateArticle(updateArticle, articleEntity);
        //ici on n'update pas l'auteur puisque celui-ci ne peut pas changer, est-ce que c'est possible de le faire comme ça ?

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().build().toUri();

        articleRepository.save(articleEntity);

        return ResponseEntity.created(location).build();
    }


    public ResponseEntity<Void> deleteArticleById(@ApiParam(value = "Article id to delete",required=true) @PathVariable("articleId") Long articleId) {
        articleRepository.deleteById(articleId);
        return ResponseEntity.ok().build();
    }



    // COMMENTS

    public ResponseEntity<Void> addCommentToAnArticle(@ApiParam(value = "ID of the article",required=true) @PathVariable("articleId") Long articleId,@ApiParam(value = "comment to add to the article"  )  @Valid @RequestBody CreateComment createComment) {

        ArticleEntity article = articleRepository.findById(articleId).orElseThrow(() -> new EntityNotFoundException("This article does not exist"));

        UserEntity author = userRepository.findById(createComment.getAuthor()).orElseThrow(() -> new EntityNotFoundException("This user does not exist"));

        CommentEntity comment = fromCreatetoCommentEntity(createComment);
        comment.setAuthor(author);
        commentRepository.save(comment);

        List<CommentEntity> comments = article.getComments();
        comments.add(comment);
        article.setComments(comments);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().build().toUri();

        articleRepository.save(article);


        return ResponseEntity.created(location).build();

    }


    public ResponseEntity<Void> deleteComment(@ApiParam(value = "ID of the comment to delete",required=true) @PathVariable("commentId") Long commentId,@ApiParam(value = "Article ID",required=true) @PathVariable("articleId") Long articleId) {
        commentRepository.deleteById(commentId);
        return ResponseEntity.ok().build();

    }


    public ResponseEntity<GetComment> findCommentbyID(@ApiParam(value = "ID of the comment",required=true) @PathVariable("commentId") Long commentId,@ApiParam(value = "ID of the article",required=true) @PathVariable("articleId") Long articleId) {
        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException("This comment does not exist"));;
        GetComment comment = toGetComment(commentEntity);
        return ResponseEntity.ok(comment);

    }


    public ResponseEntity<List<GetComment>> findCommentsByArticle(@ApiParam(value = "Id of the article",required=true) @PathVariable("articleId") Long articleId) {
        ArticleEntity articleEntity = articleRepository.findById(articleId).orElseThrow(() -> new EntityNotFoundException("This article does not exist"));
        GetArticle article = toGetArticle(articleEntity);
        List<GetComment> comments = article.getComments();
        return ResponseEntity.ok(comments);
    }


    public ResponseEntity<Void> updateCommentbyId(@ApiParam(value = "ID of the comment that needs to be updated",required=true) @PathVariable("commentId") Long commentId,@ApiParam(value = "ID of the article",required=true) @PathVariable("articleId") Long articleId,@ApiParam(value = "updated comment"  )  @Valid @RequestBody CreateComment createComment) {
        CommentEntity commentEntitytoUpdate = commentRepository.findById(commentId).get();

        commentEntitytoUpdate.setTitle(createComment.getTitle());
        commentEntitytoUpdate.setContent(createComment.getContent());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().build().toUri();

        commentRepository.save(commentEntitytoUpdate);

        return ResponseEntity.created(location).build();

    }

    // fonctions pour passer de model à entity et inversément

    private ArticleEntity fromCreateArticleToArticleEntity(CreateArticle article) {
        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(article.getTitle());
        entity.setPhotoUrls(article.getPhotoUrls());
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

    private ArticleEntity updateArticle(UpdateArticle article, ArticleEntity entity) {
        entity.setViews(article.getViews());
        entity.setTitle(article.getTitle());
        entity.setPhotoUrls(article.getPhotoUrls());
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

    private GetComment toGetComment(CommentEntity entity) {
        GetComment comment = new GetComment();
        comment.setContent(entity.getContent());
        comment.setTitle(entity.getTitle());
        comment.setAuthor(toGetUser(entity.getAuthor()));
        return comment ;
    }

    private CommentEntity fromCreatetoCommentEntity( CreateComment comment){
        CommentEntity entity = new CommentEntity();
        entity.setContent(comment.getContent());
        entity.setTitle(comment.getTitle());
        return entity;
    }

    private GetCategory toGetCategory (CategoryEntity entity) {
        GetCategory category = new GetCategory();
        category.setId(entity.getId());
        category.setName(entity.getName());
        return category ;
    }

    private CategoryEntity fromGetCategoryToCategoryEntity( GetCategory category){
        CategoryEntity entity = new CategoryEntity();
        entity.setName(category.getName());

        return entity;
    }

    private List<GetArticle> findArticlesByCategory(Long categoryId) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("This category does not exist"));
        List<GetArticle> articlesToReturn = null;

        for (ArticleEntity articleEntity : articleRepository.findAll()) {

            GetArticle article = toGetArticle(articleEntity);
            List<CategoryEntity> categoriesOfTheActualArticle = articleEntity.getCategories();

            if ( categoriesOfTheActualArticle.contains(categoryEntity) ) {

                articlesToReturn.add(article);
            }
        }

        return articlesToReturn;
    }

}
