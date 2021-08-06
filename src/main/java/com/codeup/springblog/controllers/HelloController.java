package com.codeup.springblog.controllers;

//import com.codeup.springblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
class HelloController {
   // private final EmailService emailSvc;

//    public HelloController(EmailService emailSvc) {
//      //  this.emailSvc = emailSvc;
//    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "<h1>Hello from Spring!</h1>";
    }

    @GetMapping("/hello/{name}")
    @ResponseBody
    public String sayHello(@PathVariable String name) {
        return "<h1>Hello " + name + "!</h1>";
    }

    @GetMapping("/join")
    public String showJoinForm() {
        return "join";
    }

    @PostMapping("/join")
    public String joinCohort(@RequestParam(name = "cohort") String cohort, Model model) {
        model.addAttribute("cohort", "Welcome to " + cohort + "!");
     //   emailSvc.prepareAndSend("jperez4432@gmail.com", "hello! welcome to " + cohort + "!", "Thank you for attending our web development program!");
        return "join";
    }

    @GetMapping("/number/{num}")
    @ResponseBody
    public int displayNumber(@PathVariable int num) {
        return num;
    }

    @RequestMapping(path = "/hello/in/color", method = RequestMethod.GET)
    @ResponseBody
    public String helloInColor(@PathVariable String color) {
        return "<h1 style=\"color: " + color + "\">Hello in " + color + "!</h1>";
    }
}
