package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String Posts() {
        return "<h1>Posts index page</h1>";
    }

    @GetMapping("/posts{id}")
    @ResponseBody
    public String PostsByID(@PathVariable int id) {
        return "<h1>View individual post " + id + "</h1>";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String CreatePost() {
        return "<h1>View the form for creating a post</h1>";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String hello() {
        return "<h1>Create a new post</h1>";
    }
}
