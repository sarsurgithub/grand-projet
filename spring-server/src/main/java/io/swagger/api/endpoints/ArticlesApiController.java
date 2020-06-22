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

import io.swagger.services.utils;
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
import java.util.Iterator;
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

        articleEntity = utils.fromCreateArticleToArticleEntity(createArticle);
        articleEntity.setAuthor(author);

        for (Long categoryid : createArticle.getCategoriesIds()) {
            CategoryEntity category = categoryRepository.findById(categoryid).orElseThrow(() -> new EntityNotFoundException("This category does not exist"));
            System.out.println(category);
            articleEntity.addCategory(category);
        }

        articleRepository.save(articleEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(articleEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    //FIND

    public ResponseEntity<List<GetArticle>> findArticlesByCategories(@NotNull @ApiParam(value = "Categories to filter by", required = true) @Valid @RequestParam(value = "categories", required = true) List<Long> categories) {
        //trouver tous les articles qui ont une catégorie en commun avec l'array de catégories passée en param
        List<GetArticle> correspondingArticles = new ArrayList<>();
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
        GetArticle article = utils.toGetArticle(articleEntity);


        return ResponseEntity.ok(article);
    }


    public ResponseEntity<List<GetArticle>> findArticlesByDate() {
        List<GetArticle> articles = new ArrayList<>();

        for (ArticleEntity articleEntity : articleRepository.findAllByOrderByLastUpdateAt()) {
            articles.add(utils.toGetArticle(articleEntity));
        }

        return ResponseEntity.ok(articles);

    }

    // UPDATE-DELETE

    public ResponseEntity<Void> updateArticleById(@ApiParam(value = "ID of article that needs to be updated",required=true) @PathVariable("articleId") Long articleId,@ApiParam(value = "updated article" ,required=true )  @Valid @RequestBody UpdateArticle updateArticle) {
        ArticleEntity articleEntity = articleRepository.findById(articleId).orElseThrow(() -> new EntityNotFoundException("This article does not exist"));

        Iterable<CategoryEntity> categoriesIte = categoryRepository.findAllById(updateArticle.getCategoriesIds());
        List<CategoryEntity> categories = null ;

        for(CategoryEntity category : categoriesIte) {
            categories.add(category);
        }

        articleEntity = utils.updateArticle(updateArticle, articleEntity);
        articleEntity.setCategories(categories);


        //ici on n'update pas l'auteur puisque celui-ci ne peut pas changer, est-ce que c'est possible de le faire comme ça ?

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(articleEntity.getId()).toUri();

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

        CommentEntity comment = utils.fromCreatetoCommentEntity(createComment);
        comment.setAuthor(author);
        commentRepository.save(comment);

        article.addComment(comment);


        articleRepository.save(article);


        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(comment.getId()).toUri();



        return ResponseEntity.created(location).build();

    }


    public ResponseEntity<Void> deleteComment(@ApiParam(value = "ID of the comment to delete",required=true) @PathVariable("commentId") Long commentId,@ApiParam(value = "Article ID",required=true) @PathVariable("articleId") Long articleId) {
        ArticleEntity article = articleRepository.findById(articleId).orElseThrow(() -> new EntityNotFoundException("This article does not exist"));
        List<CommentEntity> comments = article.getComments();

        for (Iterator<CommentEntity> it = comments.iterator(); it.hasNext();) {
            CommentEntity comment = it.next();
            if (comment.getId() == commentId){
                it.remove();
            }
        }

        article.setComments(comments);
        articleRepository.save(article);

        commentRepository.deleteById(commentId);
        return ResponseEntity.ok().build();

    }


    public ResponseEntity<GetComment> findCommentbyID(@ApiParam(value = "ID of the comment",required=true) @PathVariable("commentId") Long commentId,@ApiParam(value = "ID of the article",required=true) @PathVariable("articleId") Long articleId) {
        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException("This comment does not exist"));;
        GetComment comment = utils.toGetComment(commentEntity);
        return ResponseEntity.ok(comment);

    }


    public ResponseEntity<List<GetComment>> findCommentsByArticle(@ApiParam(value = "Id of the article",required=true) @PathVariable("articleId") Long articleId) {
        ArticleEntity articleEntity = articleRepository.findById(articleId).orElseThrow(() -> new EntityNotFoundException("This article does not exist"));
        GetArticle article = utils.toGetArticle(articleEntity);
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

    // fonctions
    public List<GetArticle> findArticlesByCategory(Long categoryId) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("This category does not exist"));
        List<GetArticle> articlesToReturn = new ArrayList<>();

        for (ArticleEntity articleEntity : articleRepository.findAll()) {

            GetArticle article = utils.toGetArticle(articleEntity);
            List<CategoryEntity> categoriesOfTheActualArticle = articleEntity.getCategories();

            if ( categoriesOfTheActualArticle.contains(categoryEntity) ) {

                articlesToReturn.add(article);
            }
        }

        return articlesToReturn;
    }

}
