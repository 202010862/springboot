package gijin.prac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorController
{
    @GetMapping("/")
    public String Home()
    {
        return "home";
    }
    asdadasdadadad
}