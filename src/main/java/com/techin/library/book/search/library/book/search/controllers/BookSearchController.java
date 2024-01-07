package com.techin.library.book.search.library.book.search.controllers;

import com.techin.library.book.search.library.book.search.models.AuthorWorkList;
import com.techin.library.book.search.library.book.search.models.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/search-book/{author}")
public class BookSearchController {

    private final RestClient restClient;

    public BookSearchController() {
        this.restClient = RestClient.create();
    }

    @GetMapping
    public List<Book> searchBooksByAuthor(@PathVariable String author) {
        String path = "/search/authors.json";

        URI uri = buildUri(path, author);

        System.out.println(uri);

        AuthorWorkList authorWorks = restClient.get()
                .uri(uri)
                .retrieve()
                .body(AuthorWorkList.class);

        System.out.println(authorWorks);

        authorWorks.docs().forEach(book -> System.out.println(book.top_work()));

        return authorWorks.docs().stream()
                .map(book -> new Book(book.top_work(), book.name()))
                .collect(Collectors.toList());
    }

    private URI buildUri(String path, String paramValue) {
        String baseUrl = "https://openlibrary.org";
        DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(baseUrl);

        return uriBuilderFactory.uriString(path)
                .queryParam("q", "{q}")
                .build(paramValue);
    }
}
