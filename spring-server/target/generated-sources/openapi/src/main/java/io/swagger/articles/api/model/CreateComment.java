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
 * CreateComment
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-18T17:32:29.655545+02:00[Europe/Zurich]")

public class CreateComment   {
  @JsonProperty("title")
  private String title;

  @JsonProperty("content")
  private String content;

  @JsonProperty("author")
  private Long author;

  @JsonProperty("categories_ids")
  @Valid
  private List<Integer> categoriesIds = null;

  public CreateComment title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  */
  @ApiModelProperty(example = "article nul", required = true, value = "")
  @NotNull


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public CreateComment content(String content) {
    this.content = content;
    return this;
  }

  /**
   * Get content
   * @return content
  */
  @ApiModelProperty(example = "tu pourrais faire mieux", required = true, value = "")
  @NotNull


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public CreateComment author(Long author) {
    this.author = author;
    return this;
  }

  /**
   * Get author
   * @return author
  */
  @ApiModelProperty(value = "")


  public Long getAuthor() {
    return author;
  }

  public void setAuthor(Long author) {
    this.author = author;
  }

  public CreateComment categoriesIds(List<Integer> categoriesIds) {
    this.categoriesIds = categoriesIds;
    return this;
  }

  public CreateComment addCategoriesIdsItem(Integer categoriesIdsItem) {
    if (this.categoriesIds == null) {
      this.categoriesIds = new ArrayList<Integer>();
    }
    this.categoriesIds.add(categoriesIdsItem);
    return this;
  }

  /**
   * Get categoriesIds
   * @return categoriesIds
  */
  @ApiModelProperty(value = "")


  public List<Integer> getCategoriesIds() {
    return categoriesIds;
  }

  public void setCategoriesIds(List<Integer> categoriesIds) {
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
    CreateComment createComment = (CreateComment) o;
    return Objects.equals(this.title, createComment.title) &&
        Objects.equals(this.content, createComment.content) &&
        Objects.equals(this.author, createComment.author) &&
        Objects.equals(this.categoriesIds, createComment.categoriesIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, content, author, categoriesIds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateComment {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
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

