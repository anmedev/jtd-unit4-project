package com.teamtreehouse.blog.dao;

import com.teamtreehouse.blog.model.BlogEntry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SimpleBlogDao implements BlogDao{
    private List<BlogEntry> entries;

    public SimpleBlogDao() {
       entries = new ArrayList<>();
    }

    @Override
    public boolean addEntry(BlogEntry blogEntry) {
        boolean isDuplicate = entries.stream()
                .anyMatch(blog -> blog.getSlug().equals(blogEntry.getSlug()));
        if (isDuplicate) {
            return false;
        }
        return entries.add(blogEntry);
    }

    @Override
    public List<BlogEntry> findAllEntries() {
        List<BlogEntry> entries = new ArrayList<>();
        entries.add(new BlogEntry("The best day I’ve ever had", "The best day I've ever had content", LocalDate.parse("2024-10-01")));
        entries.add(new BlogEntry("The absolute worst day I’ve ever had", "The absolute worst day I’ve ever had content", LocalDate.parse("2024-10-01")));
        entries.add(new BlogEntry("That time at the mall", "That time at the mall content", LocalDate.parse("2024-10-01")));
        entries.add(new BlogEntry("Dude, where’s my car?", "Dude, where’s my car? content", LocalDate.parse("2024-10-01")));
        return entries;
    }


    @Override
    public BlogEntry findEntryBySlug(String slug) {
        return entries.stream()
                .filter(entry -> entry.getSlug().equals(slug))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
}
