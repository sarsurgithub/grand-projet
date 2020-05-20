/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.2.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package io.swagger.articles.api;

import io.swagger.articles.api.model.CreateArticle;
import io.swagger.articles.api.model.CreateComment;
import io.swagger.articles.api.model.GetArticle;
import io.swagger.articles.api.model.GetCategory;
import io.swagger.articles.api.model.GetComment;
import io.swagger.articles.api.model.UpdateArticle;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-20T11:50:33.217718+02:00[Europe/Zurich]")

@Validated
@Api(value = "articles", description = "the articles API")
public interface ArticlesApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @ApiOperation(value = "add a new comment to an article", nickname = "addCommentToAnArticle", notes = "", tags={ "comments", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "added successfully"),
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/articles/{articleId}/comments",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> addCommentToAnArticle(@ApiParam(value = "ID of the article",required=true) @PathVariable("articleId") Long articleId,@ApiParam(value = "comment to add to the article"  )  @Valid @RequestBody CreateComment createComment) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "Creates a new article", nickname = "createArticle", notes = "", tags={ "articles", })
    @ApiResponses(value = { 
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/articles",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> createArticle(@ApiParam(value = "Article that needs to be added" ,required=true )  @Valid @RequestBody CreateArticle createArticle) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "Deletes an article by ID", nickname = "deleteArticleById", notes = "", tags={ "articles", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "deleted successfully"),
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "Article not found") })
    @RequestMapping(value = "/articles/{articleId}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteArticleById(@ApiParam(value = "Article id to delete",required=true) @PathVariable("articleId") Long articleId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "Deletes a comment by ID", nickname = "deleteComment", notes = "", tags={ "comments", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "deleted successfully"),
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "Comment not found") })
    @RequestMapping(value = "/articles/{articleId}/comments/{commentId}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteComment(@ApiParam(value = "ID of the comment to delete",required=true) @PathVariable("commentId") Long commentId,@ApiParam(value = "Article ID",required=true) @PathVariable("articleId") Long articleId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "Find article by ID", nickname = "findArticleById", notes = "Returns a single article by ID", response = GetArticle.class, tags={ "articles", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = GetArticle.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "Article not found") })
    @RequestMapping(value = "/articles/{articleId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<GetArticle> findArticleById(@ApiParam(value = "ID of the article to return",required=true) @PathVariable("articleId") Long articleId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"createdAt\" : \"12-02-2013H12-05\", \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ], \"comments\" : [ { \"author\" : 5, \"id\" : 1, \"title\" : \"article nul\", \"content\" : \"tu pourrais faire mieux\" }, { \"author\" : 5, \"id\" : 1, \"title\" : \"article nul\", \"content\" : \"tu pourrais faire mieux\" } ], \"author\" : { \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"id\" : 6, \"email\" : \"email\", \"username\" : \"username\" }, \"id\" : 0, \"lastUpdateAt\" : \"12-02-2013H12-05\", \"categories\" : [ { \"name\" : \"name\", \"id\" : 5 }, { \"name\" : \"name\", \"id\" : 5 } ], \"title\" : \"title\", \"content\" : \"content\", \"views\" : 456 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "Finds articles by categories", nickname = "findArticlesByCategories", notes = "", response = GetArticle.class, responseContainer = "List", tags={ "articles", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = GetArticle.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Invalid category") })
    @RequestMapping(value = "/articles/byCategories",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<List<GetArticle>> findArticlesByCategories(@NotNull @ApiParam(value = "Categories to filter by", required = true) @Valid @RequestParam(value = "categories", required = true) List<GetCategory> categories) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"createdAt\" : \"12-02-2013H12-05\", \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ], \"comments\" : [ { \"author\" : 5, \"id\" : 1, \"title\" : \"article nul\", \"content\" : \"tu pourrais faire mieux\" }, { \"author\" : 5, \"id\" : 1, \"title\" : \"article nul\", \"content\" : \"tu pourrais faire mieux\" } ], \"author\" : { \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"id\" : 6, \"email\" : \"email\", \"username\" : \"username\" }, \"id\" : 0, \"lastUpdateAt\" : \"12-02-2013H12-05\", \"categories\" : [ { \"name\" : \"name\", \"id\" : 5 }, { \"name\" : \"name\", \"id\" : 5 } ], \"title\" : \"title\", \"content\" : \"content\", \"views\" : 456 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "Finds all articles by date", nickname = "findArticlesByDate", notes = "", response = GetArticle.class, responseContainer = "List", tags={ "articles", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = GetArticle.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Articles not found") })
    @RequestMapping(value = "/articles",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<List<GetArticle>> findArticlesByDate() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"createdAt\" : \"12-02-2013H12-05\", \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ], \"comments\" : [ { \"author\" : 5, \"id\" : 1, \"title\" : \"article nul\", \"content\" : \"tu pourrais faire mieux\" }, { \"author\" : 5, \"id\" : 1, \"title\" : \"article nul\", \"content\" : \"tu pourrais faire mieux\" } ], \"author\" : { \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"id\" : 6, \"email\" : \"email\", \"username\" : \"username\" }, \"id\" : 0, \"lastUpdateAt\" : \"12-02-2013H12-05\", \"categories\" : [ { \"name\" : \"name\", \"id\" : 5 }, { \"name\" : \"name\", \"id\" : 5 } ], \"title\" : \"title\", \"content\" : \"content\", \"views\" : 456 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "returns a comment by ID", nickname = "findCommentbyID", notes = "", response = GetComment.class, tags={ "comments", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = GetComment.class),
        @ApiResponse(code = 400, message = "Invalid tag value") })
    @RequestMapping(value = "/articles/{articleId}/comments/{commentId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<GetComment> findCommentbyID(@ApiParam(value = "ID of the comment",required=true) @PathVariable("commentId") Long commentId,@ApiParam(value = "ID of the article",required=true) @PathVariable("articleId") Long articleId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"author\" : 5, \"id\" : 1, \"title\" : \"article nul\", \"content\" : \"tu pourrais faire mieux\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "returns an array of comments for a particular article", nickname = "findCommentsByArticle", notes = "", response = GetComment.class, responseContainer = "List", tags={ "comments", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = GetComment.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Invalid tag value") })
    @RequestMapping(value = "/articles/{articleId}/comments",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<List<GetComment>> findCommentsByArticle(@ApiParam(value = "Id of the article",required=true) @PathVariable("articleId") Long articleId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"author\" : 5, \"id\" : 1, \"title\" : \"article nul\", \"content\" : \"tu pourrais faire mieux\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "Updates an article by ID", nickname = "updateArticleById", notes = "", tags={ "articles", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "updated successfully"),
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/articles/{articleId}",
        consumes = { "application/json" },
        method = RequestMethod.PATCH)
    default ResponseEntity<Void> updateArticleById(@ApiParam(value = "ID of article that needs to be updated",required=true) @PathVariable("articleId") Long articleId,@ApiParam(value = "updated article" ,required=true )  @Valid @RequestBody UpdateArticle updateArticle) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "Updates a comment by ID", nickname = "updateCommentbyId", notes = "", tags={ "comments", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "updated successfully"),
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/articles/{articleId}/comments/{commentId}",
        consumes = { "application/json" },
        method = RequestMethod.PATCH)
    default ResponseEntity<Void> updateCommentbyId(@ApiParam(value = "ID of the comment that needs to be updated",required=true) @PathVariable("commentId") Long commentId,@ApiParam(value = "ID of the article",required=true) @PathVariable("articleId") Long articleId,@ApiParam(value = "updated comment"  )  @Valid @RequestBody CreateComment createComment) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
