package org.personal.mason.feop.server.blog.domain.model;

import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the category database table.
 */
@Entity
@Table(name = "category")
public class Category extends BlogPersistable {

    private static final long serialVersionUID = 3659463451113700595L;

    @Column(name = "category_name")
    private String categoryName;

    @Lob
    @Column(name = "description")
    private String description;

    // bi-directional many-to-one association to Blog
    @OneToMany(mappedBy = "category")
    private List<Blog> blogs;

    // bi-directional many-to-one association to Category
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category category;

    // bi-directional many-to-one association to Category
    @OneToMany(mappedBy = "category")
    private List<Category> categories;

    // bi-directional many-to-one association to Sery
    @OneToMany(mappedBy = "category")
    private List<Sery> series;

    public Category() {
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Blog> getBlogs() {
        return this.blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Sery> getSeries() {
        return this.series;
    }

    public void setSeries(List<Sery> series) {
        this.series = series;
    }

}