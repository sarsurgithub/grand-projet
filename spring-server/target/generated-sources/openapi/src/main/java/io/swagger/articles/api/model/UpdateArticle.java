package io.swagger.articles.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UpdateArticle
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-18T10:09:53.531853+02:00[Europe/Zurich]")

public class UpdateArticle   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("title")
  private String title;

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

  public UpdateArticle id(Long id) {
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

  public UpdateArticle title(String title) {
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

  public UpdateArticle content(String content) {
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

  public UpdateArticle photoUrls(List<String> photoUrls) {
    this.photoUrls = photoUrls;
    return this;
  }

  public UpdateArticle addPhotoUrlsItem(String photoUrlsItem) {
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

  public UpdateArticle createdAt(String createdAt) {
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

  public UpdateArticle lastUpdateAt(String lastUpdateAt) {
    this.lastUpdateAt = lastUpdateAt;
    return this;
  }

  /**
   * Get lastUpdateAt
   * @return lastUpdateAt
  */
  @ApiModelProperty(example = "12-02-2013H12-05", required = true, value = "")
  @NotNull


  public String getLastUpdateAt() {
    return lastUpdateAt;
  }

  public void setLastUpdateAt(String lastUpdateAt) {
    this.lastUpdateAt = lastUpdateAt;
  }

  public UpdateArticle views(Integer views) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateArticle updateArticle = (UpdateArticle) o;
    return Objects.equals(this.id, updateArticle.id) &&
        Objects.equals(this.title, updateArticle.title) &&
        Objects.equals(this.content, updateArticle.content) &&
        Objects.equals(this.photoUrls, updateArticle.photoUrls) &&
        Objects.equals(this.createdAt, updateArticle.createdAt) &&
        Objects.equals(this.lastUpdateAt, updateArticle.lastUpdateAt) &&
        Objects.equals(this.views, updateArticle.views);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, content, photoUrls, createdAt, lastUpdateAt, views);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateArticle {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    photoUrls: ").append(toIndentedString(photoUrls)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    lastUpdateAt: ").append(toIndentedString(lastUpdateAt)).append("\n");
    sb.append("    views: ").append(toIndentedString(views)).append("\n");
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
