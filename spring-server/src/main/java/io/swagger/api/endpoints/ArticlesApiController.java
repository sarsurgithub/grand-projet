package io.swagger.api.endpoints;

import io.swagger.articles.api.ArticlesApi;
import io.swagger.articles.api.model.Article;
import io.swagger.articles.api.model.Category;
import io.swagger.articles.api.model.Comment;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.articles.api.model.Tag;
import io.swagger.articles.api.model.User;
import io.swagger.entities.ArticleEntity;
import io.swagger.entities.TagEntity;
import io.swagger.entities.UserEntity;
import io.swagger.repositories.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-08T14:21:38.963Z[GMT]")
@Controller
public class ArticlesApiController implements ArticlesApi {
    @Autowired
    ArticleRepository articleRepository;
    private static final Logger log = LoggerFactory.getLogger(ArticlesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ArticlesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addCommentToAnArticle(@ApiParam(value = "",required=true) @PathVariable("commentId") Long commentId
,@ApiParam(value = "ID of the article",required=true) @PathVariable("articleId") Long articleId
,@ApiParam(value = "comment to add to the article"  )  @Valid @RequestBody Comment body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> createArticle(@ApiParam(value = "Article that needs to be added" ,required=true )  @Valid @RequestBody Article body
) {
        String accept = request.getHeader("Accept");
        ArticleEntity newArticleEntity = toArticleEntity(body);
        articleRepository.save(newArticleEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newArticleEntity.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    public ResponseEntity<Void> deleteArticleById(@ApiParam(value = "Article id to delete",required=true) @PathVariable("articleId") Long articleId
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteComment(@ApiParam(value = "ID of the comment to delete",required=true) @PathVariable("commentId") Long commentId
,@ApiParam(value = "Article ID",required=true) @PathVariable("articleId") Long articleId
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Article> findArticleById(@ApiParam(value = "ID of the article to return",required=true) @PathVariable("articleId") Long articleId
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Article>(objectMapper.readValue("{\n  \"createdAt\" : \"12-02-2013H12-05\",\n  \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],\n  \"author\" : {\n    \"firstName\" : \"firstName\",\n    \"lastName\" : \"lastName\",\n    \"password\" : \"password\",\n    \"id\" : 1,\n    \"email\" : \"email\",\n    \"username\" : \"username\"\n  },\n  \"id\" : 0,\n  \"lastUpdateAt\" : \"12-02-2013H12-05\",\n  \"title\" : \"my super article\",\n  \"content\" : \"Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500...\",\n  \"views\" : 456,\n  \"tags\" : [ {\n    \"name\" : \"name\",\n    \"id\" : 6\n  }, {\n    \"name\" : \"name\",\n    \"id\" : 6\n  } ]\n}", Article.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Article>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Article>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Article>> findArticlesByCategories(@NotNull @ApiParam(value = "Categories to filter by", required = true) @Valid @RequestParam(value = "category", required = true) List<Category> category
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Article>>(objectMapper.readValue("[ {\n  \"createdAt\" : \"12-02-2013H12-05\",\n  \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],\n  \"author\" : {\n    \"firstName\" : \"firstName\",\n    \"lastName\" : \"lastName\",\n    \"password\" : \"password\",\n    \"id\" : 1,\n    \"email\" : \"email\",\n    \"username\" : \"username\"\n  },\n  \"id\" : 0,\n  \"lastUpdateAt\" : \"12-02-2013H12-05\",\n  \"title\" : \"my super article\",\n  \"content\" : \"Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500...\",\n  \"views\" : 456,\n  \"tags\" : [ {\n    \"name\" : \"name\",\n    \"id\" : 6\n  }, {\n    \"name\" : \"name\",\n    \"id\" : 6\n  } ]\n}, {\n  \"createdAt\" : \"12-02-2013H12-05\",\n  \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],\n  \"author\" : {\n    \"firstName\" : \"firstName\",\n    \"lastName\" : \"lastName\",\n    \"password\" : \"password\",\n    \"id\" : 1,\n    \"email\" : \"email\",\n    \"username\" : \"username\"\n  },\n  \"id\" : 0,\n  \"lastUpdateAt\" : \"12-02-2013H12-05\",\n  \"title\" : \"my super article\",\n  \"content\" : \"Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500...\",\n  \"views\" : 456,\n  \"tags\" : [ {\n    \"name\" : \"name\",\n    \"id\" : 6\n  }, {\n    \"name\" : \"name\",\n    \"id\" : 6\n  } ]\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Article>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Article>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Article>> findArticlesByDate() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Article>>(objectMapper.readValue("[ {\n  \"createdAt\" : \"12-02-2013H12-05\",\n  \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],\n  \"author\" : {\n    \"firstName\" : \"firstName\",\n    \"lastName\" : \"lastName\",\n    \"password\" : \"password\",\n    \"id\" : 1,\n    \"email\" : \"email\",\n    \"username\" : \"username\"\n  },\n  \"id\" : 0,\n  \"lastUpdateAt\" : \"12-02-2013H12-05\",\n  \"title\" : \"my super article\",\n  \"content\" : \"Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500...\",\n  \"views\" : 456,\n  \"tags\" : [ {\n    \"name\" : \"name\",\n    \"id\" : 6\n  }, {\n    \"name\" : \"name\",\n    \"id\" : 6\n  } ]\n}, {\n  \"createdAt\" : \"12-02-2013H12-05\",\n  \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],\n  \"author\" : {\n    \"firstName\" : \"firstName\",\n    \"lastName\" : \"lastName\",\n    \"password\" : \"password\",\n    \"id\" : 1,\n    \"email\" : \"email\",\n    \"username\" : \"username\"\n  },\n  \"id\" : 0,\n  \"lastUpdateAt\" : \"12-02-2013H12-05\",\n  \"title\" : \"my super article\",\n  \"content\" : \"Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500...\",\n  \"views\" : 456,\n  \"tags\" : [ {\n    \"name\" : \"name\",\n    \"id\" : 6\n  }, {\n    \"name\" : \"name\",\n    \"id\" : 6\n  } ]\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Article>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Article>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Comment>> findCommentbyID(@ApiParam(value = "ID of the comment",required=true) @PathVariable("commentId") Long commentId
,@ApiParam(value = "ID of the article",required=true) @PathVariable("articleId") Long articleId
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Comment>>(objectMapper.readValue("[ {\n  \"author\" : {\n    \"firstName\" : \"firstName\",\n    \"lastName\" : \"lastName\",\n    \"password\" : \"password\",\n    \"id\" : 1,\n    \"email\" : \"email\",\n    \"username\" : \"username\"\n  },\n  \"id\" : 0,\n  \"title\" : \"article nul\",\n  \"content\" : \"tu pourrais faire mieux\",\n  \"article\" : {\n    \"createdAt\" : \"12-02-2013H12-05\",\n    \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],\n    \"author\" : {\n      \"firstName\" : \"firstName\",\n      \"lastName\" : \"lastName\",\n      \"password\" : \"password\",\n      \"id\" : 1,\n      \"email\" : \"email\",\n      \"username\" : \"username\"\n    },\n    \"id\" : 0,\n    \"lastUpdateAt\" : \"12-02-2013H12-05\",\n    \"title\" : \"my super article\",\n    \"content\" : \"Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500...\",\n    \"views\" : 456,\n    \"tags\" : [ {\n      \"name\" : \"name\",\n      \"id\" : 6\n    }, {\n      \"name\" : \"name\",\n      \"id\" : 6\n    } ]\n  }\n}, {\n  \"author\" : {\n    \"firstName\" : \"firstName\",\n    \"lastName\" : \"lastName\",\n    \"password\" : \"password\",\n    \"id\" : 1,\n    \"email\" : \"email\",\n    \"username\" : \"username\"\n  },\n  \"id\" : 0,\n  \"title\" : \"article nul\",\n  \"content\" : \"tu pourrais faire mieux\",\n  \"article\" : {\n    \"createdAt\" : \"12-02-2013H12-05\",\n    \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],\n    \"author\" : {\n      \"firstName\" : \"firstName\",\n      \"lastName\" : \"lastName\",\n      \"password\" : \"password\",\n      \"id\" : 1,\n      \"email\" : \"email\",\n      \"username\" : \"username\"\n    },\n    \"id\" : 0,\n    \"lastUpdateAt\" : \"12-02-2013H12-05\",\n    \"title\" : \"my super article\",\n    \"content\" : \"Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500...\",\n    \"views\" : 456,\n    \"tags\" : [ {\n      \"name\" : \"name\",\n      \"id\" : 6\n    }, {\n      \"name\" : \"name\",\n      \"id\" : 6\n    } ]\n  }\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Comment>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Comment>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Comment>> findCommentsByArticle(@ApiParam(value = "Id of the article",required=true) @PathVariable("articleId") Long articleId
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Comment>>(objectMapper.readValue("[ {\n  \"author\" : {\n    \"firstName\" : \"firstName\",\n    \"lastName\" : \"lastName\",\n    \"password\" : \"password\",\n    \"id\" : 1,\n    \"email\" : \"email\",\n    \"username\" : \"username\"\n  },\n  \"id\" : 0,\n  \"title\" : \"article nul\",\n  \"content\" : \"tu pourrais faire mieux\",\n  \"article\" : {\n    \"createdAt\" : \"12-02-2013H12-05\",\n    \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],\n    \"author\" : {\n      \"firstName\" : \"firstName\",\n      \"lastName\" : \"lastName\",\n      \"password\" : \"password\",\n      \"id\" : 1,\n      \"email\" : \"email\",\n      \"username\" : \"username\"\n    },\n    \"id\" : 0,\n    \"lastUpdateAt\" : \"12-02-2013H12-05\",\n    \"title\" : \"my super article\",\n    \"content\" : \"Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500...\",\n    \"views\" : 456,\n    \"tags\" : [ {\n      \"name\" : \"name\",\n      \"id\" : 6\n    }, {\n      \"name\" : \"name\",\n      \"id\" : 6\n    } ]\n  }\n}, {\n  \"author\" : {\n    \"firstName\" : \"firstName\",\n    \"lastName\" : \"lastName\",\n    \"password\" : \"password\",\n    \"id\" : 1,\n    \"email\" : \"email\",\n    \"username\" : \"username\"\n  },\n  \"id\" : 0,\n  \"title\" : \"article nul\",\n  \"content\" : \"tu pourrais faire mieux\",\n  \"article\" : {\n    \"createdAt\" : \"12-02-2013H12-05\",\n    \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],\n    \"author\" : {\n      \"firstName\" : \"firstName\",\n      \"lastName\" : \"lastName\",\n      \"password\" : \"password\",\n      \"id\" : 1,\n      \"email\" : \"email\",\n      \"username\" : \"username\"\n    },\n    \"id\" : 0,\n    \"lastUpdateAt\" : \"12-02-2013H12-05\",\n    \"title\" : \"my super article\",\n    \"content\" : \"Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500...\",\n    \"views\" : 456,\n    \"tags\" : [ {\n      \"name\" : \"name\",\n      \"id\" : 6\n    }, {\n      \"name\" : \"name\",\n      \"id\" : 6\n    } ]\n  }\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Comment>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Comment>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateArticleById(@ApiParam(value = "updated article" ,required=true )  @Valid @RequestBody Article body
,@ApiParam(value = "ID of article that needs to be updated",required=true) @PathVariable("articleId") Long articleId
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateCommentbyId(@ApiParam(value = "ID of the comment that needs to be updated",required=true) @PathVariable("commentId") Long commentId
,@ApiParam(value = "ID of the article",required=true) @PathVariable("articleId") Long articleId
,@ApiParam(value = "updated comment"  )  @Valid @RequestBody Comment body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    private ArticleEntity toArticleEntity(Article article) {
        ArticleEntity entity = new ArticleEntity();
        entity.setAuthor(toUserEntity(article.getAuthor()));
        entity.setContent(article.getContent());
        entity.setCreatedAt(article.getCreatedAt());
        entity.setLastUpdateAt(article.getLastUpdateAt());
        entity.setPhotoUrls(article.getPhotoUrls());
        entity.setTags(toTagEntityList(article.getTags()));
        entity.setTitle(article.getTitle());
        entity.setViews(article.getViews());

        return entity;
    }

    private Article toArticle(ArticleEntity entity) {
        Article article = new Article();
        article.setAuthor(toUser(entity.getAuthor()));
        article.setContent(entity.getContent());
        article.setCreatedAt(entity.getCreatedAt());
        article.setLastUpdateAt(entity.getLastUpdateAt());
        article.setPhotoUrls(entity.getPhotoUrls());
        article.setTags(toTagList(entity.getTags()));
        article.setTitle(entity.getTitle());
        article.setViews(entity.getViews());
        return article;
    }

    private UserEntity toUserEntity( User user) {
       UserEntity entity = new UserEntity();
       entity.setEmail(user.getEmail());
       entity.setFirstName(user.getFirstName());
       entity.setLastName(user.getLastName());
       entity.setPassword(user.getPassword());
       entity.setUsername(user.getUsername());
       return entity;
    }

    private User toUser(UserEntity entity) {
        User user = new User();
        user.setUsername(entity.getUsername());
        user.setEmail(entity.getEmail());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setPassword(entity.getPassword());
        return user;
    }

    private TagEntity toTagEntity( Tag tag) {
        TagEntity entity = new TagEntity();
        entity.setName(tag.getName());
        return entity;
    }

    private List<Tag> toTagList(List<TagEntity> listEntity) {
        List<Tag> listTags = null;
        for (int i = 0; i < listEntity.size(); i++) {
            Tag tag = new Tag();
            tag.setName(listEntity.get(i).getName());
            listTags.add(tag);
        }

       return listTags;
    }

    private List<TagEntity> toTagEntityList(List<Tag> listTags) {
        List<TagEntity> listEntity = null;
        for (int i = 0; i < listTags.size(); i++) {
            TagEntity tagEntity = new TagEntity();
            tagEntity.setName(listTags.get(i).getName());
            listEntity.add(tagEntity);
        }
        return listEntity;
    }

}
