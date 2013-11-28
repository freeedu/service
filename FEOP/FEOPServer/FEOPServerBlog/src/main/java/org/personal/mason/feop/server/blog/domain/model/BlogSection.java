package org.personal.mason.feop.server.blog.domain.model;

import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the blog_section database table.
 */
@Entity
@Table(name = "blog_section")
public class BlogSection extends BlogPersistable {

    private static final long serialVersionUID = 3877355201598927979L;

    @Lob
    @Column(name = "section_content")
    private String sectionContent;

    @Column(name = "section_title")
    private String sectionTitle;

    @Column(name = "seq")
    private int sequence;

    // bi-directional many-to-one association to Blog
    @ManyToOne
    private Blog blog;

    // bi-directional many-to-one association to Comment
    @OneToMany(mappedBy = "blogSection")
    private List<Comment> comments;

    public BlogSection() {
    }

    public String getSectionContent() {
        return this.sectionContent;
    }

    public void setSectionContent(String sectionContent) {
        this.sectionContent = sectionContent;
    }

    public String getSectionTitle() {
        return this.sectionTitle;
    }

    public void setSectionTitle(String sectionTitle) {
        this.sectionTitle = sectionTitle;
    }

    public int getSequence() {
        return this.sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public Blog getBlog() {
        return this.blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}