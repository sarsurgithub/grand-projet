package io.swagger.articles.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.articles.api.model.GetComment;
import io.swagger.articles.api.model.GetUser;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetArticle
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-18T16:15:42.793756+02:00[Europe/Zurich]")

public class GetArticle   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("title")
  private String title;

  @JsonProperty("author")
  private GetUser author;

  @JsonProperty("content")
  private String content;

  @JsonProperty("photoUrls")
  @Valid
  private List<String> photoUrls = null;

  @JsonProperty("createdAt")
  private String createdAt;

  @JsonProperty("lastUpdateAt")
  private String lastUpdateAt;

  @JsonProperty("views")
  private Integer views;

  @JsonProperty("comments")
  @Valid
  private List<GetComment> comments = null;

  public GetArticle id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public GetArticle title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public GetArticle author(GetUser author) {
    this.author = author;
    return this;
  }

  /**
   * Get author
   * @return author
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public GetUser getAuthor() {
    return author;
  }

  public void setAuthor(GetUser author) {
    this.author = author;
  }

  public GetArticle content(String content) {
    this.content = content;
    return this;
  }

  /**
   * Get content
   * @return content
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public GetArticle photoUrls(List<String> photoUrls) {
    this.photoUrls = photoUrls;
    return this;
  }

  public GetArticle addPhotoUrlsItem(String photoUrlsItem) {
    if (this.photoUrls == null) {
      this.photoUrls = new ArrayList<String>();
    }
    this.photoUrls.add(photoUrlsItem);
    return this;
  }

  /**
   * Get photoUrls
   * @return photoUrls
  */
  @ApiModelProperty(value = "")


  public List<String> getPhotoUrls() {
    return photoUrls;
  }

  public void setPhotoUrls(List<String> photoUrls) {
    this.photoUrls = photoUrls;
  }

  public GetArticle createdAt(String createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Get createdAt
   * @return createdAt
  */
  @ApiModelProperty(example = "12-02-2013H12-05", required = true, value = "")
  @NotNull


  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public GetArticle lastUpdateAt(String lastUpdateAt) {
    this.lastUpdateAt = lastUpdateAt;
    return this;
  }

  /**
   * Get lastUpdateAt
   * @return lastUpdateAt
  */
  @ApiModelProperty(example = "12-02-2013H12-05", value = "")


  public String getLastUpdateAt() {
    return lastUpdateAt;
  }

  public void setLastUpdateAt(String lastUpdateAt) {
    this.lastUpdateAt = lastUpdateAt;
  }

  public GetArticle views(Integer views) {
    this.views = views;
    return this;
  }

  /**
   * Get views
   * @return views
  */
  @ApiModelProperty(example = "456", required = true, value = "")
  @NotNull


  public Integer getViews() {
    return views;
  }

  public void setViews(Integer views) {
    this.views = views;
  }

  public GetArticle comments(List<GetComment> comments) {
    this.comments = comments;
    return this;
  }

  public GetArticle addCommentsItem(GetComment commentsItem) {
    if (this.comments == null) {
      this.comments = new ArrayList<GetComment>();
    }
    this.comments.add(commentsItem);
    return this;
  }

  /**
   * Get comments
   * @return comments
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<GetComment> getComments() {
    return comments;
  }

  public void setComments(List<GetComment> comments) {
    this.comments = comments;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetArticle getArticle = (GetArticle) o;
    return Objects.equals(this.id, getArticle.id) &&
        Objects.equals(this.title, getArticle.title) &&
        Objects.equals(this.author, getArticle.author) &&
        Objects.equals(this.content, getArticle.content) &&
        Objects.equals(this.photoUrls, getArticle.photoUrls) &&
        Objects.equals(this.createdAt, getArticle.createdAt) &&
        Objects.equals(this.lastUpdateAt, getArticle.lastUpdateAt) &&
        Objects.equals(this.views, getArticle.views) &&
        Objects.equals(this.comments, getArticle.comments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, author, content, photoUrls, createdAt, lastUpdateAt, views, comments);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetArticle {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    photoUrls: ").append(toIndentedString(photoUrls)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    lastUpdateAt: ").append(toIndentedString(lastUpdateAt)).append("\n");
    sb.append("    views: ").append(toIndentedString(views)).append("\n");
    sb.append("    comments: ").append(toIndentedString(comments)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

