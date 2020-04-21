package io.swagger.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CategoryEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @OneToMany
    private List<TagEntity> children = new ArrayList<TagEntity>();

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TagEntity> getChildren() {
        return children;
    }

    public void setChildren(List<TagEntity> children) {
        this.children = children;
    }





}
