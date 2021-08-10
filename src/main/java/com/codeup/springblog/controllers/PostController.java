package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
// import com.codeup.springblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;
   // private final EmailService emailSvc;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;

    }

    @GetMapping("/posts")
    public String index(Model model){
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}/edit")
    public String getOne(Model model,@PathVariable long id){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postDao.getById(id);
        if (currentUser.getId() == post.getUser().getId()){
            model.addAttribute("post", postDao.getById(id));
            return "posts/edit";
        }  else {
            return "redirect:/posts/" + id;
        }
    }

    @PostMapping("/posts/{id}/edit")
    public String editOne(Model model,@PathVariable long id, @ModelAttribute Post post){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post postFromDB = postDao.getById(id);
        if(currentUser.getId() == postFromDB.getUser().getId()){
            post.setUser(currentUser);
            postDao.save(post);
        }
        return "redirect:/posts/" + id;
    }


    @GetMapping("/posts/{id}")
    public String showOne(Model model,@PathVariable long id){
        Post post = postDao.getById(id);
        boolean isPostOwner = false;
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            isPostOwner = currentUser.getId() == post.getUser().getId();
        }
        model.addAttribute("post", post);
        model.addAttribute("isPostOwner", isPostOwner);
        return "posts/show";
    }

    @PostMapping("/posts/delete/{id}")
    public String deleteOne(@PathVariable long id){
        postDao.deleteById(id);
        return "redirect:/posts";
    }

//    List<Post> posts = new ArrayList<>();
//
//    @RequestMapping(path = "/posts", method = RequestMethod.GET)
//    public String takeToIndex(Model model){
//        posts.add(new Post("This is post1", "This is post1s body"));
//        posts.add(new Post("This is post2", "This is post2s body"));
//        model.addAttribute("posts", posts);
//        return "posts/index";
//    }
//
//    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
//    public String takeToIndivPost(@PathVariable long id, Model model){
//        Post post =  new Post("Jeff buys a bicycle", "No one knows why, must really like the wind");
//        model.addAttribute("post", post);
//        return "posts/show";
//    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    public String createPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    // refactor here
    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    public String createPost (@ModelAttribute Post post){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(currentUser);
        postDao.save(post);
      //  emailSvc.prepareAndSend("jperez4432@gmail.com", "Thank you for creating a post " + post.getUser().getUsername() + "!", "You created a post titled: " + post.getTitle());
        return "redirect:/posts";
    }
}