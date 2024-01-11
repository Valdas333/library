package com.techin.library.book.search.library.book.search.controllers;

import com.techin.library.book.search.library.book.search.models.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookSearchFormController {
    BookSearchController bookSearchController;

    public BookSearchFormController(BookSearchController bookSearchController) {
        this.bookSearchController = bookSearchController;
    }

    @GetMapping("/show-form")
    public String showForm(){
        return "author-search";
    }

    @PostMapping("/processAuthorSearchForm")
    public String processAuthorSearchForm(@RequestParam ("authorName") String authorName, Model model){
        List <Book> authorBooks = bookSearchController.searchBooksByAuthor(authorName);
        System.out.println(authorBooks);
        model.addAttribute("author", authorName);
        model.addAttribute("authorsBooks", authorBooks);
        return "author-search-results";
    }
}
