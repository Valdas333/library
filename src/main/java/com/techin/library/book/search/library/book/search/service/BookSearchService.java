package com.techin.library.book.search.library.book.search.service;

import com.techin.library.book.search.library.book.search.models.AuthorWorkList;
import com.techin.library.book.search.library.book.search.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookSearchService {
    private final RestClient restClient;

    @Autowired
    public BookSearchService(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<Book> searchBooksByAuthor(String author) {
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
