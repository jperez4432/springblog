package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice")
    public String guess() {
        return "guess";
    }

    @GetMapping("/roll-dice/{n}")
    public String checkGuess(@PathVariable int n, Model model) {
        int outcome = (int) Math.floor((Math.random() * 6) + 1);
        boolean check = outcome == n;
        model.addAttribute("n", n);
        model.addAttribute("outcome", outcome);
        model.addAttribute("check", check);
        return "dice-roll-check";
    }
}
