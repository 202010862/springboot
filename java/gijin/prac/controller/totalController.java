package gijin.prac.controller;

import gijin.prac.domain.Author;
import gijin.prac.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class totalController
{
    private AuthorService authorService;
    @Autowired
    public totalController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors/new")
    public String createForm()
    {
        return "authors/createForm";
    }
    @PostMapping("/authors/new")
    public String create(AuthorForm authorForm)
    {
        Author author=new Author();
        author.setDate(authorForm.getDate());
        author.setName(authorForm.getName());
        author.setStatement(authorForm.getStatement());
        authorService.join(author);
        return  "redirect:/";
    }
    @GetMapping("/authors")
    public String show(Model model)
    {
        List<Author> result=authorService.findAuthors();
        model.addAttribute("authors",result);
        return "authors/authorShow";
    }
}
