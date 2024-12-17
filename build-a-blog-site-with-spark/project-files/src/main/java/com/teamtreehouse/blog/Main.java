package com.teamtreehouse.blog;

import com.teamtreehouse.blog.dao.BlogDao;
import com.teamtreehouse.blog.dao.NotFoundException;
import com.teamtreehouse.blog.dao.SimpleBlogDao;
import com.teamtreehouse.blog.model.BlogEntry;
import com.teamtreehouse.blog.model.Comment;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Main {
    private static final String FLASH_MESSAGE_KEY = "flash_message";

    public static void main(String[] args) {
        staticFileLocation("/public");
        BlogDao dao = new SimpleBlogDao();

        before((req, res) -> {
            if (req.cookie("admin") != null) {
                req.attribute("admin", req.cookie("admin"));
            }
        });

        // Route for Index Page
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("entries", dao.findAllEntries());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        // Route for Detail Page
        get("/detail/:slug", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("detail", dao.findEntryBySlug(req.params("slug")));
            return new ModelAndView(model, "detail.hbs");
        }, new HandlebarsTemplateEngine());

        // Route to Get a New Blog Entry Page
        get("/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "new.hbs");
        }, new HandlebarsTemplateEngine());

        // Route to Add a New Blog Entry
        post("/new", (req, res) -> {
            String title = req.queryParams("title");
            String content = req.queryParams("content");
            LocalDate date = LocalDate.now();

            BlogEntry entry = new BlogEntry(title, content, date);
            dao.addEntry(entry);
            res.redirect("/");
            return null;
        });

        // Route to Show Edit Blog Entry Page
        get("/edit/:slug", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("entry", dao.findEntryBySlug(req.params("slug")));
            return new ModelAndView(model, "edit.hbs");
        }, new HandlebarsTemplateEngine());


        // Route to Handle Edited Blog Entry Page
        post("/edit/:slug", (req, res) -> {
            String slug = req.params("slug");
            BlogEntry entry = dao.findEntryBySlug(slug);

            entry.setTitle(req.queryParams("title"));
            entry.setContent(req.queryParams("content"));
            entry.setDate(LocalDate.now());

            res.redirect("/detail/" + entry.getSlug());
            return null;
        });


        // Route to Add a Comment to a Blog Entry
        post("/detail/:slug/comment", (req, res) -> {
            String slug = req.params("slug");
            BlogEntry entry = dao.findEntryBySlug(slug);
            String author = req.queryParams("author");
            String content = req.queryParams("content");
            LocalDate date = LocalDate.now();

            Comment comment = new Comment(author, content, date);
            entry.addComment(comment);

            res.redirect("/detail/" + slug);
            return null;
        });



//        private static String getFlashMessage(Request req) {
//            if (req.session(false) == null) {
//                return null;
//            }
//            if (req.session().attributes().contains(FLASH_MESSAGE_KEY)) {
//                return null;
//            }
//            return req.session().attribute(FLASH_MESSAGE_KEY);
//        }
//
//        private static void setFlashMessage(Request req, String message) {
//            req.session().attribute(FLASH_MESSAGE_KEY, message);
//        }








    }
}
