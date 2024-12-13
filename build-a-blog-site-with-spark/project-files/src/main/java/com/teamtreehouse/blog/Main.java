package com.teamtreehouse.blog;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        // Route for Index Page
        get("/", (req, res) -> {
            return new ModelAndView(null, "index.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
