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
 * CreateArticle
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-08-26T11:16:28.324752+02:00[Europe/Zurich]")

public class CreateArticle   {
  @JsonProperty("title")
  private String title;

  @JsonProperty("content")
  private String content;

  @JsonProperty("author_id")
  private Long authorId;

  @JsonProperty("categories_ids")
  @Valid
  private List<Long> categoriesIds = null;

  public CreateArticle title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  */
  @ApiModelProperty(example = "my super article", required = true, value = "")
  @NotNull


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public CreateArticle content(String content) {
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

  public CreateArticle authorId(Long authorId) {
    this.authorId = authorId;
    return this;
  }

  /**
   * Get authorId
   * @return authorId
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Long authorId) {
    this.authorId = authorId;
  }

  public CreateArticle categoriesIds(List<Long> categoriesIds) {
    this.categoriesIds = categoriesIds;
    return this;
  }

  public CreateArticle addCategoriesIdsItem(Long categoriesIdsItem) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateArticle createArticle = (CreateArticle) o;
    return Objects.equals(this.title, createArticle.title) &&
        Objects.equals(this.content, createArticle.content) &&
        Objects.equals(this.authorId, createArticle.authorId) &&
        Objects.equals(this.categoriesIds, createArticle.categoriesIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, content, authorId, categoriesIds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateArticle {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    authorId: ").append(toIndentedString(authorId)).append("\n");
    sb.append("    categoriesIds: ").append(toIndentedString(categoriesIds)).append("\n");
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

