package org.launchcode.skillstracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("")
public class SkillsController {
    private final String[] LANGUAGES = {"Java", "JavaScript", "Python"};

    @GetMapping("/")
    public String index() {
        return "<h1>Skills Tracker</h1>" +
                "<h2>Programming Languages</h2>" +
                buildOL() +
                "<a href='/form'>Take the quiz</a>";
    }

    @GetMapping("/form")
    public String form() {
        return "<h1>Skills Tracker</h1>" +
                "<h2>Select your favorite languages</h2>" +
                buildForm() +
                "<a href='/'>Home</a>";
    }

    @PostMapping("/form")
    public String postData(@RequestParam String name, @RequestParam String gold, @RequestParam String silver, @RequestParam String bronze) {
        return "<h1>" + name + "'s Skills Tracker:</h1>" +
                "<h2>Favorite languages</h2>" +
                buildOL(new String[]{gold, silver, bronze}) +
                "<a href='/'>Home</a> | " +
                "<a href='/form'>Retake the quiz</a>";
    }

    private String buildForm() {
        String[] favoritePrompts = {"First favorite", "Second favorite", "Third favorite"};
        String[] ranks = {"gold", "silver", "bronze"};
        StringBuilder res = new StringBuilder();

        res.append("<form method='post' action='/form'>");
        res.append("<label for='name'>Name:</label>");
        res.append("<input type='text' name='name'/>");
        res.append("<br/>");

        for (int i = 0; i < favoritePrompts.length; i++) {
            res.append("<label for='" + ranks[i] + "'>" + favoritePrompts[i] + ":</label>");
            res.append(buildSelect(ranks[i]));
            res.append("<br/>");
        }

        res.append("<input type='submit' value='Submit'>");

        res.append("</form>");

        return res.toString();
    }

    private String buildSelect(String name) {
        return buildSelect(name, LANGUAGES);
    }

    private String buildSelect(String name, String[] languages) {
        StringBuilder res = new StringBuilder();

        res.append("<select name='" + name + "'>");
        for (String language : languages) {
            res.append(String.format("<option value='%s'>%s</option>", language, language));
        }
        res.append("</select>");

        return res.toString();
    }

    private String buildOL() {
        return buildOL(LANGUAGES);
    }

    private String buildOL(String[] languages) {
        StringBuilder res = new StringBuilder();

        res.append("<ol>");
        for (String language : languages) {
            res.append(String.format("<li>%s</li>", language));
        }
        res.append("</ol>");

        return res.toString();
    }
}
