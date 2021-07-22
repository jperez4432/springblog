package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    private List<Post> posts = new ArrayList<>();

    @GetMapping("/posts")
    public String viewPosts(Model model) {
        posts.add(new Post("This is post1", "This is post1s body"));
        posts.add(new Post("This is post2", "This is post2s body"));
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable long id, Model model) {
        Post post = new Post("Jeff buys bicycle.", "No one know why. Must really like the feeling of the wind on his face.");
        model.addAttribute("post", post);
        return "posts/show";
    }

    // When you visit the URL you will see the form to create a post.
    @GetMapping("/posts/create")
    @ResponseBody
    public String createForm() {
        return "View form to create a post.";
    }

    // When you submit the form on the /posts/create page,
    // the information will be posted to the same URL
//    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost() {
        return "Creates new post.";
    }
}