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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-08-26T11:16:28.324752+02:00[Europe/Zurich]")

public class UpdateArticle   {
  @JsonProperty("title")
  private String title;

  @JsonProperty("content")
  private String content;

  @JsonProperty("views")
  private Integer views;

  @JsonProperty("categories_ids")
  @Valid
  private List<Long> categoriesIds = null;

  @JsonProperty("comments_ids")
  @Valid
  private List<Long> commentsIds = null;

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

  public UpdateArticle views(Integer views) {
    this.views = views;
    return this;
  }

  /**
   * Get views
   * @return views
  */
  @ApiModelProperty(example = "456", value = "")


  public Integer getViews() {
    return views;
  }

  public void setViews(Integer views) {
    this.views = views;
  }

  public UpdateArticle categoriesIds(List<Long> categoriesIds) {
    this.categoriesIds = categoriesIds;
    return this;
  }

  public UpdateArticle addCategoriesIdsItem(Long categoriesIdsItem) {
    if (this.categoriesIds == null) {
      this.categoriesIds = new ArrayList<Long>();
    }
    this.categoriesIds.add(categoriesIdsItem);
    return this;
  }

  /**
   * Get categoriesIds
   * @return categoriesIds
  */
  @ApiModelProperty(value = "")


  public List<Long> getCategoriesIds() {
    return categoriesIds;
  }

  public void setCategoriesIds(List<Long> categoriesIds) {
    this.categoriesIds = categoriesIds;
  }

  public UpdateArticle commentsIds(List<Long> commentsIds) {
    this.commentsIds = commentsIds;
    return this;
  }

  public UpdateArticle addCommentsIdsItem(Long commentsIdsItem) {
    if (this.commentsIds == null) {
      this.commentsIds = new ArrayList<Long>();
    }
    this.commentsIds.add(commentsIdsItem);
    return this;
  }

  /**
   * Get commentsIds
   * @return commentsIds
  */
  @ApiModelProperty(value = "")


  public List<Long> getCommentsIds() {
    return commentsIds;
  }

  public void setCommentsIds(List<Long> commentsIds) {
    this.commentsIds = commentsIds;
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
    return Objects.equals(this.title, updateArticle.title) &&
        Objects.equals(this.content, updateArticle.content) &&
        Objects.equals(this.views, updateArticle.views) &&
        Objects.equals(this.categoriesIds, updateArticle.categoriesIds) &&
        Objects.equals(this.commentsIds, updateArticle.commentsIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, content, views, categoriesIds, commentsIds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateArticle {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    views: ").append(toIndentedString(views)).append("\n");
    sb.append("    categoriesIds: ").append(toIndentedString(categoriesIds)).append("\n");
    sb.append("    commentsIds: ").append(toIndentedString(commentsIds)).append("\n");
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

