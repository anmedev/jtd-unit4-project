package com.teamtreehouse.blog.model;

import java.time.LocalDate;

public class Comment {
    private String author;
    private String content;
    private LocalDate date;

    public Comment(String author, String content, LocalDate date) {
        this.author = author;
        this.content = content;
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public void setDateTime(LocalDate date) {
        this.date = date;
    }
}
