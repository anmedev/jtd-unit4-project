package com.teamtreehouse.blog.model;

import com.github.slugify.Slugify;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class BlogEntry {
    private String slug;
    // Adds fields blog content
    private String title;
    private String content;
    private LocalDate date;
    private List<Comment> comments;

    public BlogEntry(String title, String content, LocalDate date) {
        this.title = title;
        this.content = content;
        this.date = date;
        try {
            Slugify slugify = new Slugify();
            slug = slugify.slugify(title);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to add comments
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    // Method to delete comments
    public void deleteComment(Comment comment) {
        comments.remove(comment);
    }

    public String getSlug() {
        return slug;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


}
