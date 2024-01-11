package com.techin.library.book.search.library.book.search.controllers;

import com.techin.library.book.search.library.book.search.models.Book;
import com.techin.library.book.search.library.book.search.service.BookSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search-book/{author}")
public class BookSearchController {

    private final BookSearchService bookSearchService;

    @Autowired
    public BookSearchController(BookSearchService bookSearchService) {
        this.bookSearchService = bookSearchService;
    }

    @GetMapping
    public List<Book> searchBooksByAuthor(@PathVariable String author) {
        return bookSearchService.searchBooksByAuthor(author);
    }
}
